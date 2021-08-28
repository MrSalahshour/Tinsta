package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.util.EventObject;

public class ChangePhoneNumberEvent extends ProfileEvent {
    private String phoneNumber;
    public ChangePhoneNumberEvent(Object source, String phoneNumber) {
        super(source);
        this.phoneNumber = phoneNumber;
    }

    public ChangePhoneNumberEvent() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        ProfileEventVisitor profileEventVisitor = (ProfileEventVisitor) eventVisitor;
        return profileEventVisitor.changePhoneNumber(this);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
