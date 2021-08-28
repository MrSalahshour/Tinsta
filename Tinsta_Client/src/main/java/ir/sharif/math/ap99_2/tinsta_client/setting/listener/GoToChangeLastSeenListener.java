package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.setting.view.ChangeLastSeenView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.GoToChangeLastSeenEvent;

import javax.swing.*;

public class GoToChangeLastSeenListener {

    public void eventOccurred(GoToChangeLastSeenEvent goToChangeLastSeenEvent){
        JPanel panel = (JPanel) goToChangeLastSeenEvent.getSource();
        panel.removeAll();
        ChangeLastSeenView changeLastSeenView = new ChangeLastSeenView(panel);
        changeLastSeenView.setShowLastSeenToAllListener(new ShowLastSeenToAllListener());
        changeLastSeenView.setShowLastSeenToFollowersListener(new ShowLastSeenToFollowersListener());
        changeLastSeenView.setShowLastSeenToNoOneListener(new ShowLastSeenToNoOneListener());
        changeLastSeenView.setBackToLoginListener(new BackToLoginListener());
        changeLastSeenView.setGoToSettingListener(new GoToSettingListener());
        panel.add(changeLastSeenView);
        panel.revalidate();
        panel.repaint();

    }
}
