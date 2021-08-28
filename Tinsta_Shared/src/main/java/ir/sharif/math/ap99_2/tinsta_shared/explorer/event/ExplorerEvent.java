package ir.sharif.math.ap99_2.tinsta_shared.explorer.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BlockOrUnblockEvent.class, name = "BlockOrUnblockEvent"),
        @JsonSubTypes.Type(value = FollowOrUnfollowEvent.class, name = "FollowOrUnfollowEvent"),
        @JsonSubTypes.Type(value = GetCurrentExplorerTweetEvent.class, name = "GetCurrentExplorerTweetEvent"),
        @JsonSubTypes.Type(value = GetUserProfileTextEvent.class, name = "GetUserProfileTextEvent"),
        @JsonSubTypes.Type(value = MuteOrUnMuteEvent.class, name = "MuteOrUnMuteEvent"),
        @JsonSubTypes.Type(value = SearchUserEvent.class, name = "SearchUserEvent"),
        @JsonSubTypes.Type(value = ShowExplorerTweetEvent.class, name = "ShowExplorerTweetEvent"),

})
public abstract class ExplorerEvent extends Event {
    public ExplorerEvent() {
    }

    public ExplorerEvent(Object source) {
        super(source);
    }
}
