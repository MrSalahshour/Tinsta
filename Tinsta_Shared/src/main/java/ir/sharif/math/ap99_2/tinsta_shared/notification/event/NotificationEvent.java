package ir.sharif.math.ap99_2.tinsta_shared.notification.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GetCurrentFollowReqIdEvent.class, name = "GetCurrentFollowReqIdEvent"),
        @JsonSubTypes.Type(value = AcceptFollowReqEvent.class, name = "AcceptFollowReqEvent"),
        @JsonSubTypes.Type(value = HasNextFollowReqEvent.class, name = "HasNextFollowReqEvent"),
        @JsonSubTypes.Type(value = HasPreviousFollowReqEvent.class, name = "HasPreviousFollowReqEvent"),
        @JsonSubTypes.Type(value = NextFollowReqEvent.class, name = "NextFollowReqEvent"),
        @JsonSubTypes.Type(value = PreviousFollowReqEvent.class, name = "PreviousFollowReqEvent"),
        @JsonSubTypes.Type(value = RejectFollowReqEvent.class, name = "RejectFollowReqEvent"),
        @JsonSubTypes.Type(value = ShowMyFollowRequestsEvent.class, name = "ShowMyFollowRequestsEvent"),
        @JsonSubTypes.Type(value = ShowSystemMessagesEvent.class, name = "ShowSystemMessagesEvent"),
        @JsonSubTypes.Type(value = RejectFollowReqWithNotifyEvent.class, name = "RejectFollowReqWithNotifyEvent"),



})
public abstract class NotificationEvent extends Event {

    public NotificationEvent() {
    }

    public NotificationEvent(Object source) {
        super(source);
    }
}
