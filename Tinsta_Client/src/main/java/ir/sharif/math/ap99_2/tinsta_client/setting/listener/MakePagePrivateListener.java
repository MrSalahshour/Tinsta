package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.MakePagePrivateEvent;

import javax.swing.*;

public class MakePagePrivateListener {

    public void eventOccurred(MakePagePrivateEvent makePagePrivateEvent){
        if (!MainController.isConnected()){
            Context context = new Context();
            context.getEvents().add(makePagePrivateEvent);
            String message = "Your Request will be done when you get online!";
            JOptionPane.showMessageDialog(null, message,"Changed to Private Account!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            MainController.sendEvents(makePagePrivateEvent);
            JPanel panel = (JPanel) makePagePrivateEvent.getSource();
            String message = "Done!";
            JOptionPane.showMessageDialog(panel, message,"Changed to Private Account!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }


    }
}
