package ir.sharif.math.ap99_2.tinsta_server.profile.controller;



import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.InfoPrivacyEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.response.VoidResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Email;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.PhoneNumber;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class InfoPrivacyController extends Controller implements GetUser, GetLogger, InfoPrivacyEventVisitor {
    private final ClientHandler clientHandler;
    private LoggerController loggerController;
    public InfoPrivacyController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public Response setToShowBirthday() {
        User owner = getCurrentUser();
        owner.getBirthday().setPrivacy(false);
        UserName userName = getContext().getUserNames().get(getCurrentUser().getUserNameId());
        getLogger().log(LoggerController.infoLevel(),"InfoPrivacyController",
                "setToShowBirthday",userName.getUserNameText(),"Change Birthday Privacy" );
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response setToNotShowBirthday() {
        User owner = getCurrentUser();
        owner.getBirthday().setPrivacy(true);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(),"InfoPrivacyController",
                "setBirthdayPrivacy",userName.getUserNameText(),"Change Birthday Privacy" );
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response setToShowEmail() {
        User owner = getCurrentUser();
        Email email = getContext().getEmails().get(owner.getEmailId());
        email.setPrivacy(false);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(),"InfoPrivacyController",
                "setToShowEmail",userName.getUserNameText(),"Change Email Privacy" );
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response setToNotShowEmail(){
        User owner = getCurrentUser();
        Email email = getContext().getEmails().get(owner.getEmailId());
        email.setPrivacy(true);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(),"InfoPrivacyController",
                "setToNotShowEmail",userName.getUserNameText(),"Change Email Privacy" );
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response setToShowPhoneNumber() {
        User owner = getCurrentUser();
        PhoneNumber phoneNumber = getContext().getPhoneNumbers().get(owner.getPhoneNumberId());
        phoneNumber.setPrivacy(false);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(),"InfoPrivacyController",
                "setToShowPhoneNumber",userName.getUserNameText(),"Change PhoneNumber Privacy" );
        getContext().getUsers().update(owner);
        return new VoidResponse();

    }

    public Response setToNotShowPhoneNumber() {
        User owner = getCurrentUser();
        PhoneNumber phoneNumber = getContext().getPhoneNumbers().get(owner.getPhoneNumberId());
        phoneNumber.setPrivacy(true);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(),"InfoPrivacyController",
                "setToNotShowPhoneNumber",userName.getUserNameText(),"Change PhoneNumber Privacy" );
        getContext().getUsers().update(owner);
        return new VoidResponse();

    }


    @Override
    public LoggerController getLogger() {
        if (loggerController==null){
            loggerController = new LoggerController(clientHandler);
        }
        return loggerController;
    }

    @Override
    public User getCurrentUser() {
        return clientHandler.getCurrentUser();
    }

}
