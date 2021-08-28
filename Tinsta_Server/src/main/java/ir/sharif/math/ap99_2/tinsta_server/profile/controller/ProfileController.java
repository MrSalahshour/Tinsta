package ir.sharif.math.ap99_2.tinsta_server.profile.controller;



import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.response.VoidResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Email;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.PhoneNumber;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.EmailAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.PhoneNumberAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNameAlreadyExist;

public class ProfileController extends Controller implements GetUser, GetLogger, ProfileEventVisitor {
    private final ClientHandler clientHandler;
    private LoggerController loggerController;
    public ProfileController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }


    public Response changeUserName(ChangeUserNameEvent changeUserNameEvent) {
        User owner = getCurrentUser();
        String username = changeUserNameEvent.getUsername();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        if (getContext().getUserNames().UserNameExist(username)) {
            return new ExceptionResponse(new UserNameAlreadyExist("UserName Already Exist!"));
        }
        changeUserName(username);
        getLogger().log(LoggerController.infoLevel(), "ProfileController",
                "changeUserName", userName.getUserNameText(), "User Changed UserName");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response changeName(ChangeNameEvent changeNameEvent){
        User owner = getCurrentUser();
        String name = changeNameEvent.getName();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getHomePage().getProfile().changeName(name,owner);
        getLogger().log(LoggerController.infoLevel(), "ProfileController",
                "changeName", userName.getUserNameText(), "User Changed Name");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response changeLastName(ChangeLastNameEvent changeLastNameEvent) {
        User owner = getCurrentUser();
        String lastName = changeLastNameEvent.getLastName();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getHomePage().getProfile().changeLastName(lastName,owner);
        getLogger().log(LoggerController.infoLevel(), "ProfileController",
                "changeLastName", userName.getUserNameText(), "User Changed LastName");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response changeBiography(ChangeBioEvent changeBioEvent) {
        User owner = getCurrentUser();
        String biography = changeBioEvent.getBio();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getHomePage().getProfile().changeBiography(biography,owner);
        getLogger().log(LoggerController.infoLevel(), "ProfileController",
                "changeBiography", userName.getUserNameText(), "User Changed Biography");
        getContext().getUsers().update(owner);
        return new VoidResponse();

    }

    public Response changePhoneNumber(ChangePhoneNumberEvent changePhoneNumberEvent) {
        String phoneNumber = changePhoneNumberEvent.getPhoneNumber();
        if (getContext().getPhoneNumbers().PhoneNumberExist(phoneNumber)) {
            return new ExceptionResponse(new PhoneNumberAlreadyExist("Phone Number Already Exist!"));
        }
        UserName userName = getContext().getUserNames().get(getCurrentUser().getUserNameId());
        changePhoneNumber(phoneNumber);
        getLogger().log(LoggerController.infoLevel(), "ProfileController",
                "changePhoneNumber", userName.getUserNameText(), "User Changed Phone Number");
        getContext().getUsers().update(getCurrentUser());
        return new VoidResponse();

    }

    public Response changeEmail(ChangeEmailEvent changeEmailEvent){
        String email = changeEmailEvent.getEmail();
        UserName userName = getContext().getUserNames().get(getCurrentUser().getUserNameId());

        if (getContext().getEmails().EmailExist(email)) {
            return new ExceptionResponse(new EmailAlreadyExist("Email Already Exist!"));
        }
        changeEmail(email);
        getLogger().log(LoggerController.infoLevel(), "ProfileController",
                "changeEmail", userName.getUserNameText(), "User Changed Email");
        getContext().getUsers().update(getCurrentUser());
        return new VoidResponse();

    }

    public Response changeBirthday(ChangeBirthdayEvent changeBirthdayEvent){
        User owner = getCurrentUser();
        String year = changeBirthdayEvent.getYear();
        String month = changeBirthdayEvent.getMonth();
        String day = changeBirthdayEvent.getDay();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getCurrentUser().getMenu().getHomePage().getProfile().changeBirthday(year, month, day,owner);
        getLogger().log(LoggerController.infoLevel(), "ProfileController",
                "changeBirthday", userName.getUserNameText(), "User Changed Birthday");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    private void changeUserName(String username) {
        UserName userName = getContext().getUserNames().get(getCurrentUser().getUserNameId());
        userName.setUserNameText(username);
        getContext().getUserNames().update(userName);
    }

    private void changePhoneNumber(String phoneNumber) {
        PhoneNumber phonenumber =getContext().getPhoneNumbers().get(getCurrentUser().getPhoneNumberId());
        phonenumber.setPhoneNumberText(phoneNumber);
        getContext().getPhoneNumbers().update(phonenumber);
    }

    private void changeEmail(String emailText) {
        Email email =getContext().getEmails().get(getCurrentUser().getEmailId());
        email.setEmailText(emailText);
        getContext().getEmails().update(email);
    }

    @Override
    public User getCurrentUser() {
        return clientHandler.getCurrentUser();
    }

    @Override
    public LoggerController getLogger() {
        if (loggerController==null){
            loggerController = new LoggerController(clientHandler);
        }
        return loggerController;
    }
}
