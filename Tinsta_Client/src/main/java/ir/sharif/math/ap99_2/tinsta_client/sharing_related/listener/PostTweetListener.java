package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.PostTweetView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.AddCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.PostTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

import javax.swing.*;

public class PostTweetListener {

    public void eventOccurred(PostTweetEvent postTweetEvent){
        PostTweetView postTweetView = (PostTweetView) postTweetEvent.getSource();
        ShowTweetsView showTweetsView = postTweetView.getShowTweetsViewSource();
        if (showTweetsView==null) {
            MainController.sendEvents(postTweetEvent);
            String message = "Done!";
            JOptionPane.showMessageDialog(postTweetView, message,"Posted a Tweet!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            Tweet mainTweet;
            GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                    (new GetTweetEvent(this,showTweetsView.getMessageView().getMessageId()));
            Tweet tweet = getTweetResponse.getTweet();
            if (tweet!=null){
                mainTweet = tweet;
            }
            else {
                GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                        (new GetCommentEvent(this,showTweetsView.getMessageView().getMessageId()));
                mainTweet = getCommentResponse.getComment();
            }
            AddCommentEvent addCommentEvent = new AddCommentEvent(this);
            addCommentEvent.setParent(mainTweet);
            addCommentEvent.setPostTweetEvent(postTweetEvent);
            MainController.sendEvents(addCommentEvent);
            String message = "Done!";
            JOptionPane.showMessageDialog(postTweetView, message,"Posted a Comment!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }


    }
}
