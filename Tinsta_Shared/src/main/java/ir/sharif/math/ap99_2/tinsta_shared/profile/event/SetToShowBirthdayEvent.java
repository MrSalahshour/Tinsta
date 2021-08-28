package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class SetToShowBirthdayEvent extends InfoPrivacyEvent {

    public SetToShowBirthdayEvent(Object source) {
        super(source);
    }

    public SetToShowBirthdayEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        InfoPrivacyEventVisitor infoPrivacyEventVisitor = (InfoPrivacyEventVisitor) eventVisitor;
        return infoPrivacyEventVisitor.setToShowBirthday();
    }
}
