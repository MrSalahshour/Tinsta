package ir.sharif.math.ap99_2.tinsta_client.notification.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.notification.view.NotificationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.AcceptFollowReqEvent;

import javax.swing.*;

public class AcceptFollowReqListener {

    public void eventOccurred(AcceptFollowReqEvent acceptFollowReqEvent){
        JPanel panel = (JPanel) acceptFollowReqEvent.getSource();
        panel.removeAll();
        MainController.sendEvents(acceptFollowReqEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Request Accepted!"
                ,JOptionPane.INFORMATION_MESSAGE);
        NotificationMenuView notificationMenuView = new NotificationMenuView(panel);
        notificationMenuView.setBackToLoginListener(new BackToLoginListener());
        notificationMenuView.setGoToHomePageListener(new GoToHomePageListener());
        notificationMenuView.setShowMyFollowRequestsListener(new ShowMyFollowRequestsListener());
        notificationMenuView.setShowSystemMessagesListener(new ShowSystemMessagesListener());
        panel.add(notificationMenuView);
        panel.revalidate();
        panel.repaint();
    }
}
