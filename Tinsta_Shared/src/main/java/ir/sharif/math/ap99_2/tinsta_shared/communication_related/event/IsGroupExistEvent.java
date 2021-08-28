package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class IsGroupExistEvent extends ChatRoomEvent {
    private String groupName;

    public IsGroupExistEvent() {
    }

    public IsGroupExistEvent(Object source, String groupName) {
        super(source);
        this.groupName = groupName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.groupExist(groupName);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
