package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ForwardToUserFolderView;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToUserFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;

import javax.swing.*;

public class ForwardToUserFolderListener {
    Context context = new Context();

    public void eventOccurred(ForwardToUserFolderEvent forwardToUserFolderEvent) throws UserFolderNotExist {
        ForwardToUserFolderView forwardToUserFolderView = (ForwardToUserFolderView) forwardToUserFolderEvent.getSource();
        Message main;
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController.sendEvents
                (new GetMessageEvent(this,forwardToUserFolderView.getMessageId()));
        Message message = getMessageResponse.getMessage();
        GetTweetResponse getTweetResponse = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,forwardToUserFolderView.getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                (new GetCommentEvent(this,forwardToUserFolderView.getMessageId()));
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
        forwardToUserFolderEvent.setMessage(main);
        Response response = MainController.sendEvents(forwardToUserFolderEvent);
        if (response instanceof ExceptionResponse){
            throw (UserFolderNotExist)((ExceptionResponse) response).getException();
        }
        if (response instanceof SaveChatRoomResponse){
            Context context = new Context();
            for (ChatRoom chatroom: ((SaveChatRoomResponse) response).getChatRooms()) {
                context.getChatRooms().update(chatroom);
            }
        }
        JOptionPane.showMessageDialog(forwardToUserFolderView, "Done",
                "Forwarded to user folder",JOptionPane.INFORMATION_MESSAGE);
    }
}
