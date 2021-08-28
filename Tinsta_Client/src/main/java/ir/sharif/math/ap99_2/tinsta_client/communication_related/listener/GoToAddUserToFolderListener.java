package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.AddUserToFolderView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToAddUserToFolderEvent;

import javax.swing.*;

public class GoToAddUserToFolderListener {

    public void eventOccurred(GoToAddUserToFolderEvent goToAddUserToFolderEvent){
        JPanel panel = (JPanel) goToAddUserToFolderEvent.getSource();
        panel.removeAll();
        AddUserToFolderView addUserToFolderView = new AddUserToFolderView(panel);
        addUserToFolderView.setAddToUserFolderListener(new AddToUserFolderListener());
        addUserToFolderView.setBackToLoginListener(new BackToLoginListener());
        addUserToFolderView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        panel.add(addUserToFolderView);
        panel.revalidate();
        panel.repaint();

    }
}
