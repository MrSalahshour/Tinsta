package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.util.EventObject;

public class SetToNotShowBirthdayEvent extends InfoPrivacyEvent {

    public SetToNotShowBirthdayEvent(Object source) {
        super(source);
    }

    public SetToNotShowBirthdayEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        InfoPrivacyEventVisitor infoPrivacyEventVisitor = (InfoPrivacyEventVisitor) eventVisitor;
        return infoPrivacyEventVisitor.setToNotShowBirthday();
    }
}
