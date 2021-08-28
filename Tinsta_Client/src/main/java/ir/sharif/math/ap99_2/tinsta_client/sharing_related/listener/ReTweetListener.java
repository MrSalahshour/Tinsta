package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ReTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

import javax.swing.*;

public class ReTweetListener {

    public void eventOccurred(ReTweetEvent reTweetEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) reTweetEvent.getSource();
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
        reTweetEvent.setTweet(mainTweet);
        MainController.sendEvents(reTweetEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(showTweetsView, message,"ReTweed a Tweet!"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
