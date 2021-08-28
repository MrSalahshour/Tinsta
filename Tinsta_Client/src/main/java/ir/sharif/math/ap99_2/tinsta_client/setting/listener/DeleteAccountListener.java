package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToRegisterListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.LoginFormListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.view.LoginView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.DeleteAccountEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.WrongPassword;

import javax.swing.*;

public class DeleteAccountListener {
    Context context = new Context();

    public void eventOccurred(DeleteAccountEvent deleteAccountEvent) throws WrongPassword {
        Response response = MainController.sendEvents(deleteAccountEvent);
        if (response instanceof ExceptionResponse){
            throw (WrongPassword)((ExceptionResponse) response).getException();
        }
        OfflineUser offlineUser = context.getOfflineUsers().get(MainController.getUserId());
        UserName userName = context.getUserNames().get(offlineUser.getUserNameId());
        ChatRoom chatRoom = context.getChatRooms().get(offlineUser.getChatRoomID());
        for (ChatRoom chatroom : context.getChatRooms().all()) {
            chatroom.getMySingleChats().remove(chatRoom.searchChats(offlineUser.getUserId()));
            for (Group group: chatroom.getMultiChat().getGroupChats()) {
                group.getAllUsersId().remove((Integer) offlineUser.getUserId());
                for (int i = 0; i <group.getMessagesId().size() ; i++) {
                    Message message = getOfflineMessage(group.getMessagesId().get(i));
                    if (message.getOwnerId()==offlineUser.getUserId()){
                        group.getMessagesId().remove((Integer) message.getId());
                        if (group.getMessagesId().size()!=0)
                            group.setCurrentMessageId(group.getMessagesId().getLast());
                        else
                            group.setCurrentMessageId(null);
                        i--;
                    }
                }
                context.getChatRooms().update(chatroom);
            }
        }
        context.getOfflineUsers().remove(offlineUser);
        context.getUserNames().remove(userName);
        context.getChatRooms().remove(chatRoom);
        JPanel panel = (JPanel) deleteAccountEvent.getSource();
        String message = "Done!";
        JOptionPane.showMessageDialog(panel, message,"User Deleted his or her Account!"
                ,JOptionPane.INFORMATION_MESSAGE);
        LoginView loginView = new LoginView(panel);
        loginView.setLoginFormListener(new LoginFormListener());
        loginView.setBackToRegisterListener(new BackToRegisterListener());
        panel.removeAll();
        panel.add(loginView);
        panel.revalidate();
        panel.repaint();

    }

    private Message getOfflineMessage(int MessageId) {
        Message main;
        Message message = context.getMessages().get(MessageId);
        Tweet tweet = context.getTweets().get(MessageId);
        Comment comment = context.getComments().get(MessageId);
        if (message != null)
            main = message;
        else if (tweet != null)
            main = tweet;
        else
            main = comment;
        return main;
    }
}
