package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ForwardMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ForwardMessageEvent.class, name = "ForwardMessageEvent"),

})
public abstract class MessageEvent extends Event {
    public MessageEvent() {
    }

    public MessageEvent(Object source) {
        super(source);
    }
}
