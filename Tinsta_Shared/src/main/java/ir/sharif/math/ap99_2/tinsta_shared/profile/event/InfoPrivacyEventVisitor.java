package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface InfoPrivacyEventVisitor extends EventVisitor {

    Response setToNotShowBirthday();

    Response setToNotShowEmail();

    Response setToNotShowPhoneNumber();

    Response setToShowBirthday();

    Response setToShowEmail();

    Response setToShowPhoneNumber();
}
