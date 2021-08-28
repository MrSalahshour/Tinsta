package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.SetToShowBirthdayEvent;

import javax.swing.*;

public class SetToShowBirthdayListener {
    public void eventOccurred(SetToShowBirthdayEvent setToShowBirthdayEvent){
        JPanel panel = (JPanel) setToShowBirthdayEvent.getSource();
        MainController.sendEvents(setToShowBirthdayEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed Birthday Privacy",JOptionPane.INFORMATION_MESSAGE);

    }
}
