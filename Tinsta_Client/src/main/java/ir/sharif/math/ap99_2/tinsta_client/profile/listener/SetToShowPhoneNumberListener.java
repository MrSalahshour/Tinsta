package ir.sharif.math.ap99_2.tinsta_client.profile.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.SetToShowPhoneNumberEvent;

import javax.swing.*;

public class SetToShowPhoneNumberListener {
    public void eventOccurred(SetToShowPhoneNumberEvent setToShowPhoneNumberEvent){
        JPanel panel = (JPanel) setToShowPhoneNumberEvent.getSource();
        MainController.sendEvents(setToShowPhoneNumberEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed PhoneNumber Privacy"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
