package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ChangeUserNameEvent extends ProfileEvent {
    private String username;
    public ChangeUserNameEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public ChangeUserNameEvent() {
    }

    public String getUsername() {
        return username;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        ProfileEventVisitor profileEventVisitor = (ProfileEventVisitor) eventVisitor;
        return profileEventVisitor.changeUserName(this);
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
