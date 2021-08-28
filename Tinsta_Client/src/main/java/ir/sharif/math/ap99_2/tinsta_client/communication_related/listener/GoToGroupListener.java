package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.GroupChatsMenuView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToGroupEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.IsGroupExistEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupNotExist;

import javax.swing.*;

public class GoToGroupListener {
    Context context = new Context();

    public void eventOccurred(GoToGroupEvent goToGroupEvent) throws GroupNotExist {
        ChatRoomView chatRoomView;
        if (goToGroupEvent.getSource() instanceof ChatRoomView)
            chatRoomView = (ChatRoomView) goToGroupEvent.getSource();
        else {
            GroupChatsMenuView groupChatsMenuView = (GroupChatsMenuView) goToGroupEvent.getSource();
            chatRoomView = (ChatRoomView) groupChatsMenuView.getSource();
        }
        String groupName = goToGroupEvent.getGroupName();
        JPanel panel = chatRoomView.getSource();
        if (!MainController.isConnected()) {
            handleOfflinePart(groupName, chatRoomView, panel);
        } else {
            handleOnlinePart(groupName, chatRoomView, panel);
        }
        panel.revalidate();
        panel.repaint();

    }

    private Message getMessage(int MessageId) {
        Message main;
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController
                .sendEvents(new GetMessageEvent(this, MessageId));
        Message message = getMessageResponse.getMessage();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                .sendEvents(new GetTweetEvent(this, MessageId));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController
                .sendEvents(new GetCommentEvent(this, MessageId));
        Comment comment = getCommentResponse.getComment();
        if (message != null)
            main = message;
        else if (tweet != null)
            main = tweet;
        else
            main = comment;
        return main;
    }

    private Message getOfflineMessage(int MessageId) {
        Message main;
        Message message = context.getMessages().get(MessageId);
        Tweet tweet = context.getTweets().get(MessageId);
        Comment comment = context.getComments().get(MessageId);
        if (message != null)
            main = message;
        else if (tweet != null)
            main = tweet;
        else
            main = comment;
        return main;
    }

    private ChatView handleView(ChatRoomView chatRoomView, String groupName, Group group) {
        ChatView chatView = new ChatView(chatRoomView);
        chatView.setGroupName(groupName);
        chatView.setSendMessageListener(new SendMessageListener());
        chatView.setSendTimerMessageListener(new SendTimerMessageListener());
        chatView.setSelectImageForMessageListener(new SelectImageForMessageListener());
        chatView.setPreviousMessageListener(new PreviousMessageListener());
        chatView.setNextMessageListener(new NextMessageListener());
        chatView.setEditMessageListener(new EditMessageListener());
        chatView.setDeleteMessageListener(new DeleteMessageListener());
        chatView.setEditMessageListener(new EditMessageListener());
        chatView.setAddToSavedMessageFromChatListener(new AddToSavedMessageFromChatListener());
        chatView.setBackToLoginListener(new BackToLoginListener());
        chatView.setBackToListener(new BackToListener());
        return chatView;
    }

    private void handleOfflinePart(String groupName, ChatRoomView chatRoomView, JPanel panel) throws GroupNotExist {
        Context context = new Context();
        OfflineUser offlineUser = context.getOfflineUsers().get(MainController.getUserId());
        ChatRoom chatRoom = context.getChatRooms().get(offlineUser.getChatRoomID());
        if (!chatRoom.getMultiChat().isGroupExist(groupName))
            throw new GroupNotExist("Group Not Exist!");
        Group group = chatRoom.getMultiChat().getGroup(groupName);
        ChatView chatView = handleView(chatRoomView, groupName, group);
        if (group.getCurrentMessageId() == null) {
            chatView.setMessageView(null);
        } else {
            int messageId = group.getCurrentMessageId();
            Message message = getOfflineMessage(messageId);
            OfflineUser offlineMsgOwner = context.getOfflineUsers().get(message.getOwnerId());
            UserName userName = context.getUserNames().get(offlineMsgOwner.getUserNameId());
            MessageView newMessageView = new MessageView(message.getId());
            newMessageView.setPublishedTimeText(message.getPublishedTime());
            newMessageView.setTweetOwnerUserNameText(userName.getUserNameText());
            newMessageView.setOwnerImagePath(offlineMsgOwner.getProfileImagePath());
            newMessageView.setMessageImagePath(message.getImagePath());
            newMessageView.setMessageContentTextArea(message.getContent());
            newMessageView.configElements();
            if (chatView.getMessageView() != null) {
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
            }
            chatView.setMessageView(newMessageView);
            chatView.configMessageView();

        }
        panel.removeAll();
        panel.add(chatView);
    }

    private void handleOnlinePart(String groupName, ChatRoomView chatRoomView, JPanel panel) throws GroupNotExist {
        GroupExistResponse groupExistResponse = (GroupExistResponse) MainController.
                sendEvents(new IsGroupExistEvent(this, groupName));
        if (!groupExistResponse.isExist()) {
            throw new GroupNotExist("Group Not Exist!");
        }
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent(this));
        Group group = getUserResponse.getUser().getMenu()
                .getChatRoom().getMultiChat().getGroup(groupName);
        ChatView chatView = handleView(chatRoomView, groupName, group);
        if (group.getCurrentMessageId() == null) {
            chatView.setMessageView(null);
        } else {
            int messageId = group.getCurrentMessageId();
            Message message = getMessage(messageId);
            GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                    .sendEvents(new GetUserEvent(this, message.getOwnerId()));
            User messageOwner = getUserResponse1.getUser();
            GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                    .sendEvents(new GetUserNameEvent(this, messageOwner.getUserNameId()));
            UserName userName = getUserNameResponse.getUserName();
            MessageView newMessageView = new MessageView(message.getId());
            newMessageView.setPublishedTimeText(message.getPublishedTime());
            newMessageView.setTweetOwnerUserNameText(userName.getUserNameText());
            newMessageView.setOwnerImagePath(messageOwner.getProfileImagePath());
            newMessageView.setMessageImagePath(message.getImagePath());
            newMessageView.setMessageContentTextArea(message.getContent());
            newMessageView.setMessageState(message.getState());
            newMessageView.configElements();
            if (chatView.getMessageView() != null) {
                chatView.remove(chatView.getMessageView());
                chatView.revalidate();
                chatView.repaint();
            }
            chatView.setMessageView(newMessageView);
            chatView.configMessageView();

        }
        panel.removeAll();
        panel.add(chatView);
    }
}
