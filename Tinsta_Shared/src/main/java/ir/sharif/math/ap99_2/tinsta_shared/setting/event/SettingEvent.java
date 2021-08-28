package ir.sharif.math.ap99_2.tinsta_shared.setting.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChangePasswordEvent.class, name = "ChangePasswordEvent"),
        @JsonSubTypes.Type(value = DeActiveAccountEvent.class, name = "DeActiveAccountEvent"),
        @JsonSubTypes.Type(value = DeleteAccountEvent.class, name = "DeleteAccountEvent"),
        @JsonSubTypes.Type(value = GetLastSeenStatusEvent.class, name = "GetLastSeenStatusEvent"),
        @JsonSubTypes.Type(value = GetPrivacyStatusEvent.class, name = "GetPrivacyStatusEvent"),
        @JsonSubTypes.Type(value = MakePagePrivateEvent.class, name = "MakePagePrivateEvent"),
        @JsonSubTypes.Type(value = MakePagePublicEvent.class, name = "MakePagePublicEvent"),
        @JsonSubTypes.Type(value = ShowLastSeenToAllEvent.class, name = "ShowLastSeenToAllEvent"),
        @JsonSubTypes.Type(value = ShowLastSeenToFollowersEvent.class, name = "ShowLastSeenToFollowersEvent"),
        @JsonSubTypes.Type(value = ShowLastSeenToNoOneEvent.class, name = "ShowLastSeenToNoOneEvent"),

})
public abstract class SettingEvent extends Event {
    public SettingEvent() {
    }

    public SettingEvent(Object source) {
        super(source);
    }
}
