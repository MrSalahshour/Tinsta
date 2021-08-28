package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ShowLastSeenToAllEvent;

import javax.swing.*;

public class ShowLastSeenToAllListener {

    public void eventOccurred(ShowLastSeenToAllEvent showLastSeenToAllEvent){
        if (!MainController.isConnected()){
            Context context = new Context();
            context.getEvents().add(showLastSeenToAllEvent);
            String message = "Your Request will be done when you get online!";
            JOptionPane.showMessageDialog(null, message,"Last Seen Changed!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            MainController.sendEvents(showLastSeenToAllEvent);
            JPanel panel = (JPanel) showLastSeenToAllEvent.getSource();
            String message = "Done!";
            JOptionPane.showMessageDialog(panel, message,"Last Seen Changed!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
