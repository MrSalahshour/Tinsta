package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.view.MenuView;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.BackToMenuEvent;

import javax.swing.*;

public class BackToMenuListener {

    public void eventOccurred(BackToMenuEvent backToMenuEvent)  {

        JPanel panel = (JPanel) backToMenuEvent.getSource();
        panel.removeAll();
        MenuView menuView = new MenuView(panel);
        menuView.setBackToLoginListener(new BackToLoginListener());
        menuView.setGoToChatRoomListener(new GoToChatRoomListener());
        menuView.setGoToExplorerListener(new GoToExplorerListener());
        menuView.setGoToHomePageListener(new GoToHomePageListener());
        menuView.setGoToSettingListener(new GoToSettingListener());
        menuView.setGoToTimeLineListener(new GoToTimeLineListener());
        panel.add(menuView);
        panel.revalidate();
        panel.repaint();
    }
}
