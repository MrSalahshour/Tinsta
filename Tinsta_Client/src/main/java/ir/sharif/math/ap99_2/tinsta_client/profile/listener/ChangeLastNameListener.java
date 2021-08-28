package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeLastNameEvent;

import javax.swing.*;

public class ChangeLastNameListener {

    public void eventOccurred(ChangeLastNameEvent changeLastNameEvent){
        JPanel panel = (JPanel) changeLastNameEvent.getSource();
        MainController.sendEvents(changeLastNameEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed LastName!"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
