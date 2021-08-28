package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangeLastNameView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangeLastNameEvent;

import javax.swing.*;

public class GoToChangeLastNameListener {
    public void eventOccurred(GoToChangeLastNameEvent goToChangeLastNameEvent){
        JPanel panel = (JPanel) goToChangeLastNameEvent.getSource();
        panel.removeAll();
        ChangeLastNameView changeLastNameView = new ChangeLastNameView(panel);
        changeLastNameView.setBackToLoginListener(new BackToLoginListener());
        changeLastNameView.setChangeLastNameListener(new ChangeLastNameListener());
        changeLastNameView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        panel.add(changeLastNameView);
        panel.revalidate();
        panel.repaint();
    }
}
