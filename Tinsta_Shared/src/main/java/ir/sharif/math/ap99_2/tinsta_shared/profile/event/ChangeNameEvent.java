package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ChangeNameEvent extends ProfileEvent {
    private String Name;
    public ChangeNameEvent(Object source, String name) {
        super(source);
        Name = name;
    }

    public ChangeNameEvent() {
    }

    public String getName() {
        return Name;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        ProfileEventVisitor profileEventVisitor = (ProfileEventVisitor) eventVisitor;
        return profileEventVisitor.changeName(this);
    }

    public void setName(String name) {
        Name = name;
    }
}
