package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowFoldersListView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowFoldersListEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowFoldersListListener {

    public void eventOccurred(ShowFoldersListEvent showFoldersListEvent){
        JPanel panel = (JPanel) showFoldersListEvent.getSource();
        panel.removeAll();
        ShowFoldersListView showFoldersListView = new ShowFoldersListView(panel);
        GetStringResponse getStringResponse = (GetStringResponse) MainController.sendEvents(showFoldersListEvent);
        showFoldersListView.setBackToLoginListener(new BackToLoginListener());
        showFoldersListView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        showFoldersListView.setShowFoldersTextArea(getStringResponse.getContent());
        panel.add(showFoldersListView);
        panel.revalidate();
        panel.repaint();

    }
}
