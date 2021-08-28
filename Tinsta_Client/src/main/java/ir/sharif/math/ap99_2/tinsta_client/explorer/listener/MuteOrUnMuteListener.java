package ir.sharif.math.ap99_2.tinsta_client.explorer.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ShowUserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetUserProfileTextEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.MuteOrUnMuteEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class MuteOrUnMuteListener {


    public void eventOccurred(MuteOrUnMuteEvent muteOrUnMuteEvent){
        ShowUserProfileView showUserProfileView = (ShowUserProfileView) muteOrUnMuteEvent.getSource();
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this,showUserProfileView.getUserId()));
        User user = getUserResponse.getUser();
        muteOrUnMuteEvent.setUser(user);
        MainController.sendEvents(muteOrUnMuteEvent);

    }
}
