package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangeBioView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangeBioEvent;

import javax.swing.*;

public class GoToChangeBioListener {
    public void eventOccurred(GoToChangeBioEvent goToChangeBioEvent){
        JPanel panel = (JPanel) goToChangeBioEvent.getSource();
        panel.removeAll();
        ChangeBioView changeBioView = new ChangeBioView(panel);
        changeBioView.setBackToLoginListener(new BackToLoginListener());
        changeBioView.setChangeBioListener(new ChangeBioListener());
        changeBioView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        panel.add(changeBioView);
        panel.revalidate();
        panel.repaint();

    }
}
