package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeEmailEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.EmailAlreadyExist;

import javax.swing.*;

public class ChangeEmailListener {

    public void eventOccurred(ChangeEmailEvent changeEmailEvent) throws EmailAlreadyExist {
        JPanel panel = (JPanel) changeEmailEvent.getSource();
        Response response = MainController.sendEvents(changeEmailEvent);
        if (response instanceof ExceptionResponse){
           throw (EmailAlreadyExist) ((ExceptionResponse) response).getException();
        }
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed Email!"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
