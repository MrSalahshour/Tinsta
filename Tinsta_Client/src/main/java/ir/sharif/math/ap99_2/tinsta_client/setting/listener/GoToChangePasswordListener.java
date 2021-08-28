package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.setting.view.ChangePasswordView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.GoToChangePasswordEvent;

import javax.swing.*;

public class GoToChangePasswordListener {

    public void eventOccurred(GoToChangePasswordEvent goToChangePasswordEvent){
        JPanel panel = (JPanel) goToChangePasswordEvent.getSource();
        panel.removeAll();
        ChangePasswordView changeLastSeenView = new ChangePasswordView(panel);
        changeLastSeenView.setBackToLoginListener(new BackToLoginListener());
        changeLastSeenView.setChangePasswordListener(new ChangePasswordListener());
        changeLastSeenView.setGoToSettingListener(new GoToSettingListener());
        panel.add(changeLastSeenView);
        panel.revalidate();
        panel.repaint();

    }
}
