package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.AddToUserFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.CantAddToUserFolder;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;

import javax.swing.*;

public class AddToUserFolderListener {

    public void eventOccurred(AddToUserFolderEvent addToUserFolderEvent) throws
            UserFolderNotExist, UserNotExist, CantAddToUserFolder {
        JPanel panel = (JPanel) addToUserFolderEvent.getSource();
        Response response = MainController.sendEvents(addToUserFolderEvent);
        if (response instanceof ExceptionResponse){
            ExceptionResponse exceptionResponse = (ExceptionResponse) response;
            if (exceptionResponse.getException() instanceof UserFolderNotExist){
                throw new UserFolderNotExist(exceptionResponse.getException().getMessage());
            }
            else if (exceptionResponse.getException() instanceof UserNotExist){
                throw new UserNotExist(exceptionResponse.getException().getMessage());
            }
            else {
                throw new CantAddToUserFolder(exceptionResponse.getException().getMessage());
            }
        }
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,
                "Added User To Folder",JOptionPane.INFORMATION_MESSAGE);
    }
}
