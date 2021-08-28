package ir.sharif.math.ap99_2.tinsta_server.user_related.controller;



import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Email;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.PhoneNumber;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.LoginFormEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterFormEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.util.Loop;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.EmailAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNameAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


public class RegisterController extends Controller implements GetLogger, RegisterEventVisitor {

    private final LoggerController loggerController;
    private final ClientHandler clientHandler;
    private Loop lastSeenUpdater;

    public RegisterController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.loggerController = new LoggerController(clientHandler);
        lastSeenUpdater = new Loop(0.2,this::refreshTask);

    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public Response loginUser(LoginFormEvent loginFormEvent){

        String username = loginFormEvent.getUserName();
        String password = loginFormEvent.getPassword();

        User user = loginUser(username, password);
        if (user == null){
            getLogger().log(LoggerController.errorLevel(),
                    "RegisterController", "loginUser",
                    "Unknown!!", "Invalid Login");
            return new ExceptionResponse(new UserNotExist("User Not Exist!"));
        }
        else {
            user.setOnline(true);
            clientHandler.setCurrentUser(user);
            getContext().getUsers().update(user);
            UserName userName = getContext().getUserNames().get(user.getUserNameId());
            getLogger().log(LoggerController.infoLevel(),
                    "RegisterController", "loginUser", userName.getUserNameText(), "User Login");
            lastSeenUpdater.start();
        }
        LoginResponse loginResponse = new LoginResponse(true);
        loginResponse.setUserId(user.getId());
        return loginResponse;
    }

    public Response registerUser(RegisterFormEvent registerFormEvent) {
        try {
            String name = registerFormEvent.getName();
            String lastName = registerFormEvent.getLastName();
            String userName = registerFormEvent.getUserName();
            String password = registerFormEvent.getPassword();
            String email = registerFormEvent.getEmail();
            User user = signupUser(name, lastName, userName, password, email);
            user.setProfileImagePath(ImageLoader.getDefaultProfileImagePath());
            getContext().getUsers().add(user);
            clientHandler.setCurrentUser(user);
            UserName userNameTmp = getContext().getUserNames().get(user.getUserNameId());
            getLogger().log(LoggerController.infoLevel(), "RegisterController", "registerUser",
                    userNameTmp.getUserNameText(), "User SignUp");
            lastSeenUpdater.start();
            LoginResponse loginResponse = new LoginResponse(true);
            loginResponse.setUserId(user.getId());
            loginResponse.setUserName(userNameTmp);
            loginResponse.setUser(user);
            return loginResponse;
        }
        catch (EmailAlreadyExist | UserNameAlreadyExist emailAlreadyExist){
            return new ExceptionResponse(emailAlreadyExist);
        }
    }

    @Override
    public Response logOut() {
        clientHandler.setRunning(false);
        lastSeenUpdater.stop();
        lastSeenUpdater = new Loop(0.2,this::refreshTask);
        User user = clientHandler.getCurrentUser();
        user.setOnline(false);
        getContext().getUsers().update(user);
        clientHandler.setRunning(true);
        return new VoidResponse();
    }

    @Override
    public Response isConnected() {
        if (clientHandler.getCurrentUser()==null)
            return new GetBooleanResponse(false);
        else
            return new GetBooleanResponse(true);
    }

    @Override
    public Response exit() {
        clientHandler.setRunning(false);
        lastSeenUpdater.stop();
        lastSeenUpdater = new Loop(0.2,this::refreshTask);
        User user = clientHandler.getCurrentUser();
        if (user!=null){
            user.setOnline(false);
            getContext().getUsers().update(user);
        }
        return new VoidResponse();
    }

    private User loginUser(String username, String password) {
        if (getContext().getUserNames().UserNameExist(username)) {
            for (User user : getContext().getUsers().all()) {
                UserName userNameInput = getContext().getUserNames().get(user.getUserNameId());
                if (userNameInput.getUserNameText().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        user.getMenu().getSetting().setActive(true);
                        return user;
                    }
                }
            }
        }
        return null;
    }

    private User signupUser(String name, String lastName, String userNameText, String password, String emailText)
            throws UserNameAlreadyExist, EmailAlreadyExist {
        if (getContext().getUserNames().UserNameExist(userNameText)) {
            getLogger().log(LoggerController.errorLevel(),
                    "RegisterController", "signupUser",
                    "Unknown!!", "Invalid User Name");
            throw new UserNameAlreadyExist("User Name Already Exist");
        }
        if (getContext().getEmails().EmailExist(emailText)) {
            getLogger().log(LoggerController.errorLevel(),
                    "RegisterController", "signupUser",
                    "Unknown!!", "Invalid Email");
            throw new EmailAlreadyExist("Email Already Exist");
        }
        User user = new User(name, lastName, password);
        user.setProfileImagePath(ImageLoader.getDefaultProfileImagePath());
        UserName userName = new UserName(user.getId(),userNameText);
        getContext().getUserNames().add(userName);
        user.setUserNameId(userName.getId());
        PhoneNumber phoneNumber = new PhoneNumber(user.getId(), "NoPhoneNumber");
        getContext().getPhoneNumbers().add(phoneNumber);
        user.setPhoneNumberId(phoneNumber.getId());
        Email email = new Email(user.getId(), emailText);
        getContext().getEmails().add(email);
        user.setEmailId(email.getId());
        return user;
    }


    private void refreshTask(){
        if (clientHandler.getCurrentUser()!=null){
            User user = clientHandler.getCurrentUser();
            user.setLastSeen(LocalDateTime.now().toString());
            getContext().getUsers().update(user);
        }
    }



    @Override
    public LoggerController getLogger() {
        return loggerController;
    }
}
