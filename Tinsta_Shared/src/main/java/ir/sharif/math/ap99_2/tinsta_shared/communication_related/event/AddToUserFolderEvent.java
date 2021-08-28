package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class AddToUserFolderEvent extends CommunityEvent {
    private String username;
    private String userFolder;

    public AddToUserFolderEvent() {
    }

    public AddToUserFolderEvent(Object source, String username, String userFolder) {
        super(source);
        this.username = username;
        this.userFolder = userFolder;
    }

    public String getUsername() {
        return username;
    }

    public String getUserFolder() {
        return userFolder;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        CommunityEventVisitor communityEventVisitor = (CommunityEventVisitor) eventVisitor;
        return communityEventVisitor.addUserToFolder(this);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserFolder(String userFolder) {
        this.userFolder = userFolder;
    }
}
