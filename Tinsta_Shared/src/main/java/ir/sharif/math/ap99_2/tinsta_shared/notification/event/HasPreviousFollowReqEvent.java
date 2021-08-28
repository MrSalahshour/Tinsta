package ir.sharif.math.ap99_2.tinsta_shared.notification.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class HasPreviousFollowReqEvent extends NotificationEvent{

    public HasPreviousFollowReqEvent(Object source) {
        super(source);
    }

    public HasPreviousFollowReqEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        NotificationEventVisitor notificationEventVisitor = (NotificationEventVisitor) eventVisitor;
        return notificationEventVisitor.hasPreviousFollowRequest();
    }
}
