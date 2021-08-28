package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.user_related.view.RegisterView;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToRegisterEvent;

import javax.swing.*;

public class BackToRegisterListener {

    public void eventOccurred(BackToRegisterEvent backToRegisterEvent) {
        JPanel panel = (JPanel) backToRegisterEvent.getSource();
        panel.removeAll();
        RegisterView registerView = new RegisterView(panel);
        registerView.setRegisterFormListener(new RegisterFormListener());
        registerView.setBackToLoginListener(new BackToLoginListener());
        panel.add(registerView);
        panel.revalidate();
        panel.repaint();
    }
}
