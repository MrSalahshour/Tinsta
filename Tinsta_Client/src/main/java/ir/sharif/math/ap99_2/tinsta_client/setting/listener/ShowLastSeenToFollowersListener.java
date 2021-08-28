package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ShowLastSeenToFollowersEvent;

import javax.swing.*;

public class ShowLastSeenToFollowersListener {

    public void eventOccurred(ShowLastSeenToFollowersEvent showLastSeenToFollowersEvent){
        if (!MainController.isConnected()){
            Context context = new Context();
            context.getEvents().add(showLastSeenToFollowersEvent);
            String message = "Your Request will be done when you get online!";
            JOptionPane.showMessageDialog(null, message,"Last Seen Changed!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            MainController.sendEvents(showLastSeenToFollowersEvent);
            JPanel panel = (JPanel) showLastSeenToFollowersEvent.getSource();
            String message = "Done!";
            JOptionPane.showMessageDialog(panel, message,"Last Seen Changed!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
