package ir.sharif.math.ap99_2.tinsta_shared.user_related.event;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LogOutEvent.class, name = "LogOutEvent"),
        @JsonSubTypes.Type(value = LoginFormEvent.class, name = "LoginFormEvent"),
        @JsonSubTypes.Type(value = RegisterFormEvent.class, name = "RegisterFormEvent"),
        @JsonSubTypes.Type(value = IsConnectedEvent.class, name = "IsConnectedEvent"),
        @JsonSubTypes.Type(value = ExitEvent.class, name = "ExitEvent"),





})
public abstract class RegisterEvent extends Event {
    public RegisterEvent() {
    }

    public RegisterEvent(Object source) {
        super(source);
    }
}
