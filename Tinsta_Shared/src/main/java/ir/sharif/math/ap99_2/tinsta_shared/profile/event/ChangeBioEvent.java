package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ChangeBioEvent extends ProfileEvent {
    private String bio;

    public ChangeBioEvent(Object source, String bio) {
        super(source);
        this.bio = bio;
    }

    public ChangeBioEvent() {
    }

    public String getBio() {
        return bio;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ProfileEventVisitor profileEventVisitor = (ProfileEventVisitor) eventVisitor;
        return profileEventVisitor.changeBiography(this);
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
