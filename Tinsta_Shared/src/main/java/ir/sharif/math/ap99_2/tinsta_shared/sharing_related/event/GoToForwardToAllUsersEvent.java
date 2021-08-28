package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;


public class GoToForwardToAllUsersEvent extends ChatRoomEvent {
    private Message message;

    public GoToForwardToAllUsersEvent(Object source) {
        super(source);
    }

    public GoToForwardToAllUsersEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.sendMessageToAllUsers(message);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
