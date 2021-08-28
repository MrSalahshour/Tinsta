package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddToUserFolderEvent.class, name = "AddToUserFolderEvent"),
        @JsonSubTypes.Type(value = CreateNewFolderEvent.class, name = "CreateNewFolderEvent"),
        @JsonSubTypes.Type(value = RemoveUserFromFolderEvent.class, name = "RemoveUserFromFolderEvent"),
        @JsonSubTypes.Type(value = ShowAFolderContentEvent.class, name = "ShowAFolderContentEvent"),
        @JsonSubTypes.Type(value = ShowBlackListsEvent.class, name = "ShowBlackListsEvent"),
        @JsonSubTypes.Type(value = ShowFoldersListEvent.class, name = "ShowFoldersListEvent"),
        @JsonSubTypes.Type(value = ShowFollowersEvent.class, name = "ShowFollowersEvent"),
        @JsonSubTypes.Type(value = ShowFollowingsEvent.class, name = "ShowFollowingsEvent"),
        @JsonSubTypes.Type(value = ShowMutedListEvent.class, name = "ShowMutedListEvent"),

})
public abstract class CommunityEvent extends Event {
    public CommunityEvent() {
    }

    public CommunityEvent(Object source) {
        super(source);
    }
}
