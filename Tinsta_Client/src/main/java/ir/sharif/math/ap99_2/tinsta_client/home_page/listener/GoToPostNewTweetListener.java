package ir.sharif.math.ap99_2.tinsta_client.home_page.listener;



import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.PostTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.SelectImageForTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.PostTweetView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToPostNewTweetEvent;

import javax.swing.*;

public class GoToPostNewTweetListener {
    public void eventOccurred(GoToPostNewTweetEvent goToPostNewTweetEvent){
        HomePageView homePageView = (HomePageView) goToPostNewTweetEvent.getSource();
        JPanel panel = homePageView.getSource();
        panel.removeAll();
        PostTweetView postTweetView = new PostTweetView(homePageView, null);
        postTweetView.setBackToLoginListener(new BackToLoginListener());
        postTweetView.setBackToListener(new BackToListener());
        postTweetView.setPostTweetListener(new PostTweetListener());
        postTweetView.setSelectImageForTweetListener(new SelectImageForTweetListener());
        panel.add(postTweetView);
        panel.revalidate();
        panel.repaint();

    }
}
