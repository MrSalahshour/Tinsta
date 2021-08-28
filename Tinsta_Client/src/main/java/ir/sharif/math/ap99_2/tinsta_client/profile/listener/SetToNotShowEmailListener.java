package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.SetToNotShowEmailEvent;

import javax.swing.*;

public class SetToNotShowEmailListener {
    public void eventOccurred(SetToNotShowEmailEvent setToNotShowEmailEvent){
        JPanel panel = (JPanel) setToNotShowEmailEvent.getSource();
        MainController.sendEvents(setToNotShowEmailEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed Email Privacy"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
