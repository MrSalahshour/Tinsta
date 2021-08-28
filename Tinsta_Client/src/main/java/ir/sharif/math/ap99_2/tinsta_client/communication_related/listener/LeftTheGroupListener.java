package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;

import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.IsGroupExistEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.LeftTheGroupEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.response.GroupExistResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveChatRoomResponse;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupNotExist;

import javax.swing.*;

public class LeftTheGroupListener {
    public void eventOccurred(LeftTheGroupEvent leftTheGroupEvent) throws GroupNotExist {
        String groupName = leftTheGroupEvent.getGroupName();
        GroupExistResponse groupExistResponse = (GroupExistResponse) MainController.
                sendEvents(new IsGroupExistEvent(this,groupName));
        if (!groupExistResponse.isExist()){
            throw new GroupNotExist("Group Not Exist!");
        }
        SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                MainController.sendEvents(leftTheGroupEvent);
        Context context = new Context();
        for (ChatRoom chatroom:saveChatRoomResponse.getChatRooms()) {
            context.getChatRooms().update(chatroom);
        }
        String message = "Done!";
        JOptionPane.showMessageDialog(null, message,"You Left the "+groupName+" !!"
                ,JOptionPane.INFORMATION_MESSAGE);


    }
}
