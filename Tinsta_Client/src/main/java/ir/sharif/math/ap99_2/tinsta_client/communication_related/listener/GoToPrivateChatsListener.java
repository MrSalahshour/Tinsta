package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.PrivateChatsMenuView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToPrivateChatsEvent;

import javax.swing.*;

public class GoToPrivateChatsListener {

    public void eventOccurred(GoToPrivateChatsEvent goToPrivateChatsEvent){
        ChatRoomView chatRoomView = (ChatRoomView) goToPrivateChatsEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        PrivateChatsMenuView privateChatsMenuView = new PrivateChatsMenuView(chatRoomView);
        panel.removeAll();
        privateChatsMenuView.setBackToChatRoomListener(new BackToChatRoomListener());
        privateChatsMenuView.setBackToLoginListener(new BackToLoginListener());
        privateChatsMenuView.setGetUserIdListener(new GetUserIdListener());
        privateChatsMenuView.setStartChatListener(new StartChatListener());
        panel.add(privateChatsMenuView);
        panel.revalidate();
        panel.repaint();


    }
}
