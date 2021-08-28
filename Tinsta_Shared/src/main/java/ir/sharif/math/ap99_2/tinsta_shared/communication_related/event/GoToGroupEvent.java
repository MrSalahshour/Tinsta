package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import java.util.EventObject;


public class GoToGroupEvent extends EventObject {

    private final String groupName;

    public GoToGroupEvent(Object source, String groupName) {
        super(source);
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }


}
