package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowAFolderContentView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowAFolderContentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;

import javax.swing.*;

public class ShowAFolderContentListener {

    public void eventOccurred(ShowAFolderContentEvent showAFolderContentEvent) throws UserFolderNotExist {
        JPanel panel = (JPanel) showAFolderContentEvent.getSource();
        String folderName;
        folderName = JOptionPane.showInputDialog(panel, "Enter Your Name: ");
        if (folderName == null)
            return;
        showAFolderContentEvent.setFolderName(folderName);
        Response response = MainController.sendEvents(showAFolderContentEvent);
        if (response instanceof ExceptionResponse){
            throw new UserFolderNotExist(((ExceptionResponse) response).getException().getMessage());
        }
        GetStringResponse getStringResponse = (GetStringResponse) response;
        panel.removeAll();
        ShowAFolderContentView showAFolderContentView = new ShowAFolderContentView(panel);
        showAFolderContentView.setBackToLoginListener(new BackToLoginListener());
        showAFolderContentView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        showAFolderContentView.setShowAFolderContentTextArea(getStringResponse.getContent());
        panel.add(showAFolderContentView);
        panel.revalidate();
        panel.repaint();
    }
}
