package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.CommunicationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.view.LoginView;
import ir.sharif.math.ap99_2.tinsta_client.view.MainPanel;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.LogOutEvent;

import javax.swing.*;

public class BackToLoginListener {

    public void eventOccurred(BackToLoginEvent backToLoginEvent) {

        JPanel panel;

        String origin = backToLoginEvent.getSource().toString();

        if (origin.equals(MainViews.CommunicationMenuView.getName())){
            CommunicationMenuView communicationMenuView = (CommunicationMenuView) backToLoginEvent.getSource();
            panel = communicationMenuView.getSource();
        }
        else if (origin.equals(MainViews.TimeLineView.getName())){
            TimeLineView timeLineView = (TimeLineView) backToLoginEvent.getSource();
            panel = timeLineView.getSource();
        }
        else if (origin.equals(MainViews.ExplorerView.getName())){
            ExplorerView explorerView = (ExplorerView) backToLoginEvent.getSource();
            panel = explorerView.getSource();
        }
        else if (origin.equals(MainViews.HomePageView.getName())){
            HomePageView homePageView = (HomePageView) backToLoginEvent.getSource();
            panel = homePageView.getSource();

        }
        else if (origin.equals(MainViews.ChatRoomView.getName())){
            ChatRoomView chatRoomView = (ChatRoomView) backToLoginEvent.getSource();
            panel = chatRoomView.getSource();
        }
        else {
            panel = (MainPanel) backToLoginEvent.getSource();
        }
        if (backToLoginEvent.isConnected())
            MainController.sendEvents(new LogOutEvent());

        panel.removeAll();
        LoginView loginView = new LoginView(panel);
        loginView.setLoginFormListener(new LoginFormListener());
        loginView.setBackToRegisterListener(new BackToRegisterListener());
        panel.add(loginView);
        panel.revalidate();
        panel.repaint();


    }
}
