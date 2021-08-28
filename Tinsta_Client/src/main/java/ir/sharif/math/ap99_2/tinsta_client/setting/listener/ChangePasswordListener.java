package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ChangePasswordEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.WrongPassword;

import javax.swing.*;

public class ChangePasswordListener {


    public void eventOccurred(ChangePasswordEvent changePasswordEvent) throws WrongPassword {
        if (!MainController.isConnected()){
            Context context = new Context();
            context.getEvents().add(changePasswordEvent);
            JPanel panel = (JPanel) changePasswordEvent.getSource();
            String message = "Your Request will be done when you get online!";
            JOptionPane.showMessageDialog(panel, message,"Password Changed!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            Response response = MainController.sendEvents(changePasswordEvent);
            if (response instanceof ExceptionResponse){
                throw (WrongPassword)((ExceptionResponse) response).getException();
            }
            JPanel panel = (JPanel) changePasswordEvent.getSource();
            String message = "Password Changed Successfully!";
            JOptionPane.showMessageDialog(panel, message,"Password Changed!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
