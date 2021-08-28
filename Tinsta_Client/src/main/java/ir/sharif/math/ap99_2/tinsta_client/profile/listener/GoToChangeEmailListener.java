package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangeEmailView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangeEmailEvent;

import javax.swing.*;

public class GoToChangeEmailListener {
    public void eventOccurred(GoToChangeEmailEvent goToChangeEmailEvent){
        JPanel panel = (JPanel) goToChangeEmailEvent.getSource();
        panel.removeAll();
        ChangeEmailView changeEmailView = new ChangeEmailView(panel);
        changeEmailView.setBackToLoginListener(new BackToLoginListener());
        changeEmailView.setChangeEmailListener(new ChangeEmailListener());
        changeEmailView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        panel.add(changeEmailView);
        panel.revalidate();
        panel.repaint();

    }
}
