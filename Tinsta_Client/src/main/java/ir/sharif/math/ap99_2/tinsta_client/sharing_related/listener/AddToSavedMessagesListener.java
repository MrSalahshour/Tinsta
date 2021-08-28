package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.ShowTweetsView;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetCommentEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetCommentResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveChatRoomResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.AddToSavedMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

import javax.swing.*;

public class AddToSavedMessagesListener {

    public void eventOccurred(AddToSavedMessageEvent addToSavedMessageEvent){
        ShowTweetsView showTweetsView = (ShowTweetsView) addToSavedMessageEvent.getSource();
        Tweet mainTweet;
        GetTweetResponse getTweetResponse  = (GetTweetResponse) MainController.sendEvents
                (new GetTweetEvent(this,showTweetsView.getMessageView().getMessageId()));
        Tweet tweet = getTweetResponse.getTweet();
        if (tweet!=null){
            mainTweet = tweet;
        }
        else {
            GetCommentResponse getCommentResponse = (GetCommentResponse) MainController.sendEvents
                    (new GetCommentEvent(this,showTweetsView.getMessageView().getMessageId()));
            mainTweet = getCommentResponse.getComment();
        }
        addToSavedMessageEvent.setTweet(mainTweet);
        SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse)
                MainController.sendEvents(addToSavedMessageEvent);
        Context context = new Context();
        context.getChatRooms().update(saveChatRoomResponse.getChatRoom());
        String message = "Done!";
        JOptionPane.showMessageDialog(showTweetsView, message,"Added To Saved Messages!"
                ,JOptionPane.INFORMATION_MESSAGE);
    }
}
