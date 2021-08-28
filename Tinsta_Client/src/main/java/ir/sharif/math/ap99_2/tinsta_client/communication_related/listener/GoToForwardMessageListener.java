package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.WriteMessageView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToForwardMessageEvent;

import javax.swing.*;


public class GoToForwardMessageListener {


    public void eventOccurred(GoToForwardMessageEvent goToForwardMessageEvent){
        ChatRoomView chatRoomView = (ChatRoomView) goToForwardMessageEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        panel.removeAll();
        WriteMessageView writeMessageView = new WriteMessageView(chatRoomView);
        writeMessageView.setBackToChatRoomListener(new BackToChatRoomListener());
        writeMessageView.setBackToLoginListener(new BackToLoginListener());
        writeMessageView.setForwardMessageListener(new ForwardMessageListener());
        writeMessageView.setSelectImageForForwardListener(new SelectImageForForwardListener());
        panel.add(writeMessageView);
        panel.revalidate();
        panel.repaint();
    }
}
