package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ChangeLastNameEvent extends ProfileEvent {
    private String lastName;
    public ChangeLastNameEvent(Object source, String lastName) {
        super(source);
        this.lastName = lastName;
    }

    public ChangeLastNameEvent() {
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ProfileEventVisitor profileEventVisitor = (ProfileEventVisitor) eventVisitor;
        return profileEventVisitor.changeLastName(this);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
