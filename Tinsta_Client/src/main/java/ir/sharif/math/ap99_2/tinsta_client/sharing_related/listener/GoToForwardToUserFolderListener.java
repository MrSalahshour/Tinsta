package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardMessageView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardToUserFolderView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.GoToForwardToUserFolderEvent;

import javax.swing.*;

public class GoToForwardToUserFolderListener {

    public void eventOccurred(GoToForwardToUserFolderEvent goToForwardToUserFolderEvent){
        ForwardMessageView forwardMessageView = (ForwardMessageView) goToForwardToUserFolderEvent.getSource();
        int messageId = forwardMessageView.getMessageId();

        if (forwardMessageView.getSource().toString().equals(MainViews.HomePageView.getName())){
            HomePageView homePageView = (HomePageView) forwardMessageView.getSource();
            JPanel panel = homePageView.getSource();
            panel.removeAll();
            ForwardToUserFolderView forwardToUserFolderView = new ForwardToUserFolderView(homePageView,messageId);
            configForwardToUserFolderView(forwardToUserFolderView);
            panel.add(forwardToUserFolderView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.TimeLineView.getName())){
            TimeLineView timeLineView = (TimeLineView) forwardMessageView.getSource();
            JPanel panel = timeLineView.getSource();
            panel.removeAll();
            ForwardToUserFolderView forwardToUserFolderView = new ForwardToUserFolderView(timeLineView,messageId);
            configForwardToUserFolderView(forwardToUserFolderView);
            panel.add(forwardToUserFolderView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.ExplorerView.getName())){
            ExplorerView explorerView = (ExplorerView) forwardMessageView.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            ForwardToUserFolderView forwardToUserFolderView = new ForwardToUserFolderView(explorerView,messageId);
            configForwardToUserFolderView(forwardToUserFolderView);
            panel.add(forwardToUserFolderView);
            panel.revalidate();
            panel.repaint();

        }
        if (forwardMessageView.getSource().toString().equals(MainViews.ChatRoomView.getName())){
            ChatRoomView chatRoomView = (ChatRoomView) forwardMessageView.getSource();
            JPanel panel = chatRoomView.getSource();
            panel.removeAll();
            ForwardToUserFolderView forwardToUserFolderView = new ForwardToUserFolderView(chatRoomView,messageId);
            configForwardToUserFolderView(forwardToUserFolderView);
            panel.add(forwardToUserFolderView);
            panel.revalidate();
            panel.repaint();

        }


    }
    private void configForwardToUserFolderView(ForwardToUserFolderView forwardToUserFolderView){
        forwardToUserFolderView.setBackToListener(new BackToListener());
        forwardToUserFolderView.setBackToLoginListener(new BackToLoginListener());
        forwardToUserFolderView.setForwardToUserFolderListener(new ForwardToUserFolderListener());

    }
}
