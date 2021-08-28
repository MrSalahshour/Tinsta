package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardMessageView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ForwardMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;

import javax.swing.*;

public class ForwardMessageListener {

    public void eventOccurred(ForwardMessageEvent forwardMessageEvent){
        ChatRoomView chatRoomView = (ChatRoomView) forwardMessageEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController.sendEvents(forwardMessageEvent);
        Message message = getMessageResponse.getMessage();
        panel.removeAll();
        ForwardMessageView forwardMessageView = new ForwardMessageView(chatRoomView,message.getId());
        forwardMessageView.setBackToListener(new BackToListener());
        forwardMessageView.setBackToLoginListener(new BackToLoginListener());
        forwardMessageView.setGoToForwardToAllUsersListener(new GoToForwardToAllUsersListener());
        forwardMessageView.setGoToForwardToMultiUsersListener(new GoToForwardToMultiUsersListener());
        forwardMessageView.setGoToForwardToOneUserListener(new GoToForwardToOneUserListener());
        forwardMessageView.setGoToForwardToUserFolderListener(new GoToForwardToUserFolderListener());
        panel.add(forwardMessageView);
        panel.revalidate();
        panel.repaint();


    }
}
