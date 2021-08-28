package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;



public class StartChatEvent extends ChatRoomEvent {
    private int userId;

    public StartChatEvent(Object source, int userId) {
        super(source);
        this.userId = userId;
    }

    public StartChatEvent() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return null;
    }
}
