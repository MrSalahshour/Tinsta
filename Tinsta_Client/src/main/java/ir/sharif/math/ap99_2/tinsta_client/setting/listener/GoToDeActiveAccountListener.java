package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.setting.view.DeActiveAccountView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.GoToDeActiveAccountEvent;

import javax.swing.*;

public class GoToDeActiveAccountListener {

    public void eventOccurred(GoToDeActiveAccountEvent goToDeActiveAccountEvent){
        JPanel panel = (JPanel) goToDeActiveAccountEvent.getSource();
        panel.removeAll();
        DeActiveAccountView deActiveAccountView = new DeActiveAccountView(panel);
        deActiveAccountView.setBackToLoginListener(new BackToLoginListener());
        deActiveAccountView.setDeActiveAccountListener(new DeActiveAccountListener());
        deActiveAccountView.setGoToSettingListener(new GoToSettingListener());
        panel.add(deActiveAccountView);
        panel.revalidate();
        panel.repaint();

    }
}
