package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.util.EventObject;

public class ShowPrivateChatsEvent extends ChatRoomEvent {
    public ShowPrivateChatsEvent() {
    }

    public ShowPrivateChatsEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.showPrivateChats(this);
    }

}
