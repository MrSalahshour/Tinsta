package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeBioEvent;

import javax.swing.*;

public class ChangeBioListener {

    public void eventOccurred(ChangeBioEvent changeBioEvent){
        JPanel panel = (JPanel) changeBioEvent.getSource();
        MainController.sendEvents(changeBioEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed Biography!"
                ,JOptionPane.INFORMATION_MESSAGE);
    }
}
