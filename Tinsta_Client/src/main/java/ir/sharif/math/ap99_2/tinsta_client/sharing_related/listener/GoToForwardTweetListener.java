package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardMessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.GoToForwardTweetEvent;

import javax.swing.*;

public class GoToForwardTweetListener {

    public void eventOccurred(GoToForwardTweetEvent goToForwardTweetEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) goToForwardTweetEvent.getSource();
        int messageId =showTweetsView.getMessageView().getMessageId();

        if (showTweetsView.getSource().toString().equals(MainViews.HomePageView.getName())){
            HomePageView homePageView = (HomePageView) showTweetsView.getSource();
            JPanel panel = homePageView.getSource();
            panel.removeAll();
            ForwardMessageView forwardMessageView = new ForwardMessageView(homePageView,messageId);
            configForwardMessageView(forwardMessageView);
            panel.add(forwardMessageView);
            panel.revalidate();
            panel.repaint();
        }

        if (showTweetsView.getSource().toString().equals(MainViews.TimeLineView.getName())){
            TimeLineView timeLineView = (TimeLineView) showTweetsView.getSource();
            JPanel panel = timeLineView.getSource();
            panel.removeAll();
            ForwardMessageView forwardMessageView = new ForwardMessageView(timeLineView,messageId);
            configForwardMessageView(forwardMessageView);
            panel.add(forwardMessageView);
            panel.revalidate();
            panel.repaint();

        }
        if (showTweetsView.getSource().toString().equals(MainViews.ExplorerView.getName())){
            ExplorerView explorerView = (ExplorerView) showTweetsView.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            ForwardMessageView forwardMessageView = new ForwardMessageView(explorerView,messageId);
            configForwardMessageView(forwardMessageView);
            panel.add(forwardMessageView);
            panel.revalidate();
            panel.repaint();

        }

    }

    private void configForwardMessageView(ForwardMessageView forwardMessageView){
        forwardMessageView.setBackToListener(new BackToListener());
        forwardMessageView.setBackToLoginListener(new BackToLoginListener());
        forwardMessageView.setGoToForwardToAllUsersListener(new GoToForwardToAllUsersListener());
        forwardMessageView.setGoToForwardToMultiUsersListener(new GoToForwardToMultiUsersListener());
        forwardMessageView.setGoToForwardToOneUserListener(new GoToForwardToOneUserListener());
        forwardMessageView.setGoToForwardToUserFolderListener(new GoToForwardToUserFolderListener());
    }
}
