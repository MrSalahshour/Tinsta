package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangeBirthdayView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangeBirthdayEvent;

import javax.swing.*;

public class GoToChangeBirthdayListener {
    public void eventOccurred(GoToChangeBirthdayEvent goToChangeBirthdayEvent){
        JPanel panel = (JPanel) goToChangeBirthdayEvent.getSource();
        panel.removeAll();
        ChangeBirthdayView changeBirthdayView = new ChangeBirthdayView(panel);
        changeBirthdayView.setBackToLoginListener(new BackToLoginListener());
        changeBirthdayView.setChangeBirthdayListener(new ChangeBirthdayListener());
        changeBirthdayView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        panel.add(changeBirthdayView);
        panel.revalidate();
        panel.repaint();

    }
}
