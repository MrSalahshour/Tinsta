package ir.sharif.math.ap99_2.tinsta_client.home_page.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.GoToUserProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserNameEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GetCurrentHomePageTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.ShowMyTweetsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserNameResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class ShowMyTweetsListener {

    public void eventOccurred(ShowMyTweetsEvent showMyTweetsEvent){
        HomePageView homePageView = (HomePageView) showMyTweetsEvent.getSource();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetCurrentHomePageTweetEvent(this));
        if (getTweetResponse.getTweet()==null){
            String message = "You Don't Have Any Tweets !";
            JOptionPane.showMessageDialog(homePageView, message,"Error"
                    ,JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = homePageView.getSource();
        panel.removeAll();
        ShowTweetsView showTweetsView = new ShowTweetsView(homePageView);
        Tweet tweet = getTweetResponse.getTweet();
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this,tweet.getOwnerId()));
        User user = getUserResponse.getUser();
        GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                .sendEvents(new GetUserNameEvent(this,user.getUserNameId()));
        UserName userName = getUserNameResponse.getUserName();
        MessageView messageView = new MessageView(tweet.getId());
        messageView.setOwnerImagePath(user.getProfileImagePath());
        messageView.setMessageImagePath(tweet.getImagePath());
        messageView.setPublishedTimeText(tweet.getPublishedTime());
        messageView.setMessageContentTextArea(tweet.getContent());
        messageView.setTweetOwnerUserNameText(userName.getUserNameText());
        messageView.configElements();
        showTweetsView.setTweetView(messageView);
        showTweetsView.configElements();
        showTweetsView.setAddCommentListener(new AddCommentListener());
        showTweetsView.setAddToSavedMessagesListener(new AddToSavedMessagesListener());
        showTweetsView.setBackToListener(new BackToListener());
        showTweetsView.setBackToLoginListener(new BackToLoginListener());
        showTweetsView.setForwardTweetListener(new GoToForwardTweetListener());
        showTweetsView.setGoToUserProfileListener(new GoToUserProfileListener());
        showTweetsView.setLikeTweetListener(new LikeTweetListener());
        showTweetsView.setNextTweetListener(new NextTweetListener());
        showTweetsView.setPreviousTweetListener(new PreviousTweetListener());
        showTweetsView.setReportListener(new ReportListener());
        showTweetsView.setReTweetListener(new ReTweetListener());
        showTweetsView.setShowCommentsListener(new ShowCommentsListener());
        panel.add(showTweetsView);
        panel.revalidate();
        panel.repaint();

    }
}
