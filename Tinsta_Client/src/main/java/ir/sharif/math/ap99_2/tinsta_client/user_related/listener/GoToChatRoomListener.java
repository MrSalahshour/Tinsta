package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToChatroomEvent;

import javax.swing.*;

public class GoToChatRoomListener {
    public void eventOccurred(GoToChatroomEvent goToChatroomEvent)  {
         JPanel panel;
        if (goToChatroomEvent.getSource().toString().equals(MainViews.ChatRoomView.getName())){
            ChatRoomView chatRoomView = (ChatRoomView) goToChatroomEvent.getSource();
            panel = chatRoomView.getSource();
        }
        else
            panel = (JPanel) goToChatroomEvent.getSource();

        panel.removeAll();
        ChatRoomView chatRoomView = new ChatRoomView(panel);
        chatRoomView.setBackToMenuListener(new BackToMenuListener());
        chatRoomView.setBackToLoginListener(new BackToLoginListener());
        chatRoomView.setGoToAddToGPListener(new GoToAddToGPListener());
        chatRoomView.setGoToForwardMessageListener(new GoToForwardMessageListener());
        chatRoomView.setGoToGroupChatsListener(new GoToGroupChatsListener());
        chatRoomView.setGoToPrivateChatsListener(new GoToPrivateChatsListener());
        chatRoomView.setShowGroupChatsListener(new ShowGroupChatsListener());
        chatRoomView.setShowPrivateChatsListener(new ShowPrivateChatsListener());
        chatRoomView.setShowSavedMessageListener(new ShowSavedMessageListener());
        panel.add(chatRoomView);
        panel.revalidate();
        panel.repaint();
    }
}
