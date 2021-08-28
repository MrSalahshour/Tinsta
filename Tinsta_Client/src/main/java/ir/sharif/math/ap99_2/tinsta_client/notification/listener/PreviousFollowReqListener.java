package ir.sharif.math.ap99_2.tinsta_client.notification.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.notification.view.ShowMyFollowRequestView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetUserProfileTextEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.GetCurrentFollowReqIdEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.HasPreviousFollowReqEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.PreviousFollowReqEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetIntegerResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.HasPreviousFollowReqResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class PreviousFollowReqListener {
    public void eventOccurred(PreviousFollowReqEvent previousFollowReqEvent){
        ShowMyFollowRequestView showMyFollowRequestView = (ShowMyFollowRequestView) previousFollowReqEvent.getSource();
        HasPreviousFollowReqResponse hasPreviousFollowReqResponse = (HasPreviousFollowReqResponse)
                MainController.sendEvents(new HasPreviousFollowReqEvent(this));
        if (!hasPreviousFollowReqResponse.isHasPrevious()){
            String message = "You Don't Have Previous Follow Request !";
            JOptionPane.showMessageDialog(showMyFollowRequestView, message,"Error"
                    ,JOptionPane.ERROR_MESSAGE);
            return;
        }
        MainController.sendEvents(previousFollowReqEvent);
        GetIntegerResponse getIntegerResponse = (GetIntegerResponse) MainController
                .sendEvents(new GetCurrentFollowReqIdEvent(this));
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
