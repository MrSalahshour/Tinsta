package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ShowMutedListEvent extends CommunityEvent {
    public ShowMutedListEvent() {
    }

    public ShowMutedListEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        CommunityEventVisitor communityEventVisitor = (CommunityEventVisitor) eventVisitor;
        return communityEventVisitor.showMutedList(this);
    }

}
