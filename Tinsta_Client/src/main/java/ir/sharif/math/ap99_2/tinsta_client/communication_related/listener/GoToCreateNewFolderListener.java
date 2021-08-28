package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.CreateUserFolderView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToCreateNewFolderEvent;

import javax.swing.*;

public class GoToCreateNewFolderListener {


    public void eventOccurred(GoToCreateNewFolderEvent goToCreateNewFolderEvent){

        JPanel panel = (JPanel) goToCreateNewFolderEvent.getSource();
        panel.removeAll();
        CreateUserFolderView createUserFolderView = new CreateUserFolderView(panel);
        createUserFolderView.setBackToLoginListener(new BackToLoginListener());
        createUserFolderView.setCreateNewFolderListener(new CreateNewFolderListener());
        createUserFolderView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        panel.add(createUserFolderView);
        panel.revalidate();
        panel.repaint();

    }
}
