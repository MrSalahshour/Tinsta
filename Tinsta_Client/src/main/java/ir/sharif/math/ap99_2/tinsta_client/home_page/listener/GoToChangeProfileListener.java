package ir.sharif.math.ap99_2.tinsta_client.home_page.listener;



import ir.sharif.math.ap99_2.tinsta_client.profile.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ProfileMenuView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToChangeProfileEvent;

import javax.swing.*;

public class GoToChangeProfileListener {

    public void eventOccurred(GoToChangeProfileEvent goToChangeProfileEvent){
        JPanel panel = (JPanel) goToChangeProfileEvent.getSource();
        panel.removeAll();
        ProfileMenuView profileMenuView = new ProfileMenuView(panel);
        profileMenuView.setBackToLoginListener(new BackToLoginListener());
        profileMenuView.setGoToChangeBioListener(new GoToChangeBioListener());
        profileMenuView.setGoToChangeBirthdayListener(new GoToChangeBirthdayListener());
        profileMenuView.setGoToChangeEmailListener(new GoToChangeEmailListener());
        profileMenuView.setGoToChangeLastNameListener(new GoToChangeLastNameListener());
        profileMenuView.setGoToChangeNameListener(new GoToChangeNameListener());
        profileMenuView.setGoToChangePhoneNumberListener(new GoToChangePhoneNumberListener());
        profileMenuView.setGoToChangeBioListener(new GoToChangeBioListener());
        profileMenuView.setGoToChangeProfilePrivacyListener(new GoToChangeProfilePrivacyListener());
        profileMenuView.setGoToHomePageListener(new GoToHomePageListener());
        profileMenuView.setGoToChangeUserNameListener(new GoToChangeUserNameListener());
        panel.add(profileMenuView);
        panel.revalidate();
        panel.repaint();

    }
}
