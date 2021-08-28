package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class SetToShowPhoneNumberEvent extends InfoPrivacyEvent {

    public SetToShowPhoneNumberEvent(Object source) {
        super(source);
    }

    public SetToShowPhoneNumberEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        InfoPrivacyEventVisitor infoPrivacyEventVisitor = (InfoPrivacyEventVisitor) eventVisitor;
        return infoPrivacyEventVisitor.setToShowPhoneNumber();
    }
}
