package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangeProfPrivacyView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangeProfilePrivacyEvent;

import javax.swing.*;

public class GoToChangeProfilePrivacyListener {
    public void eventOccurred(GoToChangeProfilePrivacyEvent goToChangeProfilePrivacyEvent){
        JPanel panel = (JPanel) goToChangeProfilePrivacyEvent.getSource();
        panel.removeAll();
        ChangeProfPrivacyView changeProfPrivacyView = new ChangeProfPrivacyView(panel);
        changeProfPrivacyView.setBackToLoginListener(new BackToLoginListener());
        changeProfPrivacyView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        changeProfPrivacyView.setSetToNotShowBirthdayListener(new SetToNotShowBirthdayListener());
        changeProfPrivacyView.setSetToNotShowEmailListener(new SetToNotShowEmailListener());
        changeProfPrivacyView.setSetToNotShowPhoneNumberListener(new SetToNotShowPhoneNumberListener());
        changeProfPrivacyView.setSetToShowBirthdayListener(new SetToShowBirthdayListener());
        changeProfPrivacyView.setSetToShowEmailListener(new SetToShowEmailListener());
        changeProfPrivacyView.setSetToShowPhoneNumberListener(new SetToShowPhoneNumberListener());
        panel.add(changeProfPrivacyView);
        panel.revalidate();
        panel.repaint();

    }
}
