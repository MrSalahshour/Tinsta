package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.PostTweetView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.AddCommentEvent;

import javax.swing.*;

public class AddCommentListener {

    public void eventOccurred(AddCommentEvent addCommentEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) addCommentEvent.getSource();

        if (showTweetsView.getSource().toString().equals(MainViews.HomePageView.getName())){
            HomePageView homePageView = (HomePageView) showTweetsView.getSource();
            JPanel panel = homePageView.getSource();
            panel.removeAll();
            PostTweetView postTweetView = new PostTweetView(homePageView, showTweetsView);
            postTweetView.setBackToLoginListener(new BackToLoginListener());
            postTweetView.setBackToListener(new BackToListener());
            postTweetView.setPostTweetListener(new PostTweetListener());
            postTweetView.setSelectImageForTweetListener(new SelectImageForTweetListener());
            panel.add(postTweetView);
            panel.revalidate();
            panel.repaint();

        }
        if (showTweetsView.getSource().toString().equals(MainViews.ExplorerView.getName())){
            ExplorerView explorerView = (ExplorerView) showTweetsView.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            PostTweetView postTweetView = new PostTweetView(explorerView, showTweetsView);
            postTweetView.setBackToLoginListener(new BackToLoginListener());
            postTweetView.setBackToListener(new BackToListener());
            postTweetView.setPostTweetListener(new PostTweetListener());
            postTweetView.setSelectImageForTweetListener(new SelectImageForTweetListener());
            panel.add(postTweetView);
            panel.revalidate();
            panel.repaint();


        }
        if (showTweetsView.getSource().toString().equals(MainViews.TimeLineView.getName())){
            TimeLineView timeLineView = (TimeLineView) showTweetsView.getSource();
            JPanel panel = timeLineView.getSource();
            panel.removeAll();
            PostTweetView postTweetView = new PostTweetView(timeLineView, showTweetsView);
            postTweetView.setBackToLoginListener(new BackToLoginListener());
            postTweetView.setBackToListener(new BackToListener());
            postTweetView.setPostTweetListener(new PostTweetListener());
            postTweetView.setSelectImageForTweetListener(new SelectImageForTweetListener());
            panel.add(postTweetView);
            panel.revalidate();
            panel.repaint();

        }


    }
}
