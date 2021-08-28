package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowPrivateChatsView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToChatRoomListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowPrivateChatsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowPrivateChatsListener {

    public void eventOccurred(ShowPrivateChatsEvent showPrivateChatsEvent){
        ChatRoomView chatRoomView = (ChatRoomView) showPrivateChatsEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        GetStringResponse getStringResponse = (GetStringResponse) MainController.sendEvents(showPrivateChatsEvent);
        panel.removeAll();
        ShowPrivateChatsView showPrivateChatsView = new ShowPrivateChatsView(chatRoomView);
        showPrivateChatsView.setBackToLoginListener(new BackToLoginListener());
        showPrivateChatsView.setGoToCommunicationSecListener(new GoToChatRoomListener());
        showPrivateChatsView.setShowPrivateChatsTextArea(getStringResponse.getContent());
        panel.add(showPrivateChatsView);
        panel.revalidate();
        panel.repaint();

    }
}
