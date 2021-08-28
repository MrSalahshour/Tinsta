package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowSavedMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class ShowSavedMessageListener {
    Context context = new Context();

    public void eventOccurred(ShowSavedMessageEvent showSavedMessageEvent) {
        ChatRoomView chatRoomView = (ChatRoomView) showSavedMessageEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        ChatView chatView = new ChatView(chatRoomView);
        if (!MainController.isConnected())
            handleOfflinePart(panel, chatView);

        else
            handleOnlinePart(panel, chatView);
        chatView.setAddToSavedMessageFromChatListener(new AddToSavedMessageFromChatListener());
        chatView.setBackToListener(new BackToListener());
        chatView.setBackToLoginListener(new BackToLoginListener());
        chatView.setDeleteMessageListener(new DeleteMessageListener());
        chatView.setEditMessageListener(new EditMessageListener());
        chatView.setNextMessageListener(new NextMessageListener());
        chatView.setPreviousMessageListener(new PreviousMessageListener());
        chatView.setSelectImageForMessageListener(new SelectImageForMessageListener());
        chatView.setSendMessageListener(new SendMessageListener());
        panel.add(chatView);
        panel.revalidate();
        panel.repaint();
    }

    private Message getMessage(int messageId) {
        Message main;
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController
                .sendEvents(new GetMessageEvent(this, messageId));
        Message message = getMessageResponse.getMessage();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                .sendEvents(new GetTweetEvent(this, messageId));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController
                .sendEvents(new GetCommentEvent(this, messageId));
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

    private void handleOfflinePart(JPanel panel, ChatView chatView) {
        OfflineUser offlineOwner = context.getOfflineUsers().get(MainController.getUserId());
        ChatRoom ownerChatRoom = context.getChatRooms().get(offlineOwner.getChatRoomID());
        Integer messageId = ownerChatRoom.getCurrentSavedMessageId();
        panel.removeAll();
        if (messageId != null) {
            Message main = getOfflineMessage(messageId);
            MessageView messageView = new MessageView(main.getId());
            OfflineUser offlineUser = context.getOfflineUsers().get(main.getOwnerId());
            UserName messageOwnerUserName = context.getUserNames().get(offlineUser.getUserNameId());
            messageView.setMessageContentTextArea(main.getContent());
            messageView.setMessageImagePath(main.getImagePath());
            messageView.setOwnerImagePath(offlineUser.getProfileImagePath());
            messageView.setPublishedTimeText(main.getPublishedTime());
            messageView.setTweetOwnerUserNameText(messageOwnerUserName.getUserNameText());
            messageView.configElements();
            chatView.setMessageView(messageView);
            chatView.configMessageView();
        }
        if (messageId == null) {
            chatView.setMessageView(null);
        }
    }

    private void handleOnlinePart(JPanel panel, ChatView chatView) {
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent(this));
        User owner = getUserResponse.getUser();
        ChatRoom ownerChatRoom = owner.getMenu().getChatRoom();
        Integer messageId = ownerChatRoom.getCurrentSavedMessageId();
        panel.removeAll();
        if (messageId != null) {
            Message main = getMessage(messageId);
            MessageView messageView = new MessageView(main.getId());
            GetUserResponse getUserResponse1 = (GetUserResponse) MainController.sendEvents(new GetUserEvent(this, main.getOwnerId()));
            User messageOwner = getUserResponse1.getUser();
            GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                    .sendEvents(new GetUserNameEvent(this, messageOwner.getUserNameId()));
            UserName messageOwnerUserName = getUserNameResponse.getUserName();
            messageView.setMessageContentTextArea(main.getContent());
            messageView.setMessageImagePath(main.getImagePath());
            messageView.setOwnerImagePath(messageOwner.getProfileImagePath());
            messageView.setPublishedTimeText(main.getPublishedTime());
            messageView.setTweetOwnerUserNameText(messageOwnerUserName.getUserNameText());
            messageView.configElements();
            chatView.setMessageView(messageView);
            chatView.configMessageView();
        }
        if (messageId == null) {
            chatView.setMessageView(null);
        }
    }
}
