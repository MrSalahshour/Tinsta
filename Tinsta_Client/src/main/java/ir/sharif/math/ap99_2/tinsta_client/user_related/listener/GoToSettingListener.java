package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.setting.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.setting.view.SettingView;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToSettingEvent;

import javax.swing.*;

public class GoToSettingListener {

    public void eventOccurred(GoToSettingEvent goToSettingEvent)  {

        JPanel panel = (JPanel) goToSettingEvent.getSource();
        panel.removeAll();
        SettingView settingView = new SettingView(panel);
        settingView.setBackToLoginListener(new BackToLoginListener());
        settingView.setBackToMenuListener(new BackToMenuListener());
        settingView.setGoToChangeLastSeenListener(new GoToChangeLastSeenListener());
        settingView.setGoToChangePasswordListener(new GoToChangePasswordListener());
        settingView.setGoToChangePrivacyListener(new GoToChangePrivacyListener());
        settingView.setGoToDeActiveAccountListener(new GoToDeActiveAccountListener());
        settingView.setGoToDeleteAccountListener(new GoToDeleteAccountListener());
        settingView.setShowSettingStatusListener(new ShowSettingStatusListener());
        panel.add(settingView);
        panel.revalidate();
        panel.repaint();
    }
}
