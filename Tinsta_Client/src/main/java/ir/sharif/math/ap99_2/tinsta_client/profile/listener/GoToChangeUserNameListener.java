package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.view.ChangeUserNameView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.GoToChangeUserNameEvent;

import javax.swing.*;

public class GoToChangeUserNameListener {
    public void eventOccurred(GoToChangeUserNameEvent goToChangeUserNameEvent){
        JPanel panel = (JPanel) goToChangeUserNameEvent.getSource();
        panel.removeAll();
        ChangeUserNameView changeUserNameView = new ChangeUserNameView(panel);
        changeUserNameView.setBackToLoginListener(new BackToLoginListener());
        changeUserNameView.setChangeUserNameListener(new ChangeUserNameListener());
        changeUserNameView.setGoToChangeProfileListener(new GoToChangeProfileListener());
        panel.add(changeUserNameView);
        panel.revalidate();
        panel.repaint();

    }
}
