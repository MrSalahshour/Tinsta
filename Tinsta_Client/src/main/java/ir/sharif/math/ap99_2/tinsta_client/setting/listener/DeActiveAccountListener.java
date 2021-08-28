package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToRegisterListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.LoginFormListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.view.LoginView;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.DeActiveAccountEvent;

import javax.swing.*;

public class DeActiveAccountListener {

    public void eventOccurred(DeActiveAccountEvent deActiveAccountEvent){
        if (!MainController.isConnected()){
            Context context = new Context();
            context.getEvents().add(deActiveAccountEvent);
            String message = "Your Request will be done when you get online!";
            JOptionPane.showMessageDialog(null, message,"User DeActivated!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
        MainController.sendEvents(deActiveAccountEvent);
        JPanel panel = (JPanel) deActiveAccountEvent.getSource();
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"User DeActivated!"
                ,JOptionPane.INFORMATION_MESSAGE);
        LoginView loginView = new LoginView(panel);
        loginView.setLoginFormListener(new LoginFormListener());
        loginView.setBackToRegisterListener(new BackToRegisterListener());
        panel.removeAll();
        panel.add(loginView);
        panel.revalidate();
        panel.repaint();


    }
}
