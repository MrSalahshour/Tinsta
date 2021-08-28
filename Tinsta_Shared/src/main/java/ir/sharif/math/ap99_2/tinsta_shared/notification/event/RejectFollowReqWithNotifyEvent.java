package ir.sharif.math.ap99_2.tinsta_shared.notification.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.util.EventObject;

public class RejectFollowReqWithNotifyEvent extends NotificationEvent {

    public RejectFollowReqWithNotifyEvent(Object source) {
        super(source);
    }

    public RejectFollowReqWithNotifyEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        NotificationEventVisitor notificationEventVisitor = (NotificationEventVisitor) eventVisitor;
        return notificationEventVisitor.rejectRequestWithNotify();
    }
}
