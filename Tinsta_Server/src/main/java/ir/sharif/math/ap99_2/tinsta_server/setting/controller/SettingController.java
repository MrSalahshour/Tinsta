package ir.sharif.math.ap99_2.tinsta_server.setting.controller;



import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.SingleChat;
import ir.sharif.math.ap99_2.tinsta_shared.notification.model.Notification;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.response.VoidResponse;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ChangePasswordEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.DeleteAccountEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.SettingEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.setting.model.Setting;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.model.Timeline;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Email;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.PhoneNumber;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.WrongPassword;

import java.util.HashMap;
import java.util.LinkedList;


public class SettingController extends Controller implements GetUser, GetLogger, SettingEventVisitor {
    private final ClientHandler clientHandler;
    private LoggerController loggerController;

    public SettingController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public Response makePagePrivate(){
        UserName userName = getContext().getUserNames().get(getCurrentUser().getUserNameId());
        changePrivateAccount(true);
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "makePagePrivate", userName.getUserNameText(), "User Changed to Private Page");
        return new VoidResponse();
    }

    public Response makePagePublic(){
        UserName userName = getContext().getUserNames().get(getCurrentUser().getUserNameId());
        changePrivateAccount(false);
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "makePagePublic", userName.getUserNameText(), "User Changed to Public Page");
        return new VoidResponse();

    }

    public Response showLastSeenToAll(){
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getSetting().setShowLastSeenToAll(true);
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "showLastSeenToAll", userName.getUserNameText(), "User show Last Seen to all ");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response showLastSeenToFollowers(){
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getSetting().setShowLastSeenToFollowers(true);
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "showLastSeenToFollowers", userName.getUserNameText(), "User show Last Seen to followers ");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response showLastSeenToNoOne(){
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getSetting().setShowLastSeenToNoOne(true);
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "showLastSeenToNoOne", userName.getUserNameText(), "User show Last Seen to no one ");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response changePassword(ChangePasswordEvent changePasswordEvent) {
        String currentPassword = changePasswordEvent.getCurrentPassword();
        User owner = getCurrentUser();
        if (!owner.getPassword().equals(currentPassword)) {
            return new ExceptionResponse(new WrongPassword("Wrong Password!"));
        }
        String newPassword = changePasswordEvent.getNewPassword();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getSetting().changePassword(newPassword,owner);
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "changePassword", userName.getUserNameText(), "User Changed Password ");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }


    public Response deActiveAccount() {
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        owner.getMenu().getSetting().setActive(false);
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "deActiveAccount", userName.getUserNameText(), " User DeActivated ");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response deleteAccount(DeleteAccountEvent deleteAccountEvent) {
        String password = deleteAccountEvent.getPassword();
        User owner = getCurrentUser();
        if (!owner.getPassword().equals(password)){
            return new ExceptionResponse(new WrongPassword("Wrong Password!"));
        }
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "SettingController",
                "deleteAccount", userName.getUserNameText(), " User deleted his or her Account ");
        deleteUser(owner);
        return new VoidResponse();
    }

    private void changePrivateAccount(boolean privateAccount) {
        User owner = getCurrentUser();
        owner.getMenu().getSetting().setPrivateAccount(privateAccount);
        getContext().getUsers().update(owner);
    }

    private void deleteUser(User user) {
        UserName userName = getContext().getUserNames().get(user.getUserNameId());
        getContext().getUserNames().remove(userName);
        Email email = getContext().getEmails().get(user.getEmailId());
        getContext().getEmails().remove(email);
        PhoneNumber phoneNumber = getContext().getPhoneNumbers().get(user.getPhoneNumberId());
        getContext().getPhoneNumbers().remove(phoneNumber);
        Integer userId = user.getId();
        for (Group group: user.getMenu().getChatRoom().getMultiChat().getGroupChats()) {
            for (Integer usersId : group.getAllUsersId()) {
                User tmpUser = getContext().getUsers().get(usersId);
                Group userGP = tmpUser.getMenu().getChatRoom().getMultiChat().getGroup(group.getGroupName());
                userGP.getAllUsersId().remove(userId);
            }
        }
        for (User User: getContext().getUsers().all()) {
            User.getCommunication().getBlockedUsersId().remove(userId);
            User.getCommunication().getFollowersId().remove(userId);
            User.getCommunication().getFollowingsId().remove(userId);
            User.getCommunication().getMutedUsersId().remove(userId);
            handleDeleted(user,User);
            for (HashMap.Entry<String,LinkedList<Integer>> entry : User.
                    getCommunication().getUserManagement().entrySet()){
                entry.getValue().remove(userId);
            }
            Notification notification = User.getMenu().getHomePage().getProfile().getNotification();
            notification.getFollowRequestsUserId().remove(userId);
            if (userId.equals(notification.getCurrentFollowRequestId())){
                if (notification.getFollowRequestsUserId().size()==0)
                    notification.setCurrentFollowRequestId(null);
                else
                    notification.setCurrentFollowRequestId(notification.getFollowRequestsUserId().getLast());
            }
            getContext().getUsers().update(User);
        }
        clientHandler.setCurrentUser(null);
        getContext().getUsers().remove(user);
    }
    private void handleDeleted(User owner,User user){
        for (Integer tweetId: owner.getMenu().getHomePage().getMyTweetsId()) {
            Timeline timeline = user.getMenu().getTimeline();
            if (timeline.getCurrentTweetId().equals(tweetId)){
                if (timeline.getMyTweetsId().size()!=0)
                timeline.setCurrentTweetId(timeline.getMyTweetsId().getLast());
                else
                    timeline.setCurrentTweetId(null);
            }
        }
        SingleChat singleChat = user.getMenu().getChatRoom().searchChats(owner.getId());
        if (singleChat != null){
            user.getMenu().getChatRoom().getMySingleChats().remove(singleChat);
        }
        ChatRoom chatroom = user.getMenu().getChatRoom();
        Integer ownerId = owner.getId();
        chatroom.getMySingleChats().remove(chatroom.searchChats(ownerId));
        for (Group group: chatroom.getMultiChat().getGroupChats()) {
            group.getAllUsersId().remove(ownerId);
            for (int i = 0; i <group.getMessagesId().size() ; i++) {
                Message message = getMessage(group.getMessagesId().get(i));
                if (message.getOwnerId()==owner.getId()){
                    group.getMessagesId().remove((Integer) message.getId());
                    if (group.getMessagesId().size()!=0)
                        group.setCurrentMessageId(group.getMessagesId().getLast());
                    else
                        group.setCurrentMessageId(null);
                    i--;
                }
            }
            getContext().getUsers().update(user);
        }

    }
    private Message getMessage(int MessageId) {
        Message main;
        Message message = getContext().getMessages().get(MessageId);
        Tweet tweet = getContext().getTweets().get(MessageId);
        Comment comment = getContext().getComments().get(MessageId);
        if (message != null)
            main = message;
        else if (tweet != null)
            main = tweet;
        else
            main = comment;
        return main;
    }

    public Response getPrivacyStatus(){
        Setting setting = getCurrentUser().getMenu().getSetting();
        if (setting.isPrivateAccount())
            return new GetStringResponse("Privacy: Private");
        else
            return new GetStringResponse("Privacy: Public");
    }

    public Response getLastSeenStatus(){
        Setting setting = getCurrentUser().getMenu().getSetting();
        if (setting.isShowLastSeenToAll())
            return new GetStringResponse("LastSeen: Show LastSeen To All");
        if (setting.isShowLastSeenToFollowers())
            return new GetStringResponse("LastSeen: Show LastSeen To Followers");
        if (setting.isShowLastSeenToNoOne())
            return new GetStringResponse("LastSeen: Show LastSeen To No One");
        return new GetStringResponse("");
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
