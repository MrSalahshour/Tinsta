package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.AddUserToGPEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.IsGroupExistEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.CantAddUserToGP;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;

import javax.swing.*;

public class AddUserToGPListener {

    public void eventOccurred(AddUserToGPEvent addUserToGPEvent) throws UserNotExist, GroupNotExist, CantAddUserToGP {
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetUserEvent(this,addUserToGPEvent.getUsername()));
        User user = getUserResponse.getUser();
        if (user == null){
            throw new UserNotExist("User Not Exist!");
        }
        String groupName = addUserToGPEvent.getGroupName();
        GroupExistResponse groupExistResponse = (GroupExistResponse) MainController.sendEvents(new IsGroupExistEvent(this,groupName));
        if (!groupExistResponse.isExist()){
            throw new GroupNotExist("Group Not Exist");
        }
        Response response = MainController.sendEvents(addUserToGPEvent);
        if (response instanceof ExceptionResponse){
            throw new CantAddUserToGP(((ExceptionResponse) response).getException().getMessage());

        }
        Context context = new Context();
        SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse) response;
        for (ChatRoom chatRoom: saveChatRoomResponse.getChatRooms()) {
            context.getChatRooms().update(chatRoom);
        }
        String message = "Done";
        JOptionPane.showMessageDialog(null, message,
                "User Added To Group!",JOptionPane.INFORMATION_MESSAGE);

    }
}
