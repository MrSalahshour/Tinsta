package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class LeftTheGroupEvent extends SingleChatEvent {
    private String groupName;

    public LeftTheGroupEvent() {
    }

    public LeftTheGroupEvent(Object source,String groupName) {
        super(source);
        this.groupName = groupName;

    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        SingleChatEventVisitor singleChatEventVisitor = (SingleChatEventVisitor) eventVisitor;
        return singleChatEventVisitor.leftTheGroup(groupName);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
