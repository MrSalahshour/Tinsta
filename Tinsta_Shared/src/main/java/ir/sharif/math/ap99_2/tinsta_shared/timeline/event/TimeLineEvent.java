package ir.sharif.math.ap99_2.tinsta_shared.timeline.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GetCurrentTimeLineTweetEvent.class, name = "GetCurrentTimeLineTweetEvent"),
        @JsonSubTypes.Type(value = ShowTimeLineTweetEvent.class, name = "ShowTimeLineTweetEvent"),


})
public abstract class TimeLineEvent extends Event {

    public TimeLineEvent() {
    }

    public TimeLineEvent(Object source) {
        super(source);
    }
}
