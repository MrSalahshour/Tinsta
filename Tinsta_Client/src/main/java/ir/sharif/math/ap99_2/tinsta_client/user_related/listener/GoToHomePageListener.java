package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToHomePageEvent;

import javax.swing.*;

public class GoToHomePageListener {

    public void eventOccurred(GoToHomePageEvent goToHomePageEvent)  {
        JPanel panel = (JPanel) goToHomePageEvent.getSource();
        panel.removeAll();
        HomePageView homePageView = new HomePageView(panel);
        homePageView.setSelectImageForProfListener(new SelectImageForProfListener());
        homePageView.setBackToLoginListener(new BackToLoginListener());
        homePageView.setBackToMenuListener(new BackToMenuListener());
        homePageView.setGetMyInfoListener(new GetMyInfoListener());
        homePageView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        homePageView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        homePageView.setGoToNotificationsListener(new GoToNotificationsListener());
        homePageView.setGoToPostNewTweetListener(new GoToPostNewTweetListener());
        homePageView.setShowMyTweetsListener(new ShowMyTweetsListener());
        panel.add(homePageView);
        panel.revalidate();
        panel.repaint();
    }
}
