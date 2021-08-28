package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.GroupChatsMenuView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToGroupChatsEvent;

import javax.swing.*;

public class GoToGroupChatsListener {

    public void eventOccurred(GoToGroupChatsEvent goToGroupChatsEvent){
        ChatRoomView chatRoomView = (ChatRoomView) goToGroupChatsEvent.getSource();
        JPanel panel = chatRoomView.getSource();
        GroupChatsMenuView groupChatsMenuView = new GroupChatsMenuView(chatRoomView);
        panel.removeAll();
        groupChatsMenuView.setBackToChatRoomListener(new BackToChatRoomListener());
        groupChatsMenuView.setBackToLoginListener(new BackToLoginListener());
        groupChatsMenuView.setGoToGroupListener(new GoToGroupListener());
        groupChatsMenuView.setStartGroupListener(new StartGroupListener());
        groupChatsMenuView.setLeftTheGroupListener(new LeftTheGroupListener());
        panel.add(groupChatsMenuView);
        panel.revalidate();
        panel.repaint();

    }
}
