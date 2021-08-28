package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeBirthdayEvent;

import javax.swing.*;

public class ChangeBirthdayListener {

    public void eventOccurred(ChangeBirthdayEvent changeBirthdayEvent){
        JPanel panel = (JPanel) changeBirthdayEvent.getSource();
        MainController.sendEvents(changeBirthdayEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed Birthday!"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
