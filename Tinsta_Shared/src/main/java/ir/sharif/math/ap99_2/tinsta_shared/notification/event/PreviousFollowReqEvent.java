package ir.sharif.math.ap99_2.tinsta_shared.notification.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class PreviousFollowReqEvent extends NotificationEvent {

    public PreviousFollowReqEvent(Object source) {
        super(source);
    }

    public PreviousFollowReqEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        NotificationEventVisitor notificationEventVisitor = (NotificationEventVisitor) eventVisitor;
        return notificationEventVisitor.goPreviousFollowRequest();
    }
}
