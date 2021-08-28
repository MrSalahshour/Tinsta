package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.SetToShowEmailEvent;

import javax.swing.*;

public class SetToShowEmailListener {
    public void eventOccurred(SetToShowEmailEvent setToShowEmailEvent){
        JPanel panel = (JPanel) setToShowEmailEvent.getSource();
        MainController.sendEvents(setToShowEmailEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed Email Privacy"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
