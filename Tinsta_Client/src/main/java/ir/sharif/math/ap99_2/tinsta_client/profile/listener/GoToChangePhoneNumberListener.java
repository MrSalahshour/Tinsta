package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangePhoneNumberView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangePhoneNumberEvent;

import javax.swing.*;

public class GoToChangePhoneNumberListener {
    public void eventOccurred(GoToChangePhoneNumberEvent goToChangePhoneNumberEvent){
        JPanel panel = (JPanel) goToChangePhoneNumberEvent.getSource();
        panel.removeAll();
        ChangePhoneNumberView changePhoneNumberView = new ChangePhoneNumberView(panel);
        changePhoneNumberView.setBackToLoginListener(new BackToLoginListener());
        changePhoneNumberView.setChangePhoneNumberListener(new ChangePhoneNumberListener());
        changePhoneNumberView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        panel.add(changePhoneNumberView);
        panel.revalidate();
        panel.repaint();
    }
}
