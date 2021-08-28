package ir.sharif.math.ap99_2.tinsta_server.explorer.controller;


import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Communication;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.ExplorerEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.SearchUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Email;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.PhoneNumber;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotInYourLists;

public class ExplorerController extends Controller implements GetUser, GetLogger, ExplorerEventVisitor {
    private final ClientHandler clientHandler;
    private LoggerController loggerController;


    public ExplorerController(ClientHandler clientHandler) {

        this.clientHandler = clientHandler;
    }

    public Response getCurrentTweet() {
        if (getCurrentUser().getMenu().getExplorer().getCurrentTweetId()==null)
            return new GetTweetResponse(null);
        return new GetTweetResponse(getContext().getTweets().get(getCurrentUser()
                .getMenu().getExplorer().getCurrentTweetId()));
    }

    public Response refreshExplorer() {
        User owner = getCurrentUser();
        owner.getMenu().getExplorer().getMyTweetsId().clear();
        int tweetCounts = 0;
        for (User user : getContext().getUsers().all()) {
            if (user.getMenu().getHomePage().getMyTweetsId().isEmpty())
                continue;
            if (owner.getCommunication().getMutedUsersId().contains(user.getId()) || owner.getCommunication()
                    .getBlockedUsersId().contains(user.getId()) || !user.getMenu().getSetting().isActive() || (user.getMenu()
                    .getSetting().isPrivateAccount() && !owner.getCommunication().getFollowingsId().contains(user.getId()))) {
                continue;
            }
            Tweet tweet = getContext().getTweets().get(user.getMenu().getHomePage().getMyTweetsId().getLast());
            if (tweet.getReports()>=3)
                continue;
            owner.getMenu().getExplorer().addToMyTweets(tweet.getId());
            tweetCounts++;
            if (tweetCounts == 20) {
                owner.getMenu().getExplorer().setCurrentTweetId(owner.getMenu().getExplorer().getMyTweetsId().getFirst());
                return new VoidResponse();
            }
        }
        if (tweetCounts == 0) {
            owner.getMenu().getExplorer().setCurrentTweetId(null);
            return new VoidResponse();
        }
        owner.getMenu().getExplorer().setCurrentTweetId(owner.getMenu().getExplorer().getMyTweetsId().getFirst());
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response searchUser(String origin, SearchUserEvent searchUserEvent){
        String username = searchUserEvent.getUsername();
        User user = getContext().getUsers().getByUserName(username);
        if (user == null) {
            return new ExceptionResponse(new UserNotExist("User Not Exist!"));
        }
        Communication ownerCom = getCurrentUser().getCommunication();
        if (!ownerCom.getMutedUsersId().contains(user.getId())
                && !ownerCom.getBlockedUsersId().contains(user.getId())
                && !ownerCom.getFollowingsId().contains(user.getId())
                && !ownerCom.getFollowersId().contains(user.getId())) {
            if (checkCommunityOrigin(origin)) {
                return new ExceptionResponse(new UserNotInYourLists("User is Not In Your Lists!"));
            }
        }
        return new GetUserResponse(user);
    }

    public Response followOrUnFollowUser(User user)  {
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        if (!owner.getCommunication().isFollowing(user)) {
            follow(owner,user);
            getLogger().log(LoggerController.infoLevel(), "ExplorerController",
                    "followOrUnFollowUser", userName.getUserNameText(), "User Followed another User");
        } else {
            unFollow(owner,user,userName);
            getLogger().log(LoggerController.infoLevel(), "ExplorerController",
                    "followOrUnFollowUser", userName.getUserNameText(),
                    "User UnFollowed another User");
        }
        return new VoidResponse();
    }

    public Response blockOrUnBLockUser(User user)  {
        UserName ownerUserName = getContext().getUserNames().get(getCurrentUser().getUserNameId());
        UserName userName = getContext().getUserNames().get(user.getUserNameId());
        if (!getCurrentUser().getCommunication().getBlockedUsersId().contains(user.getId())) {
            block(user,getCurrentUser(),userName,ownerUserName);
            getLogger().log(LoggerController.infoLevel(), "ExplorerController",
                    "blockOrUnBLockUser", ownerUserName.getUserNameText(),
                    "User Blocked another User");
        }
        else if (getCurrentUser().getCommunication().getBlockedUsersId().contains(user.getId())) {
            unBlock(user,getCurrentUser());
            getLogger().log(LoggerController.infoLevel(), "ExplorerController",
                    "blockOrUnBLockUser", ownerUserName.getUserNameText(),
                    "User UnBlocked another User");
        }
        return new VoidResponse();
    }

    public Response muteOrUnMuteUser(User user)  {
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        if (!owner.getCommunication().getMutedUsersId().contains(user.getId())) {
            owner.getCommunication().mute(user);
            getLogger().log(LoggerController.infoLevel(), "ExplorerController",
                    "muteOrUnMuteUser", userName.getUserNameText(),
                    "User Muted another User");
            getContext().getUsers().update(owner);
            getContext().getUsers().update(user);
            return new VoidResponse();
        }
        else if (owner.getCommunication().getMutedUsersId().contains(user.getId())) {
            owner.getCommunication().unMute(user);
            getLogger().log(LoggerController.infoLevel(), "ExplorerController",
                    "muteOrUnMuteUser", userName.getUserNameText(),
                    "User UnMuted another User");
            getContext().getUsers().update(owner);
            getContext().getUsers().update(user);
        }
        return new VoidResponse();
    }

    private void follow(User owner ,User user) {
        if (user.getCommunication().getBlockedUsersId().contains(owner.getId())) {
            return;
        }
        if (user.getMenu().getSetting().isPrivateAccount()) {
            followRequest(user,owner);
        } else {
            followUser(owner,user);
            UserName ownerUserName = getContext().getUserNames().get(owner.getUserNameId());
            String message = ownerUserName.getUserNameText() + " Followed You";
            user.getMenu().getHomePage().getProfile().getNotification().addMessages(message);
            getContext().getUsers().update(user);
        }
    }

    private void followRequest(User user,User owner) {
        if (user.getCommunication().getBlockedUsersId().contains(owner.getId()))
            return;
        user.getMenu().getHomePage().getProfile().getNotification().addFollowRequests(owner);
        getContext().getUsers().update(user);
    }

    private void followUser (User owner,User user){
        owner.getCommunication().getFollowingsId().add(user.getId());
        user.getCommunication().getFollowersId().add(owner.getId());
        getContext().getUsers().update(owner);
        getContext().getUsers().update(user);
    }

    private void unFollow(User owner ,User user, UserName ownerUserName) {
        Integer userId = user.getId();
        Integer ownerId = owner.getId();
        owner.getCommunication().getFollowingsId().remove(userId);
        getContext().getUsers().update(owner);
        user.getCommunication().getFollowersId().remove(ownerId);
        String message = ownerUserName.getUserNameText() + " UnFollowed You";
        user.getMenu().getHomePage().getProfile().getNotification().addMessages(message);
        getContext().getUsers().update(user);

    }

    private void block(User user,User owner,UserName userName,UserName ownerUserName) {
        owner.getCommunication().getBlockedUsersId().add(user.getId());
        unFollow(owner,user,ownerUserName);
        getContext().getUsers().update(owner);
        unFollow(user,owner,userName);
        getContext().getUsers().update(user);
    }

    public void unBlock(User user,User owner) {
        Integer userId = user.getId();
        owner.getCommunication().getBlockedUsersId().remove(userId);
        getContext().getUsers().update(owner);
        getContext().getUsers().update(user);
    }

    public Response getUserProfileTextContent(User user) {
        User owner = getCurrentUser();
        String content="";
        content+="Name : " + user.getName()+"\n";
        content+="LastName : " + user.getLastName()+"\n";
        UserName userName = getContext().getUserNames().get(user.getUserNameId());
        content+="UserName : " + userName.getUserNameText()+"\n";
        if (user.getCommunication().getBlockedUsersId().contains(owner.getId())) {
            content+="This User Has Blocked You"+"\n";
            return new GetStringResponse(content);
        }
        if(user.isOnline()){
            content+="Last seen: " +"Online"+"\n";
        }
        else if (user.getMenu().getSetting().isShowLastSeenToAll()){
            content+="Last seen: " + user.getLastSeen()+"\n";}
        else if (user.getMenu().getSetting().isShowLastSeenToFollowers()) {
            if (owner.getCommunication().isFollower(user))
                content+="Last seen: " + user.getLastSeen()+"\n";
            else
                content+="Last seen: last seen Recently"+"\n";
        }
        else if (user.getMenu().getSetting().isShowLastSeenToNoOne())
            content+="Last seen: last seen Recently"+"\n";
        if (owner.getCommunication().getFollowingsId().contains(user.getId()))
            content+="Followed by You"+"\n";
        else content+="Not Followed by You"+"\n";
        if (owner.getCommunication().getBlockedUsersId().contains(user.getId()))
            content+="Blocked By You"+"\n";
        if (owner.getCommunication().getMutedUsersId().contains(user.getId()))
            content+="Muted By You"+"\n";
        if (!user.getBirthday().isPrivacy()) {
            if (user.getBirthday().getYear().equals("1"))
                content+="Birthday: None"+"\n";
            else
                content+="Birthday: " + user.getBirthday().toString()+"\n";
        }
        Email email = getContext().getEmails().get(user.getEmailId());
        if (!email.isPrivacy())
            content+="Email: " + email.getEmailText()+"\n";
        PhoneNumber phoneNumber = getContext().getPhoneNumbers().get(user.getPhoneNumberId());
        if (!phoneNumber.isPrivacy())
            content+="PhoneNumber: " + phoneNumber.getPhoneNumberText()+"\n";
        return new GetStringResponse(content);

    }


    public boolean checkCommunityOrigin(String origin) {
        return origin.equals("Community");
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
