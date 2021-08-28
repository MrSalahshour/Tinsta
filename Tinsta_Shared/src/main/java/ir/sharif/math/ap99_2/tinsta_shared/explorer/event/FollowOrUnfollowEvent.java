package ir.sharif.math.ap99_2.tinsta_shared.explorer.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


public class FollowOrUnfollowEvent extends ExplorerEvent {
    private User user;


    public FollowOrUnfollowEvent() {
    }
    public FollowOrUnfollowEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ExplorerEventVisitor explorerEventVisitor = (ExplorerEventVisitor) eventVisitor;
        return explorerEventVisitor.followOrUnFollowUser(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
