package ir.sharif.math.ap99_2.tinsta_client.explorer.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.CommunicationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.SearchUserView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GoToSearchUserEvent;

import javax.swing.*;

public class GoToSearchUserListener {

    public void eventOccurred(GoToSearchUserEvent goToSearchUserEvent){
        if (goToSearchUserEvent.getSource().toString().equals("ExplorerView")){
            ExplorerView explorerView = (ExplorerView) goToSearchUserEvent.getSource();
            JPanel panel = explorerView.getSource();
            panel.removeAll();
            SearchUserView searchUserView = new SearchUserView(explorerView);
            configSearchUserView(searchUserView);
            panel.add(searchUserView);
            panel.revalidate();
            panel.repaint();
        }
        if (goToSearchUserEvent.getSource().toString().equals("CommunicationMenuView")){
            CommunicationMenuView communicationMenuView = (CommunicationMenuView) goToSearchUserEvent.getSource();
            JPanel panel = communicationMenuView.getSource();
            panel.removeAll();
            SearchUserView searchUserView = new SearchUserView(communicationMenuView);
            configSearchUserView(searchUserView);
            panel.add(searchUserView);
            panel.revalidate();
            panel.repaint();
        }
    }
    private void configSearchUserView(SearchUserView searchUserView){
        searchUserView.setBackToListener(new BackToListener());
        searchUserView.setBackToLoginListener(new BackToLoginListener());
        searchUserView.setSearchUserListener(new SearchUserListener());
    }
}
