package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardToMultiUsersView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveChatRoomResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToMultiUsersEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

import javax.swing.*;

public class ForwardToMultiUsersListener {
    Context context = new Context();

    public void eventOccurred(ForwardToMultiUsersEvent forwardToMultiUsersEvent){
        ForwardToMultiUsersView forwardToMultiUsersView = (ForwardToMultiUsersView) forwardToMultiUsersEvent.getSource();
        Message main;
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController.sendEvents
                (new GetMessageEvent(this,forwardToMultiUsersView.getMessageId()));
        Message message = getMessageResponse.getMessage();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,forwardToMultiUsersView.getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                (new GetCommentEvent(this,forwardToMultiUsersView.getMessageId()));
        Comment comment = getCommentResponse.getComment();
        if (message!=null){
            main = message;
            context.getMessages().add(main);
        }
        else if (tweet!=null){
            main = tweet;
            context.getTweets().add((Tweet) main);
        }
        else{
            main = comment;
            context.getComments().add((Comment) main);
        }
        forwardToMultiUsersEvent.setMessage(main);
        SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                MainController.sendEvents(forwardToMultiUsersEvent);
        for (ChatRoom chatroom : saveChatRoomResponse.getChatRooms()) {
            context.getChatRooms().update(chatroom);
        }
        String msg = "Done!";
        JOptionPane.showMessageDialog(forwardToMultiUsersView, msg,
                "Forwarded to available users!",JOptionPane.INFORMATION_MESSAGE);

    }
}
