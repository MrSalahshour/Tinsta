package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.RemoveUserFromFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExistInFolder;

import javax.swing.*;

public class RemoveUserFromFolderListener {
    public void eventOccurred(RemoveUserFromFolderEvent removeUserFromFolderEvent)
            throws UserFolderNotExist, UserNotExist, UserNotExistInFolder {
        JPanel panel = (JPanel) removeUserFromFolderEvent.getSource();
        Response response = MainController.sendEvents(removeUserFromFolderEvent);
        if (response instanceof ExceptionResponse){
            if (((ExceptionResponse) response).getException() instanceof UserFolderNotExist)
                throw (UserFolderNotExist) ((ExceptionResponse) response).getException();
            if (((ExceptionResponse) response).getException() instanceof UserNotExist)
                throw (UserNotExist) ((ExceptionResponse) response).getException();
            if (((ExceptionResponse) response).getException() instanceof UserNotExistInFolder)
                throw (UserNotExistInFolder) ((ExceptionResponse) response).getException();
        }
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,
                "Removed User From Folder",JOptionPane.INFORMATION_MESSAGE);

    }
}
