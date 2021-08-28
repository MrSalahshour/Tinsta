package ir.sharif.math.ap99_2.tinsta_shared.event;

import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class GetUserNameEvent extends Event {

    private Integer userNameId;

    public GetUserNameEvent() {
    }

    public GetUserNameEvent(Object source, Integer userNameId) {
        super(source);
        this.userNameId = userNameId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        MainEventVisitor mainEventVisitor = (MainEventVisitor) eventVisitor;
        return mainEventVisitor.getUserName(userNameId);
    }

    public Integer getUserNameId() {
        return userNameId;
    }

    public void setUserNameId(Integer userNameId) {
        this.userNameId = userNameId;
    }
}
