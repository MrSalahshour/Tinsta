package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToMultiUsersEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToOneUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToUserFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.GoToForwardToAllUsersEvent;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddUserToGPEvent.class, name = "AddUserToGPEvent"),
        @JsonSubTypes.Type(value = DeleteMessageEvent.class, name = "DeleteMessageEvent"),
        @JsonSubTypes.Type(value = EditMessageEvent.class, name = "EditMessageEvent"),
        @JsonSubTypes.Type(value = IsGroupExistEvent.class, name = "IsGroupExistEvent"),
        @JsonSubTypes.Type(value = SendMsgEvent.class, name = "SendMsgEvent"),
        @JsonSubTypes.Type(value = ShowGroupChatsEvent.class, name = "ShowGroupChatsEvent"),
        @JsonSubTypes.Type(value = ShowPrivateChatsEvent.class, name = "ShowPrivateChatsEvent"),
        @JsonSubTypes.Type(value = ForwardToMultiUsersEvent.class, name = "ForwardToMultiUsersEvent"),
        @JsonSubTypes.Type(value = ForwardToOneUserEvent.class, name = "ForwardToOneUserEvent"),
        @JsonSubTypes.Type(value = ForwardToUserFolderEvent.class, name = "ForwardToUserFolderEvent"),
        @JsonSubTypes.Type(value = GoToForwardToAllUsersEvent.class, name = "GoToForwardToAllUsersEvent"),
        @JsonSubTypes.Type(value = SendMessageEvent.class, name = "SendMessageEvent"),
        @JsonSubTypes.Type(value = StartGroupEvent.class, name = "StartGroupEvent"),
        @JsonSubTypes.Type(value = StartChatEvent.class, name = "StartChatEvent"),
        @JsonSubTypes.Type(value = MakeGroupEvent.class, name = "MakeGroupEvent"),
        @JsonSubTypes.Type(value = DeleteUnreadMessageEvent.class, name = "DeleteUnreadMessageEvent"),
})
public abstract class ChatRoomEvent extends Event {
    public ChatRoomEvent() {
    }

    public ChatRoomEvent(Object source) {
        super(source);
    }
}
