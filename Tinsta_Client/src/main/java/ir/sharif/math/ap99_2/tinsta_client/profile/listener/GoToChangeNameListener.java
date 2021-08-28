package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangeNameView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangeNameEvent;

import javax.swing.*;

public class GoToChangeNameListener {
    public void eventOccurred(GoToChangeNameEvent goToChangeNameEvent){
        JPanel panel = (JPanel) goToChangeNameEvent.getSource();
        panel.removeAll();
        ChangeNameView changeNameView = new ChangeNameView(panel);
        changeNameView.setBackToLoginListener(new BackToLoginListener());
        changeNameView.setChangeNameListener(new ChangeNameListener());
        changeNameView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        panel.add(changeNameView);
        panel.revalidate();
        panel.repaint();

    }
}
