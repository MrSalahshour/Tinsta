package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ReportEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

import javax.swing.*;

public class ReportListener {

    public void eventOccurred(ReportEvent reportEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) reportEvent.getSource();
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
        reportEvent.setTweet(mainTweet);
        MainController.sendEvents(reportEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(showTweetsView, message,"Added Report!"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
