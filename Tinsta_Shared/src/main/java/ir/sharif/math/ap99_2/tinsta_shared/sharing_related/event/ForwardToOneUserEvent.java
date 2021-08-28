package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;


public class ForwardToOneUserEvent extends ChatRoomEvent {
    private String username;
    private Message message;

    public ForwardToOneUserEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public ForwardToOneUserEvent() {
    }

    public String getUsername() {
        return username;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.sendSingleMessage(message,this);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Message getMessage() {
        return message;
    }
}
