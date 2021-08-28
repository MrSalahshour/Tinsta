package ir.sharif.math.ap99_2.tinsta_client.home_page.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.CommunicationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.GoToSearchUserListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToCommunicationSecEvent;

import javax.swing.*;

public class GoToCommunicationSecListener {
    public void eventOccurred(GoToCommunicationSecEvent goToCommunicationSecEvent){
        JPanel panel = (JPanel) goToCommunicationSecEvent.getSource();
        panel.removeAll();
        CommunicationMenuView communicationMenuView = new CommunicationMenuView(panel);
        communicationMenuView.setBackToLoginListener(new BackToLoginListener());
        communicationMenuView.setGoToAddUserToFolderListener(new GoToAddUserToFolderListener());
        communicationMenuView.setGoToCreateNewFolderListener(new GoToCreateNewFolderListener());
        communicationMenuView.setGoToHomePageListener(new GoToHomePageListener());
        communicationMenuView.setGoToRemoveUserFromFolderListener(new GoToRemoveUserFromFolderListener());
        communicationMenuView.setGoToSearchUserListener(new GoToSearchUserListener());
        communicationMenuView.setShowAFolderContentListener(new ShowAFolderContentListener());
        communicationMenuView.setShowBlackListsListener(new ShowBlackListsListener());
        communicationMenuView.setShowFoldersListListener(new ShowFoldersListListener());
        communicationMenuView.setShowFollowersListener(new ShowFollowersListener());
        communicationMenuView.setShowFollowingsListener(new ShowFollowingsListener());
        communicationMenuView.setShowMutedListListener(new ShowMutedListListener());
        panel.add(communicationMenuView);
        panel.revalidate();
        panel.repaint();

    }
}
