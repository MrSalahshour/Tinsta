package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeNameEvent;

import javax.swing.*;

public class ChangeNameListener {

    public void eventOccurred(ChangeNameEvent changeNameEvent){
        JPanel panel = (JPanel) changeNameEvent.getSource();
        MainController.sendEvents(changeNameEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed LastName!"
                ,JOptionPane.INFORMATION_MESSAGE);
    }
}
