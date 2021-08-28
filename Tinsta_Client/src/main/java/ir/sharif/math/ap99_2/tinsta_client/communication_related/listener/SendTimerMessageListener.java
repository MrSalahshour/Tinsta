package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;

import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SendMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SendMsgEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveChatRoomResponse;

import java.util.Timer;
import java.util.TimerTask;

public class SendTimerMessageListener {

    public void eventOccurred(SendMessageEvent sendMessageEvent){
        ChatView chatView = (ChatView) sendMessageEvent.getSource();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                SaveChatRoomResponse saveChatRoomResponse = (SaveChatRoomResponse) MainController
                        .sendEvents(new SendMsgEvent(this,chatView.getGroupName(),sendMessageEvent));
                Context context = new Context();
                for (ChatRoom chatroom:saveChatRoomResponse.getChatRooms()) {
                    context.getChatRooms().update(chatroom);
                }
            }
        };
        timer.schedule(timerTask,sendMessageEvent.getMilliDelay());


    }
}
