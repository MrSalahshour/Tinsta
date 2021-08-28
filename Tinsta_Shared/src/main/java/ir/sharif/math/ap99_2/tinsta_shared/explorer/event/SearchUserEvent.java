package ir.sharif.math.ap99_2.tinsta_shared.explorer.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class SearchUserEvent extends ExplorerEvent {
    private String username;
    private String origin;

    public SearchUserEvent() {
    }

    public SearchUserEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ExplorerEventVisitor explorerEventVisitor = (ExplorerEventVisitor) eventVisitor;
        return explorerEventVisitor.searchUser(origin,this);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrigin() {
        return origin;
    }
}
