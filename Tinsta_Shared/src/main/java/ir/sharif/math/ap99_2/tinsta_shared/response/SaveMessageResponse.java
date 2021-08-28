package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;

import java.util.LinkedList;

public class SaveMessageResponse extends Response{
    private Message message;
    private LinkedList<ChatRoom> chatRooms;
    private ChatRoom chatRoom;

    public SaveMessageResponse(Message message) {
        this.message = message;
    }

    public SaveMessageResponse() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public LinkedList<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(LinkedList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
