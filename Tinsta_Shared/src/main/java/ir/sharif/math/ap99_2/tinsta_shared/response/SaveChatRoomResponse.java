package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;

import java.util.LinkedList;

public class SaveChatRoomResponse extends Response {
    private ChatRoom chatRoom;
    private LinkedList<ChatRoom> chatRooms;

    public SaveChatRoomResponse(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public SaveChatRoomResponse(LinkedList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public SaveChatRoomResponse() {
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public LinkedList<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(LinkedList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }
}
