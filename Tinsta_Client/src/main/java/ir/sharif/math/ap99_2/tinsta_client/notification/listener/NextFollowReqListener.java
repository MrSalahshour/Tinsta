package ir.sharif.math.ap99_2.tinsta_client.notification.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.notification.view.ShowMyFollowRequestView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetUserProfileTextEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.GetCurrentFollowReqIdEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.HasNextFollowReqEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.NextFollowReqEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class NextFollowReqListener {

    public void eventOccurred(NextFollowReqEvent nextFollowReqEvent){
        ShowMyFollowRequestView showMyFollowRequestView = (ShowMyFollowRequestView) nextFollowReqEvent.getSource();
        HasNextFollowReqResponse hasNextFollowReqResponse = (HasNextFollowReqResponse) MainController
                .sendEvents(new HasNextFollowReqEvent(this));
        if (!hasNextFollowReqResponse.isHasNext()){
            String message = "You Don't Have Next Follow Request !";
            JOptionPane.showMessageDialog(showMyFollowRequestView, message,"Error"
                    ,JOptionPane.ERROR_MESSAGE);
            return;
        }
        MainController.sendEvents(nextFollowReqEvent);
        GetIntegerResponse getIntegerResponse = (GetIntegerResponse) MainController.
                sendEvents(new GetCurrentFollowReqIdEvent(this));
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this,getIntegerResponse.getInteger()));
        User user = getUserResponse.getUser();
        GetStringResponse getStringResponse = (GetStringResponse) MainController
                .sendEvents(new GetUserProfileTextEvent(this,user));
        UserProfileView userProfileView = new UserProfileView(user.getId());
        userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
        userProfileView.setImageProfilePath(user.getProfileImagePath());
        showMyFollowRequestView.remove(showMyFollowRequestView.getUserProfileView());
        showMyFollowRequestView.setUserProfileView(userProfileView);
        userProfileView.configElements();
        showMyFollowRequestView.configElements();
        showMyFollowRequestView.revalidate();
        showMyFollowRequestView.repaint();

    }
}
