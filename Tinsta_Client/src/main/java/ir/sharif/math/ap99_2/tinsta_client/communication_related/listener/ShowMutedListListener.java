package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowMutedListView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowMutedListEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowMutedListListener {

    public void eventOccurred(ShowMutedListEvent showMutedListEvent){
        JPanel panel = (JPanel) showMutedListEvent.getSource();
        panel.removeAll();
        ShowMutedListView showMutedListView = new ShowMutedListView(panel);
        GetStringResponse getStringResponse = (GetStringResponse) MainController.sendEvents(showMutedListEvent);
        showMutedListView.setBackToLoginListener(new BackToLoginListener());
        showMutedListView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        showMutedListView.setShowMutedTextArea(getStringResponse.getContent());
        panel.add(showMutedListView);
        panel.revalidate();
        panel.repaint();

    }
}
