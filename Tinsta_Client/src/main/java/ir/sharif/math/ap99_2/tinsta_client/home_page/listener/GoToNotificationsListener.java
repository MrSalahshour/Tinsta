package ir.sharif.math.ap99_2.tinsta_client.home_page.listener;



import ir.sharif.math.ap99_2.tinsta_client.notification.listener.ShowMyFollowRequestsListener;
import ir.sharif.math.ap99_2.tinsta_client.notification.listener.ShowSystemMessagesListener;
import ir.sharif.math.ap99_2.tinsta_client.notification.view.NotificationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToNotificationsEvent;

import javax.swing.*;

public class GoToNotificationsListener {

    public void eventOccurred(GoToNotificationsEvent goToNotificationsEvent){
        JPanel panel = (JPanel) goToNotificationsEvent.getSource();
        panel.removeAll();
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
