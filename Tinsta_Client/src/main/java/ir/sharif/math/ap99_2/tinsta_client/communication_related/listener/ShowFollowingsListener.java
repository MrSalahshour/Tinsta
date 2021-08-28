package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowFollowingsView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowFollowingsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowFollowingsListener {

    public void eventOccurred(ShowFollowingsEvent showFollowingsEvent){
        JPanel panel = (JPanel) showFollowingsEvent.getSource();
        panel.removeAll();
        ShowFollowingsView showFollowingsView = new ShowFollowingsView(panel);
        GetStringResponse getStringResponse = (GetStringResponse) MainController.sendEvents(showFollowingsEvent);
        showFollowingsView.setBackToLoginListener(new BackToLoginListener());
        showFollowingsView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        showFollowingsView.setShowFollowingsTextArea(getStringResponse.getContent());
        panel.add(showFollowingsView);
        panel.revalidate();
        panel.repaint();

    }
}
