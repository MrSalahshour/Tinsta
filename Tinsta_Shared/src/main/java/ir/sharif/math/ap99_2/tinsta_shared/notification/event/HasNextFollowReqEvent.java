package ir.sharif.math.ap99_2.tinsta_shared.notification.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class HasNextFollowReqEvent extends NotificationEvent {

    public HasNextFollowReqEvent(Object source) {
        super(source);
    }

    public HasNextFollowReqEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        NotificationEventVisitor notificationEventVisitor = (NotificationEventVisitor) eventVisitor;
        return notificationEventVisitor.hasNextFollowRequest();
    }

}
