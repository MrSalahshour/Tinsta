package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MainPages;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetCurrentExplorerTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GetCurrentHomePageTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.HasNextCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.HasNextTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.NextCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.NextTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.GetCurrentTimeLineTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class NextTweetListener {

    public void eventOccurred(NextTweetEvent nextTweetEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) nextTweetEvent.getSource();
        handleNextInHomePage(showTweetsView,nextTweetEvent);
        handleNextInExplorer(showTweetsView,nextTweetEvent);
        handleNextInTimeLine(showTweetsView,nextTweetEvent);
    }

    private void handleNextInHomePage(ShowTweetsView showTweetsView,NextTweetEvent nextTweetEvent){
        if (showTweetsView.getSource().toString().equals(MainViews.HomePageView.getName())){
            String origin = MainPages.HomePage.getName();
            handleNext(showTweetsView, origin, nextTweetEvent);
        }
    }

    private void handleNextInExplorer(ShowTweetsView showTweetsView,NextTweetEvent nextTweetEvent){
        if (showTweetsView.getSource().toString().equals(MainViews.ExplorerView.getName())){
            String origin = MainPages.Explorer.getName();
            handleNext(showTweetsView, origin, nextTweetEvent);

        }
    }

    private void handleNextInTimeLine(ShowTweetsView showTweetsView,NextTweetEvent nextTweetEvent){
        if (showTweetsView.getSource().toString().equals(MainViews.TimeLineView.getName())){
            String origin = MainPages.TimeLine.getName();
            handleNext(showTweetsView, origin, nextTweetEvent);

        }
    }

    private void handleNext(ShowTweetsView showTweetsView, String origin, NextTweetEvent nextTweetEvent) {
        if (isTweet(showTweetsView)){
            GetBooleanResponse hasNext = (GetBooleanResponse) MainController
                    .sendEvents(new HasNextTweetEvent(this,origin));
            if (!hasNext.isaBoolean()){
                String message = "You Don't Have Next Tweet !";
                JOptionPane.showMessageDialog(showTweetsView, message,"Error"
                        ,JOptionPane.ERROR_MESSAGE);
                return;
            }
            nextTweetEvent.setOrigin(origin);
            MainController.sendEvents(nextTweetEvent);
            Tweet newTweet;
            if (origin.equals(MainPages.TimeLine.getName())){
                GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                        (new GetCurrentTimeLineTweetEvent(this));
                newTweet = getTweetResponse.getTweet();
            }
            else if (origin.equals(MainPages.Explorer.getName())){
                GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                        (new GetCurrentExplorerTweetEvent(this));
                newTweet = getTweetResponse.getTweet();
            }
            else {
                GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                        (new GetCurrentHomePageTweetEvent(this));
                newTweet = getTweetResponse.getTweet();
            }
            GetUserResponse getUserResponse1 = (GetUserResponse) MainController
                    .sendEvents(new GetUserEvent(this,newTweet.getOwnerId()));
            User user = getUserResponse1.getUser();
            GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                    .sendEvents(new GetUserNameEvent(this,user.getUserNameId()));
            UserName userName = getUserNameResponse.getUserName();
            MessageView messageView = new MessageView(newTweet.getId());
            messageView.setOwnerImagePath(user.getProfileImagePath());
            messageView.setMessageImagePath(newTweet.getImagePath());
            messageView.setPublishedTimeText(newTweet.getPublishedTime());
            messageView.setMessageContentTextArea(newTweet.getContent());
            messageView.setTweetOwnerUserNameText(userName.getUserNameText());
            messageView.configElements();
            showTweetsView.remove(showTweetsView.getMessageView());
            showTweetsView.revalidate();
            showTweetsView.repaint();
            showTweetsView.setTweetView(messageView);
            showTweetsView.configMessageView();
        }
        else if (isComment(showTweetsView)){
            Tweet parent;
            GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                    (new GetCommentEvent(this,showTweetsView.getMessageView().getMessageId()));
            Comment comment = getCommentResponse.getComment();
            GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                    (new GetTweetEvent(this,comment.getTweetId()));
            Tweet parentTweet = getTweetResponse.getTweet();
            if (parentTweet!=null)
                parent = parentTweet;
            else {
                GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController.sendEvents
                        (new GetCommentEvent(this,comment.getTweetId()));
                parent = getCommentResponse1.getComment();
            }
            GetBooleanResponse getBooleanResponse = (GetBooleanResponse) MainController
                    .sendEvents(new HasNextCommentEvent(this,parent));
            if (!getBooleanResponse.isaBoolean()){
                String message = "You Don't Have Next Comment !";
                JOptionPane.showMessageDialog(showTweetsView, message,"Error"
                        ,JOptionPane.ERROR_MESSAGE);
                return;
            }
            MainController.sendEvents(new NextCommentEvent(this,parent));
            if (parent instanceof Comment){
                GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController
                        .sendEvents(new GetCommentEvent(this,parent.getId()));
                parent = getCommentResponse1.getComment();
            }
            else {
                GetTweetResponse getTweetResponse1 = (GetTweetResponse) MainController
                        .sendEvents(new GetTweetEvent(this,parent.getId()));
                parent = getTweetResponse1.getTweet();
            }
            GetCommentResponse getCommentResponse1 = (GetCommentResponse) MainController.sendEvents
                    (new GetCommentEvent(this,parent.getCurrentCommentId()));
            Comment nextComment = getCommentResponse1.getComment();
            GetUserResponse getUserResponse1 = (GetUserResponse) MainController.sendEvents
                    (new GetUserEvent(this,nextComment.getOwnerId()));
            User user = getUserResponse1.getUser();
            GetUserNameResponse getUserNameResponse = (GetUserNameResponse) MainController
                    .sendEvents(new GetUserNameEvent(this,user.getUserNameId()));
            UserName userName = getUserNameResponse.getUserName();
            MessageView messageView = new MessageView(nextComment.getId());
            messageView.setOwnerImagePath(user.getProfileImagePath());
            messageView.setMessageImagePath(nextComment.getImagePath());
            messageView.setPublishedTimeText(nextComment.getPublishedTime());
            messageView.setMessageContentTextArea(nextComment.getContent());
            messageView.setTweetOwnerUserNameText(userName.getUserNameText());
            messageView.configElements();
            showTweetsView.remove(showTweetsView.getMessageView());
            showTweetsView.revalidate();
            showTweetsView.repaint();
            showTweetsView.setTweetView(messageView);
            showTweetsView.configMessageView();
        }
        showTweetsView.revalidate();
        showTweetsView.repaint();
    }

    private boolean isTweet(ShowTweetsView showTweetsView){
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,showTweetsView.getMessageView().getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        return tweet != null;

    }

    private boolean isComment(ShowTweetsView showTweetsView){
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                (new GetCommentEvent(this,showTweetsView.getMessageView().getMessageId()));
        Comment comment = getCommentResponse.getComment();
        return comment != null;

    }
}
