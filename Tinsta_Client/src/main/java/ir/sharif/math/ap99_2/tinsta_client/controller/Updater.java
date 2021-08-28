package ir.sharif.math.ap99_2.tinsta_client.controller;

import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.*;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ShowUserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.SingleChat;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MessageState;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetCurrentExplorerTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetUserProfileTextEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.ShowExplorerTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.GetCurrentTimeLineTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.ShowTimeLineTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.IsConnectedEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.util.Loop;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;


public class Updater {
    private final JPanel panel;
    private boolean connected;
    Context context = new Context();

    public Updater(JPanel panel) {
        this.panel = panel;
        this.connected = false;
        Loop loop = new Loop(1, this::update);
        loop.start();
        Loop messageUpdater = new Loop(1, this::updateMessages);
        Loop offlineEventUpdater = new Loop(1,this::sendOfflineEvents);
        messageUpdater.start();
        offlineEventUpdater.start();
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public synchronized void update() {
        if (MainController.isConnected()){
            for (Component component : panel.getComponents()) {
                if (component instanceof ShowFollowersView) {
                    updateFollowersView(component);
                } else if (component instanceof ChatView) {
                    ChatView chatView = (ChatView) component;
                    MessageView messageView = chatView.getMessageView();
                    if (chatView.getGroupName() != null) {
                        updateGroupChat(chatView, messageView);
                    } else if (chatView.getUserFromId() != null) {
                        updateSingleChat(chatView, messageView);
                    }
                } else if (component instanceof ShowGroupChatsView) {
                    updateShowGroupChatsView(component);
                } else if (component instanceof ShowUserProfileView) {
                    updateUserProfileView(component);
                } else if (component instanceof ShowTweetsView) {
                    updateShowTweetsView(component);
                }
            }
        }
    }

    private void updateFollowersView(Component component) {
        GetStringResponse getStringResponse = (GetStringResponse)
                MainController.sendEvents(new ShowFollowersEvent());
        ShowFollowersView showFollowersView = (ShowFollowersView) component;
        showFollowersView.setShowFollowersTextArea(getStringResponse.getContent());
        showFollowersView.revalidate();
        showFollowersView.repaint();
    }

    private void updateShowGroupChatsView(Component component) {
        GetStringResponse getStringResponse = (GetStringResponse)
                MainController.sendEvents(new ShowGroupChatsEvent());
        ShowGroupChatsView showGroupChatsView = (ShowGroupChatsView) component;
        showGroupChatsView.setShowGroupChatsTextArea(getStringResponse.getContent());
        showGroupChatsView.revalidate();
        showGroupChatsView.repaint();
    }

    private void updateUserProfileView(Component component) {
        ShowUserProfileView showUserProfileView = (ShowUserProfileView) component;
        UserProfileView userProfileViewOld = showUserProfileView.getUserProfileView();
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this, showUserProfileView.getUserId()));
        User user = getUserResponse.getUser();
        GetStringResponse getStringResponse = (GetStringResponse) MainController
                .sendEvents(new GetUserProfileTextEvent(this, user));

        if (!getStringResponse.getContent().equals(userProfileViewOld.getProfileDetailsTextArea().getText())) {
            UserProfileView userProfileView = new UserProfileView(showUserProfileView.getUserId());
            userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
            userProfileView.setImageProfilePath(user.getProfileImagePath());
            userProfileView.configElements();
            showUserProfileView.remove(userProfileViewOld);
            showUserProfileView.setUserProfileView(userProfileView);
            showUserProfileView.configUserProfileView();
            showUserProfileView.revalidate();
            showUserProfileView.repaint();
        }
    }

    private void updateGroupChat(ChatView chatView, MessageView messageView) {
        if (!MainController.isConnected())
            return;
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent());
        User owner = getUserResponse.getUser();
        MainController.sendEvents(new UpdateUserEvent(this, owner));
        Group group = owner.getMenu().getChatRoom().getMultiChat().getGroup(chatView.getGroupName());
        if (!group.getMessagesId().isEmpty()) {
            if (messageView != null) {
                if (chatView.isOnline()) {
                    GetMessageResponse getMessageResponse = (GetMessageResponse) MainController.sendEvents
                            (new GetMessageEvent(this, group.getMessagesId().getLast()));
                    Message message = getMessageResponse.getMessage();
                    updateOfflineMessage(message);
                    GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                            .sendEvents(new GetUserEvent(this, message.getOwnerId()));
                    User user = getUserResponse1.getUser();
                    GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                            .sendEvents(new GetUserNameEvent(this, user.getUserNameId()));
                    UserName userName = getUserNameResponse.getUserName();
                    MessageView newMessageView = new MessageView(message.getId());
                    newMessageView.setPublishedTimeText(message.getPublishedTime());
                    newMessageView.setMessageState(message.getState());
                    newMessageView.setTweetOwnerUserNameText(userName.getUserNameText());
                    newMessageView.setOwnerImagePath(user.getProfileImagePath());
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
            }
        }
    }

    private void updateSingleChat(ChatView chatView, MessageView messageView) {
        if (!MainController.isConnected())
            return;
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent());
        User owner = getUserResponse.getUser();
        MainController.sendEvents(new UpdateUserEvent(this, owner));
        GetUserResponse ownerFinal = (GetUserResponse) MainController.sendEvents(new GetClientUserEvent());
        owner = ownerFinal.getUser();
        GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this, chatView.getUserFromId()));
        SingleChat singleChat = owner.getMenu().getChatRoom().searchChats(getUserResponse1.getUser().getId());
        if (!singleChat.getAllMessagesId().isEmpty()) {
            if (messageView != null) {
                if (chatView.isOnline()) {
                    GetMessageResponse getMessageResponse = (GetMessageResponse) MainController.sendEvents
                            (new GetMessageEvent(this, singleChat.getAllMessagesId().getLast()));
                    Message message = getMessageResponse.getMessage();
                    updateOfflineMessage(message);
                    GetUserResponse getUserResponse2 = (GetUserResponse) MainController
                            .sendEvents(new GetUserEvent(this, message.getOwnerId()));
                    User user = getUserResponse2.getUser();
                    GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                            .sendEvents(new GetUserNameEvent(this, user.getUserNameId()));
                    UserName userName = getUserNameResponse.getUserName();
                    MessageView newMessageView = new MessageView(message.getId());
                    newMessageView.setPublishedTimeText(message.getPublishedTime());
                    newMessageView.setMessageState(message.getState());
                    newMessageView.setTweetOwnerUserNameText(userName.getUserNameText());
                    newMessageView.setOwnerImagePath(user.getProfileImagePath());
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

            }

        }
    }

    private void updateShowTweetsView(Component component) {
        ShowTweetsView showTweetsView = (ShowTweetsView) component;
        int messageId = showTweetsView.getMessageView().getMessageId();
        String origin = showTweetsView.getSource().toString();
        if (origin.equals(MainViews.ExplorerView.toString())) {
            handleExplorer(showTweetsView, messageId);
        } else if (origin.equals(MainViews.TimeLineView.toString())) {
            handleTimeLine(showTweetsView, messageId);
        }
        else if (origin.equals(MainViews.HomePageView.toString())){
            handleHomePage(showTweetsView,messageId);
        }
    }

    private void handleView(Tweet tweet, ShowTweetsView showTweetsView) {
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this, tweet.getOwnerId()));
        User user = getUserResponse.getUser();
        GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                .sendEvents(new GetUserNameEvent(this, user.getUserNameId()));
        UserName userName = getUserNameResponse.getUserName();
        MessageView messageView = new MessageView(tweet.getId());
        messageView.setOwnerImagePath(user.getProfileImagePath());
        messageView.setMessageImagePath(tweet.getImagePath());
        messageView.setPublishedTimeText(tweet.getPublishedTime());
        messageView.setMessageContentTextArea(tweet.getContent());
        messageView.setTweetOwnerUserNameText(userName.getUserNameText());
        messageView.configElements();
        showTweetsView.remove(showTweetsView.getMessageView());
        showTweetsView.setTweetView(messageView);
        showTweetsView.configMessageView();
        showTweetsView.revalidate();
        showTweetsView.repaint();
    }

    private void handleExplorer(ShowTweetsView showTweetsView, int messageId) {
        if (!showTweetsView.isOnline()){
            return;
        }
        GetTweetResponse getTweetResponse1 = (GetTweetResponse) MainController.sendEvents(new GetTweetEvent
                (this, messageId));
        if (getTweetResponse1.getTweet() != null && !showTweetsView.isComment()) {
            MainController.sendEvents(new ShowExplorerTweetEvent());
            GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                    .sendEvents(new GetCurrentExplorerTweetEvent(this));
            if (getTweetResponse.getTweet() != null) {
                if (showTweetsView.isOnline()) {
                    if (messageId != getTweetResponse.getTweet().getId()) {
                        handleView(getTweetResponse.getTweet(), showTweetsView);
                    }
                }
            }
        } else if (showTweetsView.isOnline()) {
            GetCommentResponse getCommentResponse = (GetCommentResponse)
                    MainController.sendEvents(new GetCommentEvent(this, messageId));
            Comment comment = getCommentResponse.getComment();
            GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                    .sendEvents(new GetTweetEvent(this, comment.getTweetId()));
            Tweet tweet;
            if (getTweetResponse.getTweet() != null) {
                tweet = getTweetResponse.getTweet();
            } else {
                GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController
                        .sendEvents(new GetCommentEvent(this, comment.getTweetId()));
                tweet = getCommentResponse1.getComment();
            }
            if (!tweet.getCommentsId().getLast().equals(comment.getId())) {
                GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController
                        .sendEvents(new GetCommentEvent(this, tweet.getCommentsId().getLast()));
                handleView(getCommentResponse1.getComment(), showTweetsView);
            }
        }
    }

    private void handleTimeLine(ShowTweetsView showTweetsView, int messageId) {
        if (!showTweetsView.isOnline()){
            return;
        }
        GetTweetResponse getTweetResponse1 = (GetTweetResponse) MainController.sendEvents(new GetTweetEvent
                (this, messageId));
        if (getTweetResponse1.getTweet() != null && !showTweetsView.isComment()) {
            MainController.sendEvents(new ShowTimeLineTweetEvent());
            GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                    (new GetCurrentTimeLineTweetEvent(this));
            if (getTweetResponse.getTweet() != null) {
                if (showTweetsView.isOnline()) {
                    if (messageId != getTweetResponse.getTweet().getId()) {
                        handleView(getTweetResponse.getTweet(), showTweetsView);
                    }
                }
            }
        } else if (showTweetsView.isOnline()) {
            GetCommentResponse getCommentResponse = (GetCommentResponse)
                    MainController.sendEvents(new GetCommentEvent(this, messageId));
            Comment comment = getCommentResponse.getComment();
            GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                    .sendEvents(new GetTweetEvent(this, comment.getTweetId()));
            Tweet tweet;
            if (getTweetResponse.getTweet() != null) {
                tweet = getTweetResponse.getTweet();
            } else {
                GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController
                        .sendEvents(new GetCommentEvent(this, comment.getTweetId()));
                tweet = getCommentResponse1.getComment();
            }
            if (!tweet.getCommentsId().getLast().equals(comment.getId())) {
                GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController
                        .sendEvents(new GetCommentEvent(this, tweet.getCommentsId().getLast()));
                handleView(getCommentResponse1.getComment(), showTweetsView);
            }
        }
    }

    private void handleHomePage(ShowTweetsView showTweetsView, int messageId){
        if (!showTweetsView.isOnline())
            return;
        if (!showTweetsView.isComment())
            return;
        GetCommentResponse getCommentResponse = (GetCommentResponse)
                MainController.sendEvents(new GetCommentEvent(this, messageId));
        Comment comment = getCommentResponse.getComment();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController
                .sendEvents(new GetTweetEvent(this, comment.getTweetId()));
        Tweet tweet;
        if (getTweetResponse.getTweet() != null) {
            tweet = getTweetResponse.getTweet();
        } else {
            GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController
                    .sendEvents(new GetCommentEvent(this, comment.getTweetId()));
            tweet = getCommentResponse1.getComment();
        }
        if (!tweet.getCommentsId().getLast().equals(comment.getId())) {
            GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController
                    .sendEvents(new GetCommentEvent(this, tweet.getCommentsId().getLast()));
            handleView(getCommentResponse1.getComment(), showTweetsView);
        }

    }

    private synchronized void updateMessages() {
        if (MainController.isConnected() && MainController.getToken()!=null){
            if (!connected) {
                GetBooleanResponse getBooleanResponse = (GetBooleanResponse) MainController.sendEvents(new IsConnectedEvent());
                if (getBooleanResponse.isaBoolean()) {
                    setConnected(true);
                }
            }
            else {
                GetUserResponse getUserResponse = (GetUserResponse)
                        MainController.sendEvents(new GetUserEvent(null,MainController.getUserId()));
                if (getUserResponse!=null){
                    if (getUserResponse.getUser()!=null){
                        User owner = getUserResponse.getUser();
                        LinkedList<SingleChat> singleChats = owner.getMenu().getChatRoom().getMySingleChats();
                        for (SingleChat singleChat : singleChats) {
                            LinkedList<Integer> unreadMsg = singleChat.getMyUnreadMessagesId();
                            for (Integer integer : unreadMsg) {
                                Message message = getMessage(integer);
                                if (message.getState() == MessageState.SEND) {
                                    message.setState(MessageState.ONLINE);
                                    updateMessage(message);
                                    updateOfflineMessage(message);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private synchronized void sendOfflineEvents(){
        if (MainController.isConnected()){
            if (!MainController.isRefreshed()){
                Context context = new Context();
                LinkedList<Event> events = context.getEvents().all();
                Collections.sort(events);
                for (Event event: events) {
                    MainController.sendEvents(event);
                    context.getEvents().remove(event);
                }
                MainController.setRefreshed(true);
            }
        }
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

    private void updateMessage(Message message) {
        if (message instanceof Comment) {
            MainController.sendEvents(new UpdateCommentEvent((Comment) message));
        } else if (message instanceof Tweet) {
            MainController.sendEvents(new UpdateTweetEvent((Tweet) message));
        } else {
            MainController.sendEvents(new UpdateMessageEvent(message));
        }
    }
    private void updateOfflineMessage(Message message) {
        if (message instanceof Comment) {
            context.getComments().update((Comment) message);
        } else if (message instanceof Tweet) {
            context.getTweets().update((Tweet) message);
        } else {
            context.getMessages().update(message);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

}
