package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardMessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardToMultiUsersView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.GoToForwardToMultiUsersEvent;

import javax.swing.*;

public class GoToForwardToMultiUsersListener {

    public void eventOccurred(GoToForwardToMultiUsersEvent goToForwardToMultiUsersEvent){
        ForwardMessageView forwardMessageView = (ForwardMessageView) goToForwardToMultiUsersEvent.getSource();
        int messageId = forwardMessageView.getMessageId();


        if (forwardMessageView.getSource().toString().equals(MainViews.HomePageView.getName())){
            HomePageView homePageView = (HomePageView) forwardMessageView.getSource();
            JPanel panel = homePageView.getSource();
            panel.removeAll();
            ForwardToMultiUsersView forwardToMultiUsersView = new ForwardToMultiUsersView(homePageView,messageId);
            configForwardToMultiUsersView(forwardToMultiUsersView);
            panel.add(forwardToMultiUsersView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.TimeLineView.getName())){
            TimeLineView timeLineView = (TimeLineView) forwardMessageView.getSource();
            JPanel panel = timeLineView.getSource();
            panel.removeAll();
            ForwardToMultiUsersView forwardToMultiUsersView = new ForwardToMultiUsersView(timeLineView,messageId);
            configForwardToMultiUsersView(forwardToMultiUsersView);
            panel.add(forwardToMultiUsersView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.ExplorerView.getName())){
            ExplorerView explorerView = (ExplorerView) forwardMessageView.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            ForwardToMultiUsersView forwardToMultiUsersView = new ForwardToMultiUsersView(explorerView,messageId);
            configForwardToMultiUsersView(forwardToMultiUsersView);
            panel.add(forwardToMultiUsersView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.ChatRoomView.getName())){
            ChatRoomView chatRoomView = (ChatRoomView) forwardMessageView.getSource();
            JPanel panel = chatRoomView.getSource();
            panel.removeAll();
            ForwardToMultiUsersView forwardToMultiUsersView = new ForwardToMultiUsersView(chatRoomView,messageId);
            configForwardToMultiUsersView(forwardToMultiUsersView);
            panel.add(forwardToMultiUsersView);
            panel.revalidate();
            panel.repaint();

        }

    }
    private void configForwardToMultiUsersView(ForwardToMultiUsersView forwardToMultiUsersView){
        forwardToMultiUsersView.setBackToListener(new BackToListener());
        forwardToMultiUsersView.setBackToLoginListener(new BackToLoginListener());
        forwardToMultiUsersView.setForwardToMultiUsersListener(new ForwardToMultiUsersListener());
    }
}
