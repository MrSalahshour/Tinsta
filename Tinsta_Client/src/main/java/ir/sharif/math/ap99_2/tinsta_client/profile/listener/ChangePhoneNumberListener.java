package ir.sharif.math.ap99_2.tinsta_client.profile.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangePhoneNumberEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.PhoneNumberAlreadyExist;

import javax.swing.*;

public class ChangePhoneNumberListener {
    public void eventOccurred(ChangePhoneNumberEvent changePhoneNumberEvent) throws PhoneNumberAlreadyExist {

        JPanel panel = (JPanel) changePhoneNumberEvent.getSource();
        Response response = MainController.sendEvents(changePhoneNumberEvent);
        if (response instanceof ExceptionResponse){
            throw new PhoneNumberAlreadyExist(((ExceptionResponse) response).getException().getMessage());
        }
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Changed PhoneNumber!"
                ,JOptionPane.INFORMATION_MESSAGE);


    }
}
