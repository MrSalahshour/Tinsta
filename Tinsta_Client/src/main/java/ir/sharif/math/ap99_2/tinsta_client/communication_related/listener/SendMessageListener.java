package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SendMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SendMsgEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.MultiChat;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MessageState;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserNameResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.util.LinkedList;

public class SendMessageListener {
    Context context = new Context();

    public void eventOccurred(SendMessageEvent sendMessageEvent){
        ChatView chatView = (ChatView) sendMessageEvent.getSource();

        if (chatView.getUserFromId() == null && chatView.getGroupName()==null){
            int messageId;
            if(!MainController.isConnected()){
                OfflineUser offlineUser = context.getOfflineUsers().get(MainController.getUserId());
                ChatRoom chatRoom = context.getChatRooms().get(offlineUser.getChatRoomID());
                String content = sendMessageEvent.getContent();
                String imagePath = sendMessageEvent.getImagePath();
                if (imagePath!=null){
                    imagePath = ImageLoader.copyImageToResource(imagePath);
                }
                Message message = new Message(offlineUser.getUserId(),content,imagePath);
                sendMessageEvent.setMessage(message);
                context.getEvents().add(new SendMsgEvent(this,sendMessageEvent));
                message.setState(MessageState.OFFLINE);
                context.getMessages().add(message);
                chatRoom.writeMsgForMySelf(message);
                context.getChatRooms().update(chatRoom);
                messageId = message.getId();
            }
            else {
                SaveMessageResponse saveMessageResponse = (SaveMessageResponse) MainController
                        .sendEvents(new SendMsgEvent(this,sendMessageEvent));
                context.getMessages().add(saveMessageResponse.getMessage());
                context.getChatRooms().update(saveMessageResponse.getChatRoom());
                GetUserResponse getUserResponse = (GetUserResponse)
                        MainController.sendEvents(new GetClientUserEvent(this));
                messageId = getUserResponse.getUser().getMenu().getChatRoom().getCurrentSavedMessageId();
            }

            handleView(messageId,chatView);
        }
        else if (chatView.getUserFromId() !=null){
            int messageId;
            if (!MainController.isConnected()){
                OfflineUser offlineUser = context.getOfflineUsers().get(chatView.getUserFromId());
                OfflineUser offlineOwner = context.getOfflineUsers().get(MainController.getUserId());
                String content = sendMessageEvent.getContent();
                String imagePath = sendMessageEvent.getImagePath();
                if (imagePath!=null){
                    imagePath = ImageLoader.copyImageToResource(imagePath);
                }
                Message message = new Message(offlineOwner.getUserId(),content,imagePath);
                sendMessageEvent.setMessage(message);
                context.getEvents().add(new SendMsgEvent(this,offlineUser.getUserId(),sendMessageEvent));
                message.setState(MessageState.OFFLINE);
                context.getMessages().add(message);
                ChatRoom userChatRoom = context.getChatRooms().get(offlineUser.getChatRoomID());
                ChatRoom ownerChatRoom = context.getChatRooms().get(offlineOwner.getChatRoomID());
                userChatRoom.searchChats(offlineOwner.getUserId()).addToUnReadMessagesId(message);
                userChatRoom.searchChats(offlineOwner.getUserId()).addToAllMessagesId(message);
                userChatRoom.searchChats(offlineOwner.getUserId()).setCurrentMessageId(message.getId());
                ownerChatRoom.searchChats(offlineUser.getUserId()).addToAllMessagesId(message);
                ownerChatRoom.searchChats(offlineUser.getUserId()).setCurrentMessageId(message.getId());
                context.getChatRooms().update(userChatRoom);
                context.getChatRooms().update(ownerChatRoom);
                messageId = message.getId();
            }
            else {
                GetUserResponse getUserResponse = (GetUserResponse)
                        MainController.sendEvents(new GetUserEvent(this,chatView.getUserFromId()));
                User user = getUserResponse.getUser();
                SaveMessageResponse saveMessageResponse = (SaveMessageResponse) MainController
                        .sendEvents(new SendMsgEvent(this,user.getId(),sendMessageEvent));
                context.getMessages().add(saveMessageResponse.getMessage());
                for (ChatRoom chatRoom : saveMessageResponse.getChatRooms()) {
                    context.getChatRooms().update(chatRoom);
                }
                GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                        .sendEvents(new GetUserEvent(this,user.getId()));
                GetUserResponse getUserResponse2 = (GetUserResponse)
                        MainController.sendEvents(new GetClientUserEvent(this));
                messageId = getUserResponse2.getUser().getMenu()
                        .getChatRoom().searchChats(getUserResponse1.getUser().getId()).getCurrentMessageId();
            }
            handleView(messageId,chatView);
        }
        else {
            Integer messageId;
            if (!MainController.isConnected()){
                String groupName = chatView.getGroupName();
                OfflineUser offlineOwner = context.getOfflineUsers().get(MainController.getUserId());
                String content = sendMessageEvent.getContent();
                String imagePath = sendMessageEvent.getImagePath();
                if (imagePath!=null){
                    imagePath = ImageLoader.copyImageToResource(imagePath);
                }
                Message message = new Message(offlineOwner.getUserId(),content,imagePath);
                sendMessageEvent.setMessage(message);
                context.getEvents().add(new SendMsgEvent(this,chatView.getGroupName(),sendMessageEvent));
                message.setState(MessageState.OFFLINE);
                context.getMessages().add(message);
                ChatRoom ownerChatRoom = context.getChatRooms().get(offlineOwner.getChatRoomID());
                Group group = ownerChatRoom.getMultiChat().getGroup(groupName);
                LinkedList<Integer> usersId = group.getAllUsersId();
                LinkedList<OfflineUser> users = new LinkedList<>();
                for (Integer id : usersId) {
                    users.add(context.getOfflineUsers().get(id));
                }
                for (OfflineUser user:users) {
                    if (user.getUserId()!=offlineOwner.getUserId()){
                        ChatRoom chatRoom = context.getChatRooms().get(user.getChatRoomID());
                        chatRoom.getMultiChat().getGroup(groupName).addToMessagesId(message.getId());
                        chatRoom.getMultiChat().getGroup(groupName).setCurrentMessageId(message.getId());
                        context.getChatRooms().update(chatRoom);
                    }
                    else {
                        group.addToMessagesId(message.getId());
                        group.setCurrentMessageId(message.getId());
                        context.getChatRooms().update(ownerChatRoom);
                    }
                }
                messageId = message.getId();
            }
            else {
                SaveMessageResponse saveMessageResponse = (SaveMessageResponse) MainController
                        .sendEvents(new SendMsgEvent(this,chatView.getGroupName(),sendMessageEvent));
                context.getMessages().add(saveMessageResponse.getMessage());
                for (ChatRoom chatRoom :saveMessageResponse.getChatRooms()) {
                    context.getChatRooms().update(chatRoom);
                }
                GetUserResponse getOwner = (GetUserResponse)
                        MainController.sendEvents(new GetClientUserEvent(this));
                MultiChat multiChat = getOwner.getUser().getMenu().getChatRoom().getMultiChat();
                messageId = multiChat.getGroup(chatView.getGroupName()).getCurrentMessageId();
            }

            handleView(messageId,chatView);
        }

    }
    private void handleView(int messageId,ChatView chatView){
        MessageView messageView = new MessageView(messageId);
        UserName userName;
        Message message;
        if (!MainController.isConnected()){
            message = context.getMessages().get(messageId);
            if (chatView.getMessageView()!=null){
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
            }
            OfflineUser messageOwner = context.getOfflineUsers().get(message.getOwnerId());
            userName = context.getUserNames().get(messageOwner.getUserNameId());
            messageView.setMessageState(MessageState.OFFLINE);
            messageView.setOwnerImagePath(messageOwner.getProfileImagePath());
        }
        else {
            GetMessageResponse getMessageResponse = (GetMessageResponse) MainController
                    .sendEvents(new GetMessageEvent(this,messageId));
            message = getMessageResponse.getMessage();
            MainController.sendEvents(new UpdateMessageEvent(message));
            if (chatView.getMessageView()!=null){
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
            }
            GetUserResponse getUserResponse = (GetUserResponse) MainController
                    .sendEvents(new GetUserEvent(this,message.getOwnerId()));
            User messageOwner = getUserResponse.getUser();
            GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                    .sendEvents(new GetUserNameEvent(this,messageOwner.getUserNameId()));
            userName = getUserNameResponse.getUserName();
            messageView.setMessageState(MessageState.SEND);
            messageView.setOwnerImagePath(messageOwner.getProfileImagePath());

        }

        messageView.setMessageContentTextArea(message.getContent());
        messageView.setMessageImagePath(message.getImagePath());
        messageView.setTweetOwnerUserNameText(userName.getUserNameText());
        messageView.setPublishedTimeText(message.getPublishedTime());
        messageView.configElements();
        chatView.setMessageView(messageView);
        chatView.configMessageView();
        chatView.revalidate();
        chatView.repaint();
    }
}
