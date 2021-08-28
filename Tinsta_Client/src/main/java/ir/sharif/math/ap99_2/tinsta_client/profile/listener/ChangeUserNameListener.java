package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeUserNameEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNameAlreadyExist;

import javax.swing.*;

public class ChangeUserNameListener {
    public void eventOccurred(ChangeUserNameEvent changeUserNameEvent) throws UserNameAlreadyExist {
        JPanel panel = (JPanel) changeUserNameEvent.getSource();
        Response response = MainController.sendEvents(changeUserNameEvent);
        if (response instanceof ExceptionResponse){
            throw (UserNameAlreadyExist)((ExceptionResponse) response).getException();
        }
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed UserName!"
                ,JOptionPane.INFORMATION_MESSAGE);

    }
}
