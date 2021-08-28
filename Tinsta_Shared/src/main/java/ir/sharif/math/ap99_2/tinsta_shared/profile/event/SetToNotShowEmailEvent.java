package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class SetToNotShowEmailEvent extends InfoPrivacyEvent {

    public SetToNotShowEmailEvent(Object source) {
        super(source);
    }

    public SetToNotShowEmailEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        InfoPrivacyEventVisitor infoPrivacyEventVisitor = (InfoPrivacyEventVisitor) eventVisitor;
        return infoPrivacyEventVisitor.setToNotShowEmail();
    }
}
