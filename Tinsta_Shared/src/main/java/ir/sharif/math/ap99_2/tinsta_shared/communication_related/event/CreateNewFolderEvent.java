package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class CreateNewFolderEvent extends CommunityEvent {
    private String userFolder;

    public CreateNewFolderEvent() {
    }

    public CreateNewFolderEvent(Object source, String userFolder) {
        super(source);
        this.userFolder = userFolder;
    }

    public String getUserFolder() {
        return userFolder;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        CommunityEventVisitor communityEventVisitor = (CommunityEventVisitor) eventVisitor;
        return communityEventVisitor.createFolder(this);
    }

    public void setUserFolder(String userFolder) {
        this.userFolder = userFolder;
    }
}
