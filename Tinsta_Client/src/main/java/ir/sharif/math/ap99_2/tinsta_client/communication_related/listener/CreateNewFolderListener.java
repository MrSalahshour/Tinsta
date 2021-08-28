package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.CreateNewFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderAlreadyExist;

import javax.swing.*;

public class CreateNewFolderListener {

    public void eventOccurred(CreateNewFolderEvent createNewFolderEvent) throws UserFolderAlreadyExist {
        JPanel panel = (JPanel) createNewFolderEvent.getSource();
        Response response = MainController.sendEvents(createNewFolderEvent);
        if (response instanceof ExceptionResponse){
            throw (UserFolderAlreadyExist) ((ExceptionResponse) response).getException();
        }
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,
                "Created Folder",JOptionPane.INFORMATION_MESSAGE);

    }
}
