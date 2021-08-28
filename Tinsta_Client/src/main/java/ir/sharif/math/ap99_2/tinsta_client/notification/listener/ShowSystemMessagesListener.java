package ir.sharif.math.ap99_2.tinsta_client.notification.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToNotificationsListener;
import ir.sharif.math.ap99_2.tinsta_client.notification.view.ShowSystemMessagesView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.ShowSystemMessagesEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowSystemMessagesListener {
    public void eventOccurred(ShowSystemMessagesEvent showSystemMessagesEvent){
        JPanel panel = (JPanel) showSystemMessagesEvent.getSource();
        panel.removeAll();
        ShowSystemMessagesView showSystemMessagesView = new ShowSystemMessagesView(panel);
        GetStringResponse getStringResponse = (GetStringResponse) MainController
                .sendEvents(showSystemMessagesEvent);
        showSystemMessagesView.setSystemMessagesTextArea(getStringResponse.getContent());
        showSystemMessagesView.setBackToLoginListener(new BackToLoginListener());
        showSystemMessagesView.setGoToNotificationsListener(new GoToNotificationsListener());
        panel.add(showSystemMessagesView);
        panel.revalidate();
        panel.repaint();

    }
}
