package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface ProfileEventVisitor extends EventVisitor {

    Response changeBiography(ChangeBioEvent changeBioEvent);

    Response changeBirthday(ChangeBirthdayEvent changeBirthdayEvent);

    Response changeEmail(ChangeEmailEvent changeEmailEvent);

    Response changeLastName(ChangeLastNameEvent changeLastNameEvent);

    Response changeName(ChangeNameEvent changeNameEvent);

    Response changePhoneNumber(ChangePhoneNumberEvent changePhoneNumberEvent);

    Response changeUserName(ChangeUserNameEvent changeUserNameEvent);



}
