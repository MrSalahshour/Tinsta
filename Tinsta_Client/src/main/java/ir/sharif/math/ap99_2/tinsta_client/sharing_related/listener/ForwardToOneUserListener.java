package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardToOneUserView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToOneUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;

import javax.swing.*;

public class ForwardToOneUserListener {
    Context context = new Context();

    public void eventOccurred(ForwardToOneUserEvent forwardToOneUserEvent) throws UserNotExist {
        ForwardToOneUserView forwardToOneUserView = (ForwardToOneUserView) forwardToOneUserEvent.getSource();
        Message main;
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController.sendEvents
                (new GetMessageEvent(this,forwardToOneUserView.getMessageId()));
        Message message = getMessageResponse.getMessage();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,forwardToOneUserView.getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                (new GetCommentEvent(this,forwardToOneUserView.getMessageId()));
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
        forwardToOneUserEvent.setMessage(main);
        Response response = MainController.sendEvents(forwardToOneUserEvent);
        if (response instanceof ExceptionResponse){
            throw (UserNotExist)((ExceptionResponse) response).getException();
        }
        if (response instanceof SaveChatRoomResponse){
            for (ChatRoom chatRoom : ((SaveChatRoomResponse) response).getChatRooms() ) {
                context.getChatRooms().update(chatRoom);
            }
        }
        String msg = "Done!";
        JOptionPane.showMessageDialog(forwardToOneUserView, msg,
                "Forward if it was Possible!",JOptionPane.INFORMATION_MESSAGE);
    }
}
