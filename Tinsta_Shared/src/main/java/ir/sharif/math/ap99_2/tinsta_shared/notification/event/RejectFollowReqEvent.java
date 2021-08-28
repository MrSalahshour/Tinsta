package ir.sharif.math.ap99_2.tinsta_shared.notification.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.util.EventObject;

public class RejectFollowReqEvent extends NotificationEvent {

    public RejectFollowReqEvent(Object source) {
        super(source);
    }

    public RejectFollowReqEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        NotificationEventVisitor notificationEventVisitor = (NotificationEventVisitor) eventVisitor;
        return notificationEventVisitor.rejectRequestWithoutNotify();
    }
}
