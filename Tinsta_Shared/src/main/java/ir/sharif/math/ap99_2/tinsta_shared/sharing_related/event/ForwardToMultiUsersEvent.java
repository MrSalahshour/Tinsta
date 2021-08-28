package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;

import java.util.EventObject;
import java.util.LinkedList;

public class ForwardToMultiUsersEvent extends ChatRoomEvent {
    private LinkedList<String> usernames;
    private Message message;

    public ForwardToMultiUsersEvent(Object source, LinkedList<String> usernames) {
        super(source);
        this.usernames = usernames;
    }

    public ForwardToMultiUsersEvent() {
    }

    public LinkedList<String> getUsernames() {
        return usernames;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.sendMessageByUserNames(message,this);
    }

    public void setUsernames(LinkedList<String> usernames) {
        this.usernames = usernames;
    }

    public Message getMessage() {
        return message;
    }
}
