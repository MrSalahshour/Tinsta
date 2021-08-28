package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserNameEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserNameResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ShowCommentsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class ShowCommentsListener {

    public void eventOccurred(ShowCommentsEvent showCommentsEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) showCommentsEvent.getSource();
        showTweetsView.setComment(true);
        Tweet mainTweet;
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,showTweetsView.getMessageView().getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        if (tweet !=null)
            mainTweet = tweet;
        else {
            GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                    (new GetCommentEvent(this,showTweetsView.getMessageView().getMessageId()));
            mainTweet = getCommentResponse.getComment();
        }
        if (mainTweet.getCurrentCommentId()==null){
            String message = "Don't Have Any Comments!";
            JOptionPane.showMessageDialog(showTweetsView, message,"Error"
                    ,JOptionPane.ERROR_MESSAGE);
            return;
        }
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                (new GetCommentEvent(this,mainTweet.getCurrentCommentId()));
        Comment comment = getCommentResponse.getComment();
        GetUserResponse getUserResponse = (GetUserResponse) MainController.sendEvents
                (new GetUserEvent(this,comment.getOwnerId()));
        User user = getUserResponse.getUser();
        GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController.sendEvents
                (new GetUserNameEvent(this,user.getUserNameId()));
        UserName userName = getUserNameResponse.getUserName();
        MessageView messageView = new MessageView(comment.getId());
        messageView.setOwnerImagePath(user.getProfileImagePath());
        messageView.setMessageImagePath(comment.getImagePath());
        messageView.setPublishedTimeText(comment.getPublishedTime());
        messageView.setMessageContentTextArea(comment.getContent());
        messageView.setTweetOwnerUserNameText(userName.getUserNameText());
        messageView.configElements();
        showTweetsView.remove(showTweetsView.getMessageView());
        showTweetsView.setTweetView(messageView);
        showTweetsView.configMessageView();
        showTweetsView.revalidate();
        showTweetsView.repaint();


    }
}
