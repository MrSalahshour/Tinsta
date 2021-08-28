package ir.sharif.math.ap99_2.tinsta_shared.explorer.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class GetUserProfileTextEvent extends ExplorerEvent{
    private User user;


    public GetUserProfileTextEvent() {
    }

    public GetUserProfileTextEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ExplorerEventVisitor explorerEventVisitor = (ExplorerEventVisitor) eventVisitor;
        return explorerEventVisitor.getUserProfileTextContent(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
