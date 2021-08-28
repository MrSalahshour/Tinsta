package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.PrivateChatsMenuView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GetUserIdEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;

public class GetUserIdListener {
    public void eventOccurred(GetUserIdEvent getUserIdEvent) throws UserNotExist {
        PrivateChatsMenuView privateChatsMenuView = (PrivateChatsMenuView) getUserIdEvent.getSource();
        if (!MainController.isConnected()){
            Context context = new Context();
            UserName userName = context.getUserNames().getByUserNameText(getUserIdEvent.getUsername());
            if (userName==null)
                throw new UserNotExist("User Not Exist!");
            else {
                privateChatsMenuView.setUserId(userName.getUserId());
            }

        }
        else {
            GetUserResponse getUserResponse = (GetUserResponse) MainController
                    .sendEvents(new GetUserEvent(this,getUserIdEvent.getUsername()));
            User user = getUserResponse.getUser();
            if (user==null){
                throw new UserNotExist("User Not Exist!");
            }
            privateChatsMenuView.setUserId(user.getId());
        }
    }
}
