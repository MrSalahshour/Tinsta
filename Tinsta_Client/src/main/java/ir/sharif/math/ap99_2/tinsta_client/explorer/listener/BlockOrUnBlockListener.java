package ir.sharif.math.ap99_2.tinsta_client.explorer.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ShowUserProfileView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.BlockOrUnblockEvent;

import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


public class BlockOrUnBlockListener {

    public void eventOccurred(BlockOrUnblockEvent blockOrUnblockEvent){
        ShowUserProfileView showUserProfileView = (ShowUserProfileView) blockOrUnblockEvent.getSource();
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this,showUserProfileView.getUserId()));
        User user = getUserResponse.getUser();
        blockOrUnblockEvent.setUser(user);
        MainController.sendEvents(blockOrUnblockEvent);
    }
}
