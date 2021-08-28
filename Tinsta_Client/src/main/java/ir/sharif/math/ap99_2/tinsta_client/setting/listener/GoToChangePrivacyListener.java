package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.setting.view.ChangePrivacyView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.GoToChangePrivacyEvent;

import javax.swing.*;

public class GoToChangePrivacyListener {

    public void eventOccurred(GoToChangePrivacyEvent goToChangePrivacyEvent){
        JPanel panel = (JPanel) goToChangePrivacyEvent.getSource();
        panel.removeAll();
        ChangePrivacyView changePrivacyView = new ChangePrivacyView(panel);
        changePrivacyView.setBackToLoginListener(new BackToLoginListener());
        changePrivacyView.setGoToSettingListener(new GoToSettingListener());
        changePrivacyView.setMakePagePrivateListener(new MakePagePrivateListener());
        changePrivacyView.setMakePagePublicListener(new MakePagePublicListener());
        panel.add(changePrivacyView);
        panel.revalidate();
        panel.repaint();

    }
}
