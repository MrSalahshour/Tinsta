package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.MakePagePublicEvent;

import javax.swing.*;

public class MakePagePublicListener {

    public void eventOccurred(MakePagePublicEvent makePagePublicEvent){
        if (!MainController.isConnected()){
            Context context = new Context();
            context.getEvents().add(makePagePublicEvent);
            String message = "Your Request will be done when you get online!";
            JOptionPane.showMessageDialog(null, message,"Changed to Public Account!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            MainController.sendEvents(makePagePublicEvent);
            JPanel panel = (JPanel) makePagePublicEvent.getSource();
            String message = "Done!";
            JOptionPane.showMessageDialog(panel, message,"Changed to Public Account!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
