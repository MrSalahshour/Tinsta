package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.CommunicationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.StartChatEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MessageState;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class StartChatListener {
    Context context = new Context();

    public void eventOccurred(StartChatEvent startChatEvent) {
        JPanel source = (JPanel) startChatEvent.getSource();
        if (!MainController.isConnected())
            handleOfflinePart(startChatEvent);
        else {
            GetUserResponse getUserResponse = (GetUserResponse) MainController.
                    sendEvents(new GetUserEvent(this, startChatEvent.getUserId()));
            User user = getUserResponse.getUser();
            GetUserResponse getUserResponse1 = (GetUserResponse) MainController.sendEvents(new GetClientUserEvent(this));
            User owner = getUserResponse1.getUser();
            ChatRoom ownerChatRoom = owner.getMenu().getChatRoom();
            if (!ownerChatRoom.hasChatWith(user.getId()) && !ownerChatRoom.canStartChat(user, owner)) {
                String message = "You Can't Send Message To this User!";
                JOptionPane.showMessageDialog(source, message,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (startChatEvent.getSource().toString().equals("HomePageView"))
                handleHomePageOrigin(startChatEvent, ownerChatRoom, user, owner);
            if (startChatEvent.getSource().toString().equals("ExplorerView"))
                handleExplorerOrigin(startChatEvent, ownerChatRoom, user, owner);
            if (startChatEvent.getSource().toString().equals("TimeLineView"))
                handleTimeLineOrigin(startChatEvent, ownerChatRoom, user, owner);
            if (startChatEvent.getSource().toString().equals("CommunicationMenuView"))
                handleCommunicationOrigin(startChatEvent, ownerChatRoom, user, owner);
            if (startChatEvent.getSource().toString().equals("ChatRoomView"))
                handleChatRoomOrigin(startChatEvent, ownerChatRoom, user, owner);
        }
    }

    public void handleChatView(ChatView chatView, Integer userId, ChatRoom ownerChatRoom) {
        chatView.setUserFromId(userId);
        Integer messageId = ownerChatRoom.searchChats(userId).getCurrentMessageId();
        if (messageId != null) {
            Message main;
            Message message;
            Tweet tweet;
            Comment comment;
            if (!MainController.isConnected()){
                message = context.getMessages().get(messageId);
                tweet = context.getTweets().get(messageId);
                comment = context.getComments().get(messageId);
            }
            else {
                GetMessageResponse getMessageResponse = (GetMessageResponse) MainController
                        .sendEvents(new GetMessageEvent(this, messageId));
                message = getMessageResponse.getMessage();
                GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                        .sendEvents(new GetTweetEvent(this, messageId));
                tweet = getTweetResponse.getTweet();
                GetCommentResponse getCommentResponse = (GetCommentResponse) MainController
                        .sendEvents(new GetCommentEvent(this, messageId));
                comment = getCommentResponse.getComment();
            }
            if (message!=null)
                main = message;
            else if (tweet!=null)
                main = tweet;
            else
                main = comment;
            Integer id = main.getId();
            MessageView messageView = new MessageView(main.getId());
            if (!MainController.isConnected()){
                OfflineUser messageOwner = context.getOfflineUsers().get(main.getOwnerId());
                UserName messageOwnerUserName = context.getUserNames().get(messageOwner.getUserNameId());
                messageView.setOwnerImagePath(messageOwner.getProfileImagePath());
                messageView.setTweetOwnerUserNameText(messageOwnerUserName.getUserNameText());
                messageView.setMessageState(main.getState());
            }
            else {
                GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                        .sendEvents(new GetClientUserEvent(this));
                User owner = getUserResponse1.getUser();
                owner.getMenu().getChatRoom().searchChats(userId).getMyUnreadMessagesId().remove(id);
                GetUserResponse getUserResponse2 = (GetUserResponse) MainController
                        .sendEvents(new GetUserEvent(this, main.getOwnerId()));
                User messageOwner = getUserResponse2.getUser();
                GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                        .sendEvents(new GetUserNameEvent(this, messageOwner.getUserNameId()));
                UserName messageOwnerUserName = getUserNameResponse.getUserName();
                if (main.getOwnerId() != owner.getId()) {
                    main.setState(MessageState.SEEN);
                    updateMessage(main);
                    messageView.setMessageState(MessageState.SEEN);
                    context.getMessages().update(main);
                }
                else {
                    if (main.getState()==MessageState.OFFLINE){
                        main.setState(MessageState.SEND);
                        updateMessage(main);
                        messageView.setMessageState(MessageState.SEND);
                        context.getMessages().update(main);
                    }
                }
                messageView.setOwnerImagePath(messageOwner.getProfileImagePath());
                messageView.setTweetOwnerUserNameText(messageOwnerUserName.getUserNameText());
            }
            messageView.setMessageContentTextArea(main.getContent());
            messageView.setMessageImagePath(main.getImagePath());
            messageView.setPublishedTimeText(main.getPublishedTime());
            messageView.configElements();
            chatView.setMessageView(messageView);
            chatView.configMessageView();
        }
        if (messageId == null) {
            chatView.setMessageView(null);
        }
        chatView.setAddToSavedMessageFromChatListener(new AddToSavedMessageFromChatListener());
        chatView.setBackToListener(new BackToListener());
        chatView.setBackToLoginListener(new BackToLoginListener());
        chatView.setDeleteMessageListener(new DeleteMessageListener());
        chatView.setEditMessageListener(new EditMessageListener());
        chatView.setNextMessageListener(new NextMessageListener());
        chatView.setPreviousMessageListener(new PreviousMessageListener());
        chatView.setSelectImageForMessageListener(new SelectImageForMessageListener());
        chatView.setSendMessageListener(new SendMessageListener());
        chatView.setUserFromId(userId);
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

    private void handleOfflinePart(StartChatEvent startChatEvent) {
        if (startChatEvent.getSource().toString().equals("ChatRoomView")) {
            ChatRoomView chatRoomView = (ChatRoomView) startChatEvent.getSource();
            JPanel panel = chatRoomView.getSource();
            panel.removeAll();
            OfflineUser owner = context.getOfflineUsers().get(MainController.getUserId());
            ChatRoom ownerChatRoom = context.getChatRooms().get(owner.getChatRoomID());
            OfflineUser user = context.getOfflineUsers().get(startChatEvent.getUserId());
            if (!ownerChatRoom.hasChatWith(user.getUserId())) {
                return;
            }
            ChatView chatView = new ChatView(chatRoomView);
            handleChatView(chatView, user.getUserId(), ownerChatRoom);
            panel.add(chatView);
            panel.revalidate();
            panel.repaint();
        }
    }

    private void handleHomePageOrigin(StartChatEvent startChatEvent, ChatRoom ownerChatRoom, User user, User owner) {
        HomePageView homePageView = (HomePageView) startChatEvent.getSource();
        JPanel panel = homePageView.getSource();
        panel.removeAll();
        if (!ownerChatRoom.hasChatWith(user.getId())) {
            ownerChatRoom.startChat(user, owner);
            MainController.sendEvents(new UpdateUserEvent(this, user));
            MainController.sendEvents(new UpdateUserEvent(this, owner));
        }
        ChatView chatView = new ChatView(homePageView);
        handleChatView(chatView, user.getId(), ownerChatRoom);
        panel.add(chatView);
        panel.revalidate();
        panel.repaint();
    }

    private void handleExplorerOrigin(StartChatEvent startChatEvent, ChatRoom ownerChatRoom, User user, User owner) {
        ExplorerView explorerView = (ExplorerView) startChatEvent.getSource();
        JPanel panel = explorerView.getSource();
        panel.removeAll();
        if (!ownerChatRoom.hasChatWith(user.getId())) {
            ownerChatRoom.startChat(user, owner);
            MainController.sendEvents(new UpdateUserEvent(this, user));
            MainController.sendEvents(new UpdateUserEvent(this, owner));
        }
        ChatView chatView = new ChatView(explorerView);
        handleChatView(chatView, user.getId(), ownerChatRoom);
        panel.add(chatView);
        panel.revalidate();
        panel.repaint();
    }

    private void handleTimeLineOrigin(StartChatEvent startChatEvent, ChatRoom ownerChatRoom, User user, User owner) {
        TimeLineView timeLineView = (TimeLineView) startChatEvent.getSource();
        JPanel panel = timeLineView.getSource();
        panel.removeAll();
        if (!ownerChatRoom.hasChatWith(user.getId())) {
            ownerChatRoom.startChat(user, owner);
            MainController.sendEvents(new UpdateUserEvent(this, user));
            MainController.sendEvents(new UpdateUserEvent(this, owner));
        }

        ChatView chatView = new ChatView(timeLineView);
        handleChatView(chatView, user.getId(), ownerChatRoom);
        panel.add(chatView);
        panel.revalidate();
        panel.repaint();
    }

    private void handleCommunicationOrigin(StartChatEvent startChatEvent, ChatRoom ownerChatRoom, User user, User owner) {
        CommunicationMenuView communicationMenuView = (CommunicationMenuView) startChatEvent.getSource();
        JPanel panel = communicationMenuView.getSource();
        panel.removeAll();
        if (!ownerChatRoom.hasChatWith(user.getId())) {
            ownerChatRoom.startChat(user, owner);
            MainController.sendEvents(new UpdateUserEvent(this, user));
            MainController.sendEvents(new UpdateUserEvent(this, owner));
        }
        ChatView chatView = new ChatView(communicationMenuView);
        handleChatView(chatView, user.getId(), ownerChatRoom);
        panel.add(chatView);
        panel.revalidate();
        panel.repaint();
    }

    private void handleChatRoomOrigin(StartChatEvent startChatEvent, ChatRoom ownerChatRoom, User user, User owner) {
        ChatRoomView chatRoomView = (ChatRoomView) startChatEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        panel.removeAll();
        if (!ownerChatRoom.hasChatWith(user.getId())) {
            ownerChatRoom.startChat(user, owner);
            MainController.sendEvents(new UpdateUserEvent(this, user));
            MainController.sendEvents(new UpdateUserEvent(this, owner));
        }
        ChatView chatView = new ChatView(chatRoomView);
        handleChatView(chatView, user.getId(), ownerChatRoom);
        panel.add(chatView);
        panel.revalidate();
        panel.repaint();
    }
}
