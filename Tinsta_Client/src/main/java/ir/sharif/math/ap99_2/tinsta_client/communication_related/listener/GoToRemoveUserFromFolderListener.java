package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.RemoveUserFromFolderView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToRemoveUserFromFolderEvent;

import javax.swing.*;

public class GoToRemoveUserFromFolderListener {

    public void eventOccurred(GoToRemoveUserFromFolderEvent goToRemoveUserFromFolderEvent){
        JPanel panel = (JPanel) goToRemoveUserFromFolderEvent.getSource();
        panel.removeAll();
        RemoveUserFromFolderView removeUserFromFolderView = new RemoveUserFromFolderView(panel);
        removeUserFromFolderView.setRemoveUserFromFolderListener(new RemoveUserFromFolderListener());
        removeUserFromFolderView.setBackToLoginListener(new BackToLoginListener());
        removeUserFromFolderView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        panel.add(removeUserFromFolderView);
        panel.revalidate();
        panel.repaint();


    }
}
