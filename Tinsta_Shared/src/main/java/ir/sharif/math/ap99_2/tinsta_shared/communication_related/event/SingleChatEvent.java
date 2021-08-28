package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddToSavedMessageFromChatEvent.class, name = "AddToSavedMessageFromChatEvent"),
        @JsonSubTypes.Type(value = LeftTheGroupEvent.class, name = "LeftTheGroupEvent"),

})
public abstract class SingleChatEvent extends Event {
    public SingleChatEvent() {
    }

    public SingleChatEvent(Object source) {
        super(source);
    }
}
