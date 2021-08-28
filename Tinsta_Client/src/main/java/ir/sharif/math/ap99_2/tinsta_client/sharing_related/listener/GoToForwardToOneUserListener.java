package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardMessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardToOneUserView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.GoToForwardToOneUserEvent;

import javax.swing.*;

public class GoToForwardToOneUserListener {

    public void eventOccurred(GoToForwardToOneUserEvent goToForwardToOneUserEvent){
        ForwardMessageView forwardMessageView = (ForwardMessageView) goToForwardToOneUserEvent.getSource();
        int messageId = forwardMessageView.getMessageId();

        if (forwardMessageView.getSource().toString().equals(MainViews.HomePageView.getName())){
            HomePageView homePageView = (HomePageView) forwardMessageView.getSource();
            JPanel panel = homePageView.getSource();
            panel.removeAll();
            ForwardToOneUserView forwardToOneUserView = new ForwardToOneUserView(homePageView,messageId);
            configForwardToOneUserView(forwardToOneUserView);
            panel.add(forwardToOneUserView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.TimeLineView.getName())){
            TimeLineView timeLineView = (TimeLineView) forwardMessageView.getSource();
            JPanel panel = timeLineView.getSource();
            panel.removeAll();
            ForwardToOneUserView forwardToOneUserView = new ForwardToOneUserView(timeLineView,messageId);
            configForwardToOneUserView(forwardToOneUserView);
            panel.add(forwardToOneUserView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.ExplorerView.getName())){
            ExplorerView explorerView = (ExplorerView) forwardMessageView.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            ForwardToOneUserView forwardToOneUserView = new ForwardToOneUserView(explorerView,messageId);
            configForwardToOneUserView(forwardToOneUserView);
            panel.add(forwardToOneUserView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.ChatRoomView.getName())){
            ChatRoomView chatRoomView = (ChatRoomView) forwardMessageView.getSource();
            JPanel panel = chatRoomView.getSource();
            panel.removeAll();
            ForwardToOneUserView forwardToOneUserView = new ForwardToOneUserView(chatRoomView,messageId);
            configForwardToOneUserView(forwardToOneUserView);
            panel.add(forwardToOneUserView);
            panel.revalidate();
            panel.repaint();

        }
    }
    private void configForwardToOneUserView(ForwardToOneUserView forwardToOneUserView){
        forwardToOneUserView.setBackToListener(new BackToListener());
        forwardToOneUserView.setBackToLoginListener(new BackToLoginListener());
        forwardToOneUserView.setForwardToOneUserListener(new ForwardToOneUserListener());
    }
}
