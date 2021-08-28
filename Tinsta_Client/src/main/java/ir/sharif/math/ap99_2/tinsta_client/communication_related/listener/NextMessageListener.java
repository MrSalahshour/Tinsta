package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.DeleteUnreadMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.NextMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.MultiChat;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.SingleChat;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MessageState;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class NextMessageListener {
    Context context = new Context();


    public void eventOccurred(NextMessageEvent nextMessageEvent) {
        ChatView chatView = (ChatView) nextMessageEvent.getSource();
        if (chatView.getUserFromId() == null && chatView.getGroupName() == null) {
            if (!MainController.isConnected())
                handleOfflineSavedMessage(chatView);
            else
                handleOnlineSavedMessage(chatView);
        } else if (chatView.getUserFromId() != null) {
            if (!MainController.isConnected())
                handleOfflineSingleChat(chatView);
            else
                handleOnlineSingleChat(chatView);
        } else {
            if (!MainController.isConnected())
                handleOfflineGroup(chatView);

            else
                handleOnlineGroup(chatView);
        }
    }

    private void handleView(int ownerId, ChatView chatView, Message message,
                            String profileImagePath, UserName userName) {
        MessageView newMessageView = new MessageView(message.getId());
        if (MainController.isConnected()){
            if (message.getOwnerId() != ownerId) {
                if (message.getState() != MessageState.SEEN) {
                    message.setState(MessageState.SEEN);
                    updateMessage(message);
                    newMessageView.setMessageState(MessageState.SEEN);
                }
            }
            else
                newMessageView.setMessageState(message.getState());
        }
        else
            newMessageView.setMessageState(message.getState());
        newMessageView.setPublishedTimeText(message.getPublishedTime());
        newMessageView.setTweetOwnerUserNameText(userName.getUserNameText());
        newMessageView.setOwnerImagePath(profileImagePath);
        newMessageView.setMessageImagePath(message.getImagePath());
        newMessageView.setMessageContentTextArea(message.getContent());
        newMessageView.configElements();
        chatView.remove(chatView.getMessageView());
        chatView.revalidate();
        chatView.repaint();
        chatView.setMessageView(newMessageView);
        chatView.configMessageView();
        chatView.revalidate();
        chatView.repaint();
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

    private void updateMessage(Message message) {
        if (message instanceof Comment) {
            MainController.sendEvents(new UpdateCommentEvent((Comment) message));
        } else if (message instanceof Tweet) {
            MainController.sendEvents(new UpdateTweetEvent((Tweet) message));
        } else {
            MainController.sendEvents(new UpdateMessageEvent(message));
        }
    }

    private void handleOfflineSavedMessage(ChatView chatView) {
        OfflineUser ownerOffline = context.getOfflineUsers().get(MainController.getUserId());
        ChatRoom chatRoom = context.getChatRooms().get(ownerOffline.getChatRoomID());
        if (!chatRoom.hasNextSavedMessage()) {
            String msg = "Don't have Next Message!";
            JOptionPane.showMessageDialog(chatView, msg,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        chatRoom.goNextSavedMessage();
        context.getChatRooms().update(chatRoom);
        Message message = getOfflineMessage(chatRoom.getCurrentSavedMessageId());
        OfflineUser user = context.getOfflineUsers().get(message.getOwnerId());
        UserName userName = context.getUserNames().get(user.getUserNameId());
        handleView(ownerOffline.getUserId(), chatView, message, user.getProfileImagePath(), userName);
    }

    private void handleOnlineSavedMessage(ChatView chatView) {
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent(this));
        User owner = getUserResponse.getUser();
        ChatRoom chatRoom = owner.getMenu().getChatRoom();
        if (!chatRoom.hasNextSavedMessage()) {
            String msg = "Don't have Next Message!";
            JOptionPane.showMessageDialog(chatView, msg,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        chatRoom.goNextSavedMessage();
        MainController.sendEvents(new UpdateUserEvent(this, owner));
        Message message = getMessage(chatRoom.getCurrentSavedMessageId());
        GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this, message.getOwnerId()));
        User user = getUserResponse1.getUser();
        GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController.sendEvents(new GetUserNameEvent(this, user.getUserNameId()));
        UserName userName = getUserNameResponse.getUserName();
        handleView(owner.getId(), chatView, message, user.getProfileImagePath(), userName);
    }

    private void handleOfflineSingleChat(ChatView chatView) {
        OfflineUser ownerOffline = context.getOfflineUsers().get(MainController.getUserId());
        OfflineUser userOffline = context.getOfflineUsers().get(chatView.getUserFromId());
        ChatRoom ownerChatRoom = context.getChatRooms().get(ownerOffline.getChatRoomID());
        SingleChat singleChat = ownerChatRoom.searchChats(userOffline.getUserId());
        if (!singleChat.hasNextMessage()) {
            String msg = "Don't have Next Message!";
            JOptionPane.showMessageDialog(chatView, msg,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        singleChat.goNextMessage();
        context.getChatRooms().update(ownerChatRoom);
        Message message = getOfflineMessage(singleChat.getCurrentMessageId());
        OfflineUser messageOwner = context.getOfflineUsers().get(message.getOwnerId());
        UserName userName = context.getUserNames().get(messageOwner.getUserNameId());
        handleView(ownerOffline.getUserId(), chatView, message, messageOwner.getProfileImagePath(), userName);
    }

    private void handleOnlineSingleChat(ChatView chatView) {
        GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this, chatView.getUserFromId()));
        User user = getUserResponse1.getUser();
        GetUserResponse getUserResponse2 = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent(this));
        User owner = getUserResponse2.getUser();
        SingleChat singleChat = owner.getMenu().getChatRoom().searchChats(user.getId());
        if (!singleChat.hasNextMessage()) {
            String msg = "Don't have Next Message!";
            JOptionPane.showMessageDialog(chatView, msg,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        singleChat.goNextMessage();
        MainController.sendEvents(new UpdateUserEvent(this, owner));
        Message message = getMessage(singleChat.getCurrentMessageId());
        context.getMessages().update(message);
        int id = message.getId();
        SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                MainController.sendEvents(new DeleteUnreadMessageEvent(user, id));
        Context context = new Context();
        context.getChatRooms().update(saveChatRoomResponse.getChatRoom());
        GetUserResponse getUserResponse4 = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this, message.getOwnerId()));
        User messageOwner = getUserResponse4.getUser();
        GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                .sendEvents(new GetUserNameEvent(this, messageOwner.getUserNameId()));
        UserName userName = getUserNameResponse.getUserName();
        handleView(owner.getId(), chatView, message, messageOwner.getProfileImagePath(), userName);
    }

    private void handleOfflineGroup(ChatView chatView) {
        OfflineUser ownerOffline = context.getOfflineUsers().get(MainController.getUserId());
        ChatRoom chatRoom = context.getChatRooms().get(ownerOffline.getChatRoomID());
        MultiChat multiChat = chatRoom.getMultiChat();
        Group group = multiChat.getGroup(chatView.getGroupName());
        if (!group.hasNextMessage()) {
            String msg = "Don't have Next Message!";
            JOptionPane.showMessageDialog(chatView, msg,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        group.goNextMessage();
        context.getChatRooms().update(chatRoom);
        Message message = getOfflineMessage(group.getCurrentMessageId());
        OfflineUser messageOwner = context.getOfflineUsers().get(message.getOwnerId());
        UserName userName = context.getUserNames().get(messageOwner.getUserNameId());
        handleView(ownerOffline.getUserId(), chatView, message, messageOwner.getProfileImagePath(), userName);
    }

    private void handleOnlineGroup(ChatView chatView) {
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent(this));
        User owner = getUserResponse.getUser();
        MultiChat multiChat = owner.getMenu().getChatRoom().getMultiChat();
        Group group = multiChat.getGroup(chatView.getGroupName());
        if (!group.hasNextMessage()) {
            String msg = "Don't have Next Message!";
            JOptionPane.showMessageDialog(chatView, msg,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        group.goNextMessage();
        MainController.sendEvents(new UpdateUserEvent(this, owner));
        Message message = getMessage(group.getCurrentMessageId());
        context.getMessages().update(message);
        GetUserResponse getUserResponse4 = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this, message.getOwnerId()));
        User messageOwner = getUserResponse4.getUser();
        GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                .sendEvents(new GetUserNameEvent(this, messageOwner.getUserNameId()));
        UserName userName = getUserNameResponse.getUserName();
        handleView(owner.getId(), chatView, message, messageOwner.getProfileImagePath(), userName);
    }
}
