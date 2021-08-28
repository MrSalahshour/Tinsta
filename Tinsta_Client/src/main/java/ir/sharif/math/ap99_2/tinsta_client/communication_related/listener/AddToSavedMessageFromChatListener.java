package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;





import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.AddToSavedMessageFromChatEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveChatRoomResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class AddToSavedMessageFromChatListener {


    public void eventOccurred(AddToSavedMessageFromChatEvent addToSavedMessageFromChatEvent){
        ChatView chatView = (ChatView) addToSavedMessageFromChatEvent.getSource();
        if (chatView.getGroupName()==null && chatView.getUserFromId()==null){
            String message = "Already in Saved Messages";
            JOptionPane.showMessageDialog(chatView, message,
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        else if (chatView.getUserFromId()!=null){
            GetUserResponse getUserResponse = (GetUserResponse) MainController
                    .sendEvents(new GetUserEvent(chatView,chatView.getUserFromId()));
            User user = getUserResponse.getUser();
            AddToSavedMessageFromChatEvent addToSavedMessageFromChatEventFinal = new AddToSavedMessageFromChatEvent(chatView);
            addToSavedMessageFromChatEventFinal.setUser(user);
            SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                    MainController.sendEvents(addToSavedMessageFromChatEventFinal);
            saveChatRoom(saveChatRoomResponse);
            handleView(chatView);
        }
        else {
            AddToSavedMessageFromChatEvent addToSavedMessageFromChatEventFinal = new AddToSavedMessageFromChatEvent(chatView);
            addToSavedMessageFromChatEventFinal.setGroupName(chatView.getGroupName());
            SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                    MainController.sendEvents(addToSavedMessageFromChatEventFinal);
            saveChatRoom(saveChatRoomResponse);
            handleView(chatView);
        }
    }
    private void handleView(ChatView chatView){
        String message = "Done!";
        JOptionPane.showMessageDialog(chatView, message,
                "Added To Save Messages!",JOptionPane.INFORMATION_MESSAGE);
    }
    private void saveChatRoom(SaveChatRoomResponse saveChatRoomResponse){
        Context context = new Context();
        context.getChatRooms().update(saveChatRoomResponse.getChatRoom());
    }

}
