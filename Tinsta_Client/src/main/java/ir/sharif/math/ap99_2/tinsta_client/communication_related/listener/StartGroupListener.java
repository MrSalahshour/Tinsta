package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.GroupChatsMenuView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.IsGroupExistEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.MakeGroupEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.StartGroupEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetClientUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.UpdateUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GroupExistResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveChatRoomResponse;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupAlreadyExist;

import javax.swing.*;

public class StartGroupListener {

    public void eventOccurred(StartGroupEvent startGroupEvent) throws GroupAlreadyExist {
        GroupChatsMenuView groupChatsMenuView = (GroupChatsMenuView) startGroupEvent.getSource();
        ChatRoomView chatRoomView = (ChatRoomView) groupChatsMenuView.getSource();
        String groupName = startGroupEvent.getGroupName();
        JPanel panel = chatRoomView.getSource();

        GroupExistResponse groupExistResponse = (GroupExistResponse) MainController
                .sendEvents(new IsGroupExistEvent(this,groupName));
        if (groupExistResponse.isExist()){
            throw new GroupAlreadyExist("Group Already Exist!");
        }
        SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                MainController.sendEvents(new MakeGroupEvent(this,groupName));
        Context context = new Context();
        context.getChatRooms().update(saveChatRoomResponse.getChatRoom());
        ChatView chatView = new ChatView(chatRoomView);
        chatView.setGroupName(groupName);
        chatView.setSendTimerMessageListener(new SendTimerMessageListener());
        chatView.setSendMessageListener(new SendMessageListener());
        chatView.setSelectImageForMessageListener(new SelectImageForMessageListener());
        chatView.setPreviousMessageListener(new PreviousMessageListener());
        chatView.setNextMessageListener(new NextMessageListener());
        chatView.setEditMessageListener(new EditMessageListener());
        chatView.setDeleteMessageListener(new DeleteMessageListener());
        chatView.setEditMessageListener(new EditMessageListener());
        chatView.setAddToSavedMessageFromChatListener(new AddToSavedMessageFromChatListener());
        chatView.setMessageView(null);
        chatView.setBackToLoginListener(new BackToLoginListener());
        chatView.setBackToListener(new BackToListener());
        panel.removeAll();
        panel.add(chatView);
        panel.revalidate();
        panel.repaint();
    }
}
