package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.SetToNotShowPhoneNumberEvent;

import javax.swing.*;

public class SetToNotShowPhoneNumberListener {
    public void eventOccurred(SetToNotShowPhoneNumberEvent setToNotShowPhoneNumberEvent){
        JPanel panel = (JPanel) setToNotShowPhoneNumberEvent.getSource();
        MainController.sendEvents(setToNotShowPhoneNumberEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed PhoneNumber Privacy"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
