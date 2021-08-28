package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SetToNotShowBirthdayEvent.class, name = "SetToNotShowBirthdayEvent"),
        @JsonSubTypes.Type(value = SetToNotShowEmailEvent.class, name = "SetToNotShowEmailEvent"),
        @JsonSubTypes.Type(value = SetToNotShowPhoneNumberEvent.class, name = "SetToNotShowPhoneNumberEvent"),
        @JsonSubTypes.Type(value = SetToShowBirthdayEvent.class, name = "SetToShowBirthdayEvent"),
        @JsonSubTypes.Type(value = SetToShowEmailEvent.class, name = "SetToShowEmailEvent"),
        @JsonSubTypes.Type(value = SetToShowPhoneNumberEvent.class, name = "SetToShowPhoneNumberEvent"),


})
public abstract class InfoPrivacyEvent extends Event {
    public InfoPrivacyEvent() {
    }

    public InfoPrivacyEvent(Object source) {
        super(source);
    }
}
