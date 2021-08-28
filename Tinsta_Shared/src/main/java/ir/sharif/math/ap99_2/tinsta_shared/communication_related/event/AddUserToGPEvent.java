package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class AddUserToGPEvent extends ChatRoomEvent {
    private String username;
    private String groupName;

    public AddUserToGPEvent() {
    }

    public AddUserToGPEvent(Object source, String username, String groupName) {
        super(source);
        this.username = username;
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.addUserToGP(groupName,username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
