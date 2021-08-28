package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.AddUserToGPView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToAddToGPEvent;

import javax.swing.*;

public class GoToAddToGPListener {

    public void eventOccurred(GoToAddToGPEvent goToAddToGPEvent){
        ChatRoomView chatRoomView = (ChatRoomView) goToAddToGPEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        panel.removeAll();
        AddUserToGPView addUserToGPView = new AddUserToGPView(chatRoomView);
        addUserToGPView.setAddUserToGPListener(new AddUserToGPListener());
        addUserToGPView.setBackToChatRoomListener(new BackToChatRoomListener());
        addUserToGPView.setBackToLoginListener(new BackToLoginListener());
        panel.add(addUserToGPView);
        panel.revalidate();
        panel.repaint();

    }
}
