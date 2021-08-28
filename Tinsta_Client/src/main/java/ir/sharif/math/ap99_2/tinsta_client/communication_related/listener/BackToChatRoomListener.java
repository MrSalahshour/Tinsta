package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.BackToChatRoomEvent;

import javax.swing.*;

public class BackToChatRoomListener {

    public void eventOccurred(BackToChatRoomEvent backToChatRoomEvent){
        ChatRoomView chatRoomView = (ChatRoomView) backToChatRoomEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        panel.removeAll();
        panel.add(chatRoomView);
        panel.revalidate();
        panel.repaint();
    }
}
