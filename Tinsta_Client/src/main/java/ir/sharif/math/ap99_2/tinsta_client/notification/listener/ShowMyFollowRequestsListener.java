package ir.sharif.math.ap99_2.tinsta_client.notification.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToNotificationsListener;
import ir.sharif.math.ap99_2.tinsta_client.notification.view.ShowMyFollowRequestView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetUserProfileTextEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.GetCurrentFollowReqIdEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.ShowMyFollowRequestsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.DontHaveFollowReqResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetIntegerResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class ShowMyFollowRequestsListener {
    public void eventOccurred(ShowMyFollowRequestsEvent showMyFollowRequestsEvent){
        JPanel panel = (JPanel) showMyFollowRequestsEvent.getSource();
        DontHaveFollowReqResponse dontHaveFollowReqResponse = (DontHaveFollowReqResponse)
                MainController.sendEvents(showMyFollowRequestsEvent);
        if (dontHaveFollowReqResponse.isDontHave()){
            String message = "You Don't Have Any Follow Requests !";
            JOptionPane.showMessageDialog(panel, message,"Error"
                    ,JOptionPane.ERROR_MESSAGE);
            return;
        }
        panel.removeAll();
        GetIntegerResponse getIntegerResponse = (GetIntegerResponse) MainController.
                sendEvents(new GetCurrentFollowReqIdEvent(this));
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this,getIntegerResponse.getInteger()));
        User user = getUserResponse.getUser();
        ShowMyFollowRequestView showMyFollowRequestView = new ShowMyFollowRequestView(panel);
        GetStringResponse getStringResponse = (GetStringResponse) MainController
                .sendEvents(new GetUserProfileTextEvent(this,user));
        UserProfileView userProfileView = new UserProfileView(user.getId());
        userProfileView.setImageProfilePath(user.getProfileImagePath());
        userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
        userProfileView.configElements();
        showMyFollowRequestView.setUserProfileView(userProfileView);
        showMyFollowRequestView.setAcceptFollowReqListener(new AcceptFollowReqListener());
        showMyFollowRequestView.setBackToLoginListener(new BackToLoginListener());
        showMyFollowRequestView.setGoToNotificationsListener(new GoToNotificationsListener());
        showMyFollowRequestView.setNextFollowReqListener(new NextFollowReqListener());
        showMyFollowRequestView.setPreviousFollowReqListener(new PreviousFollowReqListener());
        showMyFollowRequestView.setRejectFollowReqListener(new RejectFollowReqListener());
        showMyFollowRequestView.setRejectFollowReqWithNotifyListener(new RejectFollowReqWithNotifyListener());
        showMyFollowRequestView.configElements();
        panel.add(showMyFollowRequestView);
        panel.revalidate();
        panel.repaint();

    }
}
