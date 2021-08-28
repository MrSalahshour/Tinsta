package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ChangeEmailEvent extends ProfileEvent {
    private String email;
    public ChangeEmailEvent(Object source, String email) {
        super(source);
        this.email = email;
    }

    public ChangeEmailEvent() {
    }

    public String getEmail() {
        return email;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        ProfileEventVisitor profileEventVisitor = (ProfileEventVisitor) eventVisitor;
        return profileEventVisitor.changeEmail(this);
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
