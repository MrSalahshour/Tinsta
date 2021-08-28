package ir.sharif.math.ap99_2.tinsta_shared.notification.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface NotificationEventVisitor extends EventVisitor {

    Response acceptRequest();

    Response hasNextFollowRequest();

    Response goNextFollowRequest();

    Response getCurrentFollowReqId();

    Response hasPreviousFollowRequest();

    Response goPreviousFollowRequest();

    Response rejectRequestWithoutNotify();

    Response rejectRequestWithNotify();

    Response dontHaveFollowRequest();

    Response showMySystemMessages(ShowSystemMessagesEvent showSystemMessagesEvent);



}
