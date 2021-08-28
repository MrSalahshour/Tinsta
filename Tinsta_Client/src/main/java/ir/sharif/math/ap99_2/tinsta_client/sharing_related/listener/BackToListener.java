package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.CommunicationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;

import javax.swing.*;

public class BackToListener {

    public void eventOccurred(BackToEvent backToEvent){

        if (backToEvent.getSource().toString().equals(MainViews.HomePageView.getName())){
            HomePageView homePageView = (HomePageView) backToEvent.getSource();
            JPanel panel = homePageView.getSource();
            panel.removeAll();
            panel.add(homePageView);
            panel.revalidate();
            panel.repaint();
        }

        if (backToEvent.getSource().toString().equals(MainViews.TimeLineView.getName())){
            TimeLineView timeLineView = (TimeLineView) backToEvent.getSource();
            JPanel panel = timeLineView.getSource();
            panel.removeAll();
            panel.add(timeLineView);
            panel.revalidate();
            panel.repaint();

        }

        if (backToEvent.getSource().toString().equals(MainViews.ExplorerView.getName())){
            ExplorerView explorerView = (ExplorerView) backToEvent.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            panel.add(explorerView);
            panel.revalidate();
            panel.repaint();

        }

        if (backToEvent.getSource().toString().equals(MainViews.CommunicationMenuView.getName())){
            CommunicationMenuView communicationMenuView = (CommunicationMenuView) backToEvent.getSource();
            JPanel panel = communicationMenuView.getSource();
            panel.removeAll();
            panel.add(communicationMenuView);
            panel.revalidate();
            panel.repaint();
        }

        if (backToEvent.getSource().toString().equals(MainViews.ChatRoomView.getName())){
            ChatRoomView chatRoomView = (ChatRoomView) backToEvent.getSource();
            JPanel panel = chatRoomView.getSource();
            panel.removeAll();
            panel.add(chatRoomView);
            panel.revalidate();
            panel.repaint();
        }



    }
}
