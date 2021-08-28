package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowFollowersView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowFollowersEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowFollowersListener {


    public void eventOccurred(ShowFollowersEvent showFollowersEvent){
        JPanel panel = (JPanel) showFollowersEvent.getSource();
        panel.removeAll();
        ShowFollowersView showFollowersView = new ShowFollowersView(panel);
        GetStringResponse getStringResponse = (GetStringResponse) MainController.sendEvents(showFollowersEvent);
        showFollowersView.setBackToLoginListener(new BackToLoginListener());
        showFollowersView.setGoToCommunicationSecListener(new GoToCommunicationSecListener());
        showFollowersView.setShowFollowersTextArea(getStringResponse.getContent());
        panel.add(showFollowersView);
        panel.revalidate();
        panel.repaint();
    }
}
