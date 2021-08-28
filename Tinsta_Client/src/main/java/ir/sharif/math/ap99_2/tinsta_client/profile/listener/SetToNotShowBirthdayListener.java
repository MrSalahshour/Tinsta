package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.SetToNotShowBirthdayEvent;

import javax.swing.*;

public class SetToNotShowBirthdayListener {
    public void eventOccurred(SetToNotShowBirthdayEvent setToNotShowBirthdayEvent){
        JPanel panel = (JPanel) setToNotShowBirthdayEvent.getSource();
        MainController.sendEvents(setToNotShowBirthdayEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed Birthday Privacy",JOptionPane.INFORMATION_MESSAGE);

    }
}
