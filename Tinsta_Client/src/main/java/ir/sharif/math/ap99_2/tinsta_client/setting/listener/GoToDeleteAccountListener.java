package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.setting.view.DeleteAccountView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.GoToDeleteAccountEvent;

import javax.swing.*;

public class GoToDeleteAccountListener {

    public void eventOccurred(GoToDeleteAccountEvent goToDeleteAccountEvent){
        JPanel panel = (JPanel) goToDeleteAccountEvent.getSource();
        panel.removeAll();
        DeleteAccountView deleteAccountView = new DeleteAccountView(panel);
        deleteAccountView.setBackToLoginListener(new BackToLoginListener());
        deleteAccountView.setDeleteAccountListener(new DeleteAccountListener());
        deleteAccountView.setGoToSettingListener(new GoToSettingListener());
        panel.add(deleteAccountView);
        panel.revalidate();
        panel.repaint();

    }
}
