package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ShowFollowersEvent extends CommunityEvent {
    public ShowFollowersEvent() {
    }

    public ShowFollowersEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        CommunityEventVisitor communityEventVisitor = (CommunityEventVisitor) eventVisitor;
        return communityEventVisitor.showFollowers(this);
    }


}
