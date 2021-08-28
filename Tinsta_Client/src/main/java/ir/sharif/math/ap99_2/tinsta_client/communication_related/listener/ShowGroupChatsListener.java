package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ShowGroupChatsView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToChatRoomListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowGroupChatsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;

import javax.swing.*;

public class ShowGroupChatsListener {

    public void eventOccurred(ShowGroupChatsEvent showGroupChatsEvent){
        ChatRoomView chatRoomView = (ChatRoomView) showGroupChatsEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        GetStringResponse getStringResponse = (GetStringResponse) MainController.sendEvents(showGroupChatsEvent);
        panel.removeAll();
        ShowGroupChatsView showGroupChatsView = new ShowGroupChatsView(chatRoomView);
        showGroupChatsView.setBackToLoginListener(new BackToLoginListener());
        showGroupChatsView.setGoToCommunicationSecListener(new GoToChatRoomListener());
        showGroupChatsView.setShowGroupChatsTextArea(getStringResponse.getContent());
        panel.add(showGroupChatsView);
        panel.revalidate();
        panel.repaint();

    }
}
