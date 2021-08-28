package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChangeBioEvent.class, name = "ChangeBioEvent"),
        @JsonSubTypes.Type(value = ChangeBirthdayEvent.class, name = "ChangeBirthdayEvent"),
        @JsonSubTypes.Type(value = ChangeEmailEvent.class, name = "ChangeEmailEvent"),
        @JsonSubTypes.Type(value = ChangeLastNameEvent.class, name = "ChangeLastNameEvent"),
        @JsonSubTypes.Type(value = ChangeNameEvent.class, name = "ChangeNameEvent"),
        @JsonSubTypes.Type(value = ChangePhoneNumberEvent.class, name = "ChangePhoneNumberEvent"),
        @JsonSubTypes.Type(value = ChangeUserNameEvent.class, name = "ChangeUserNameEvent"),


})
public abstract class ProfileEvent extends Event {
    public ProfileEvent() {
    }

    public ProfileEvent(Object source) {
        super(source);
    }
}
