package ir.sharif.math.ap99_2.tinsta_client.explorer.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.StartChatListener;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ShowUserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetClientUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetUserProfileTextEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GoToUserProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class GoToUserProfileListener {


    public void eventOccurred(GoToUserProfileEvent goToUserProfileEvent){
        Integer userId;
        ShowTweetsView showTweetsView = (ShowTweetsView) goToUserProfileEvent.getSource();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,showTweetsView.getMessageView().getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                (new GetCommentEvent(this,showTweetsView.getMessageView().getMessageId()));
        Comment comment = getCommentResponse.getComment();
        if (tweet!=null)
            userId = tweet.getOwnerId();
        else
            userId = comment.getOwnerId();


        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this,userId));
        User user = getUserResponse.getUser();
        GetUserResponse getClientResponse = (GetUserResponse)MainController.
                sendEvents(new GetClientUserEvent(this));
        int ownerId = getClientResponse.getUser().getId();
        if (user.getId()==ownerId){
            String message = "You Are The Writer!";
            JOptionPane.showMessageDialog(showTweetsView, message,""
                    ,JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (showTweetsView.getSource().toString().equals("HomePageView")){
            HomePageView homePageView = (HomePageView) goToUserProfileEvent.getSource();
            JPanel panel = homePageView.getSource();
            panel.removeAll();
            ShowUserProfileView showUserProfileView = new ShowUserProfileView(homePageView);
            UserProfileView userProfileView = new UserProfileView(showUserProfileView.getUserId());
            GetStringResponse getStringResponse = (GetStringResponse) MainController
                    .sendEvents(new GetUserProfileTextEvent(this,user));
            userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
            userProfileView.setImageProfilePath(user.getProfileImagePath());
            userProfileView.configElements();
            configShowUserProfileView(showUserProfileView, userId,userProfileView);
            panel.add(showUserProfileView);
            panel.revalidate();
            panel.repaint();
        }

        if (showTweetsView.getSource().toString().equals("TimeLineView")){
            TimeLineView timeLineView = (TimeLineView) goToUserProfileEvent.getSource();
            JPanel panel = timeLineView.getSource();
            panel.removeAll();
            ShowUserProfileView showUserProfileView = new ShowUserProfileView(timeLineView);

            UserProfileView userProfileView = new UserProfileView(showUserProfileView.getUserId());
            GetStringResponse getStringResponse = (GetStringResponse) MainController
                    .sendEvents(new GetUserProfileTextEvent(this,user));
            userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
            userProfileView.setImageProfilePath(user.getProfileImagePath());
            userProfileView.configElements();
            configShowUserProfileView(showUserProfileView, userId,userProfileView);
            panel.add(showUserProfileView);
            panel.revalidate();
            panel.repaint();

        }
        if (showTweetsView.getSource().toString().equals("ExplorerView")){
            ExplorerView explorerView = (ExplorerView) goToUserProfileEvent.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            ShowUserProfileView showUserProfileView = new ShowUserProfileView(explorerView);

            UserProfileView userProfileView = new UserProfileView(showUserProfileView.getUserId());
            GetStringResponse getStringResponse = (GetStringResponse) MainController
                    .sendEvents(new GetUserProfileTextEvent(this,user));
            userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
            userProfileView.setImageProfilePath(user.getProfileImagePath());
            userProfileView.configElements();
            configShowUserProfileView(showUserProfileView, userId,userProfileView);
            panel.add(showUserProfileView);
            panel.revalidate();
            panel.repaint();

        }

    }
    private void configShowUserProfileView(ShowUserProfileView showUserProfileView, Integer userId,
                                           UserProfileView userProfileView){
        showUserProfileView.setUserProfileView(userProfileView);
        showUserProfileView.setUserId(userId);
        showUserProfileView.setBackToListener(new BackToListener());
        showUserProfileView.setBlockOrUnBlockListener(new BlockOrUnBlockListener());
        showUserProfileView.setMuteOrUnMuteListener(new MuteOrUnMuteListener());
        showUserProfileView.setStartChatListener(new StartChatListener());
        showUserProfileView.setFollowOrUnfollowListener(new FollowOrUnfollowListener());
        showUserProfileView.setBackToLoginListener(new BackToLoginListener());
        showUserProfileView.configElements();

    }
}
