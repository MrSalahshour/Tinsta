package ir.sharif.math.ap99_2.tinsta_shared.setting.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface SettingEventVisitor extends EventVisitor {

    Response changePassword(ChangePasswordEvent changePasswordEvent);

    Response deActiveAccount();

    Response deleteAccount(DeleteAccountEvent deleteAccountEvent);

    Response makePagePrivate();

    Response makePagePublic();

    Response showLastSeenToAll();

    Response showLastSeenToFollowers();

    Response showLastSeenToNoOne();

    Response getLastSeenStatus();

    Response getPrivacyStatus();
}
