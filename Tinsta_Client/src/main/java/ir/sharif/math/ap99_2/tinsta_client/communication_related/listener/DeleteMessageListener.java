package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.DeleteMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.SingleChat;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class DeleteMessageListener {
    Context context = new Context();

    public void eventOccurred(DeleteMessageEvent deleteMessageEvent){
        ChatView chatView = (ChatView) deleteMessageEvent.getSource();
        int messageId = chatView.getMessageView().getMessageId();
        Message main = getMessage(messageId);
        deleteMessageEvent.setMessage(main);
        GetUserResponse getUserResponse = (GetUserResponse) MainController.sendEvents(new GetClientUserEvent(chatView));
        User owner = getUserResponse.getUser();

        if (main.getOwnerId()!=owner.getId()){
            String msg = "You Can't delete this message!";
            JOptionPane.showMessageDialog(chatView, msg,
                    "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (chatView.getUserFromId()==null && chatView.getGroupName()==null){
            deleteMessageEvent.setSavedMessage(true);
            SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                    MainController.sendEvents(deleteMessageEvent);
            context.getChatRooms().update(saveChatRoomResponse.getChatRoom());
            GetUserResponse getUserResponse2 = (GetUserResponse) MainController.sendEvents(new GetClientUserEvent());
            ChatRoom chatRoom = getUserResponse2.getUser().getMenu().getChatRoom();
            if (chatRoom.getCurrentSavedMessageId()==null){
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
                chatView.setMessageView(null);
            }
            else {
                Message message = getMessage(chatRoom.getCurrentSavedMessageId());
                GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                        .sendEvents(new GetUserEvent(this,message.getOwnerId()));
                User user = getUserResponse1.getUser();
                GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                        .sendEvents(new GetUserNameEvent(this, user.getUserNameId()));
                UserName userName = getUserNameResponse.getUserName();
                MessageView messageView = new MessageView(chatRoom.getCurrentSavedMessageId());
                messageView.setPublishedTimeText(message.getPublishedTime());
                messageView.setTweetOwnerUserNameText(userName.getUserNameText());
                messageView.setOwnerImagePath(user.getProfileImagePath());
                messageView.setMessageImagePath(message.getImagePath());
                messageView.setMessageContentTextArea(message.getContent());
                messageView.configElements();
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
                chatView.setMessageView(messageView);
                chatView.configMessageView();

            }
            chatView.revalidate();
            chatView.repaint();
        }
        else if(chatView.getUserFromId()!=null){
            GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                    .sendEvents(new GetUserEvent(this,chatView.getUserFromId()));
            User userFrom = getUserResponse1.getUser();
            deleteMessageEvent.setChat(true);
            deleteMessageEvent.setUserFrom(userFrom);
            SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                    MainController.sendEvents(deleteMessageEvent);
            for (ChatRoom chatRoom: saveChatRoomResponse.getChatRooms()) {
                context.getChatRooms().update(chatRoom);
            }
            SingleChat singleChat = owner.getMenu().getChatRoom().searchChats(userFrom.getId());
            if (singleChat.getCurrentMessageId()==null){
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
                chatView.setMessageView(null);
            }
            else {
                Message message = getMessage(singleChat.getCurrentMessageId());
                GetUserResponse getUserResponse2 = (GetUserResponse) MainController
                        .sendEvents(new GetUserEvent(this,message.getOwnerId()));
                User user = getUserResponse2.getUser();
                GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                        .sendEvents(new GetUserNameEvent(this, user.getUserNameId()));
                UserName userName = getUserNameResponse.getUserName();
                MessageView messageView = new MessageView(singleChat.getCurrentMessageId());
                messageView.setPublishedTimeText(message.getPublishedTime());
                messageView.setTweetOwnerUserNameText(userName.getUserNameText());
                messageView.setOwnerImagePath(user.getProfileImagePath());
                messageView.setMessageImagePath(message.getImagePath());
                messageView.setMessageContentTextArea(message.getContent());
                messageView.configElements();
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
                chatView.setMessageView(messageView);
                chatView.configMessageView();

            }
            chatView.revalidate();
            chatView.repaint();
        }
        else {
            deleteMessageEvent.setGroup(true);
            deleteMessageEvent.setGroupName(chatView.getGroupName());
            SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                    MainController.sendEvents(deleteMessageEvent);
            for (ChatRoom chatRoom: saveChatRoomResponse.getChatRooms()) {
                context.getChatRooms().update(chatRoom);
            }
            GetUserResponse getUserResponse1 = (GetUserResponse) MainController.sendEvents(new GetClientUserEvent(chatView));
            owner = getUserResponse1.getUser();
            Group group = owner.getMenu().getChatRoom().getMultiChat().getGroup(chatView.getGroupName());
            if (group.getCurrentMessageId()==null){
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
                chatView.setMessageView(null);
            }
            else {
                Message message = getMessage(group.getCurrentMessageId());
                GetUserResponse getUserResponse2 = (GetUserResponse) MainController
                        .sendEvents(new GetUserEvent(this,message.getOwnerId()));
                User user = getUserResponse2.getUser();
                GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                        .sendEvents(new GetUserNameEvent(this, user.getUserNameId()));
                UserName userName = getUserNameResponse.getUserName();
                MessageView messageView = new MessageView(group.getCurrentMessageId());
                messageView.setPublishedTimeText(message.getPublishedTime());
                messageView.setTweetOwnerUserNameText(userName.getUserNameText());
                messageView.setOwnerImagePath(user.getProfileImagePath());
                messageView.setMessageImagePath(message.getImagePath());
                messageView.setMessageContentTextArea(message.getContent());
                messageView.configElements();
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
                chatView.setMessageView(messageView);
                chatView.configMessageView();

            }
            chatView.revalidate();
            chatView.repaint();

        }
    }

    private Message getMessage(int MessageId){
        Message main;
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController
                .sendEvents(new GetMessageEvent(this,MessageId));
        Message message = getMessageResponse.getMessage();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                .sendEvents(new GetTweetEvent(this,MessageId));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController
                .sendEvents(new GetCommentEvent(this,MessageId));
        Comment comment = getCommentResponse.getComment();
        if (message!=null)
            main = message;
        else if (tweet!=null)
            main = tweet;
        else
            main = comment;
        return main;
    }
}
