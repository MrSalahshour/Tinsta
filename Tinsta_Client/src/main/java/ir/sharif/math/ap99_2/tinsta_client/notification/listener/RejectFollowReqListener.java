package ir.sharif.math.ap99_2.tinsta_client.notification.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.notification.view.NotificationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.RejectFollowReqEvent;

import javax.swing.*;

public class RejectFollowReqListener {
    public void eventOccurred(RejectFollowReqEvent rejectFollowReqEvent){
        JPanel panel = (JPanel) rejectFollowReqEvent.getSource();
        panel.removeAll();
        MainController.sendEvents(rejectFollowReqEvent);
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"Request Rejected!"
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
