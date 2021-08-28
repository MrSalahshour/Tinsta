package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.LikeTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

import javax.swing.*;

public class LikeTweetListener {
    Context context = new Context();

    public void eventOccurred(LikeTweetEvent likeTweetEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) likeTweetEvent.getSource();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,showTweetsView.getMessageView().getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                (new GetCommentEvent(this,showTweetsView.getMessageView().getMessageId()));
        Comment comment = getCommentResponse.getComment();
        if (tweet!=null){
            likeTweetEvent.setTweet(tweet);
        }
        else {
            likeTweetEvent.setTweet(comment);
        }
        SaveMessageResponse saveMessageResponse = (SaveMessageResponse) MainController.sendEvents(likeTweetEvent);
        Message message = saveMessageResponse.getMessage();
        if (message instanceof Comment)
            context.getComments().update((Comment) message);
        else if (message instanceof Tweet)
            context.getTweets().update((Tweet) message);

        String msg = "Done!";
        JOptionPane.showMessageDialog(showTweetsView, msg,"Liked Successfully"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
