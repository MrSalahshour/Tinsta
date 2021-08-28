package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowBlackListView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowBlackListsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowBlackListsListener {

    public void eventOccurred(ShowBlackListsEvent showBlackListsEvent){
        JPanel panel = (JPanel) showBlackListsEvent.getSource();
        panel.removeAll();
        ShowBlackListView showBlackListView = new ShowBlackListView(panel);
        GetStringResponse getStringResponse = (GetStringResponse) MainController.sendEvents(showBlackListsEvent);
        showBlackListView.setBackToLoginListener(new BackToLoginListener());
        showBlackListView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        showBlackListView.setShowBlockedTextArea(getStringResponse.getContent());
        panel.add(showBlackListView);
        panel.revalidate();
        panel.repaint();
    }
}
