package ir.sharif.math.ap99_2.tinsta_server.communication_related.controller;



import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.response.VoidResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class CommunityController extends Controller implements GetUser, GetLogger, CommunityEventVisitor {
    private final ClientHandler clientHandler;
    private LoggerController loggerController;

    public CommunityController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public Response createFolder(CreateNewFolderEvent createNewFolderEvent) {
        User owner = getCurrentUser();
        String folderName = createNewFolderEvent.getUserFolder();
        if (owner.getCommunication().haveUserFolder(folderName)) {
            return new ExceptionResponse(new UserFolderAlreadyExist("User Folder Already Exist!"));
        }
        owner.getCommunication().createUsersFolder(folderName);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(),
                "CommunityController", "createFolder", userName.getUserNameText(), "Creates a Folder");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public String getFolder(String folderName) throws UserFolderNotExist {
        if (!getCurrentUser().getCommunication().haveUserFolder(folderName)) {
            throw new UserFolderNotExist("User Folder Not Exist");
        }
        return folderName;
    }

    public Response addUserToFolder(AddToUserFolderEvent addToUserFolderEvent) {
        User owner = getCurrentUser();
        String userFolder = addToUserFolderEvent.getUserFolder();
        if (!owner.getCommunication().haveUserFolder(userFolder)) {
            return new ExceptionResponse(new UserFolderNotExist("User Folder Not Exist!"));
        }
        String userName = addToUserFolderEvent.getUsername();

        if (!getContext().getUserNames().UserNameExist(userName)) {
            return new ExceptionResponse(new UserNotExist("User Not Exist!"));
        }
        User user = getContext().getUsers().getByUserName(userName);
        if (!owner.getCommunication().canAddToFolder(user)) {
            return new ExceptionResponse(new CantAddToUserFolder("Can't Add this User To folder"));
        }
        owner.getCommunication().addToFolder(user, userFolder);
        UserName userNameTmp = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "CommunityController", "addUserToFolder",
                userNameTmp.getUserNameText(), "Added a User to a Folder");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response removeUserFromFolder(RemoveUserFromFolderEvent removeUserFromFolderEvent) {
        User owner = getCurrentUser();
        String userFolder = removeUserFromFolderEvent.getUserFolder();
        if (!owner.getCommunication().haveUserFolder(userFolder)) {
            return new ExceptionResponse(new UserFolderNotExist("User Folder Not Exist!"));
        }
        String userName = removeUserFromFolderEvent.getUsername();

        if (!getContext().getUserNames().UserNameExist(userName)) {
            return new ExceptionResponse(new UserNotExist("User Not Exist!"));
        }
        User user = getContext().getUsers().getByUserName(userName);
        if (!owner.getCommunication().removeFromFolder(user, userFolder)) {
            return new ExceptionResponse(new UserNotExistInFolder("User Not Exist in This Folder!"));
        }
        UserName userNameTmp = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "CommunityController", "removeUserFromFolder",
                userNameTmp.getUserNameText(), "Removed a User to a Folder");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response showFollowers(ShowFollowersEvent showFollowersEvent) {
        StringBuilder followers = new StringBuilder();
        followers.append("Your Followers: \n");
        User owner = getCurrentUser();
        for (int i = 0; i < owner.getCommunication().getFollowersId().size(); i++) {
            User follower = getContext().getUsers().get(owner.getCommunication().getFollowersId().get(i));
            if(follower==null){
                continue;
            }
            if (follower.getMenu().getSetting().isActive()){
                UserName followerUN = getContext().getUserNames().get(follower.getUserNameId());
                followers.append(followerUN.getUserNameText()).append("\n");
            }
        }
        return new GetStringResponse(followers.toString());
    }

    public Response showBlackList(ShowBlackListsEvent showBlackListsEvent) {
        StringBuilder blacklists = new StringBuilder();
        blacklists.append("Your BlackList Users: \n");
        User owner = getCurrentUser();
        for (int i = 0; i < owner.getCommunication().getBlockedUsersId().size(); i++) {
            User blocked = getContext().getUsers().get(owner.getCommunication().getBlockedUsersId().get(i));
            if(blocked==null){
                continue;
            }
            if (blocked.getMenu().getSetting().isActive()){
                UserName blockedUN = getContext().getUserNames().get(blocked.getUserNameId());
                blacklists.append(blockedUN.getUserNameText()).append("\n");
            }
        }
        return new GetStringResponse(blacklists.toString());
    }

    public Response showMutedList(ShowMutedListEvent showMutedListEvent) {
        StringBuilder mutedLists= new StringBuilder();
        mutedLists.append("Your Muted Users: \n");
        User owner = getCurrentUser();
        for (int i = 0; i < owner.getCommunication().getMutedUsersId().size(); i++) {
            User muted = getContext().getUsers().get(owner.getCommunication().getMutedUsersId().get(i));
            if(muted==null){
                continue;
            }
            if (muted.getMenu().getSetting().isActive()){
                UserName mutedUN = getContext().getUserNames().get(muted.getUserNameId());
                mutedLists.append(mutedUN.getUserNameText()).append("\n");
            }
        }
        return new GetStringResponse(mutedLists.toString());
    }

    public Response showFollowings(ShowFollowingsEvent showFollowingsEvent) {
        StringBuilder followings = new StringBuilder();
        followings.append("Your Followings: \n");
        User owner = getCurrentUser();
        for (int i = 0; i < owner.getCommunication().getFollowingsId().size(); i++) {
            User following = getContext().getUsers().get(owner.getCommunication().getFollowingsId().get(i));
            if(following==null){
                continue;
            }
            if (following.getMenu().getSetting().isActive()){
                UserName followingUN = getContext().getUserNames().get(following.getUserNameId());
                followings.append(followingUN.getUserNameText()).append("\n");
            }
        }
        return new GetStringResponse(followings.toString());
    }

    public Response showFolders(ShowFoldersListEvent showFoldersListEvent) {
        StringBuilder foldersLists = new StringBuilder();
        for (Map.Entry<String, LinkedList<Integer>> entry : getCurrentUser().getCommunication()
                .getUserManagement().entrySet()) {
            foldersLists.append("* ").append(entry.getKey()).append(" *\n");
        }
        return new GetStringResponse(foldersLists.toString());
    }

    public Response showAFolder(ShowAFolderContentEvent showAFolderContentEvent) {
        String userFolder;
        try {
            userFolder = getFolder(showAFolderContentEvent.getFolderName());
        }
        catch (UserFolderNotExist userFolderNotExist){
            return new ExceptionResponse(userFolderNotExist);
        }
        StringBuilder folderContent = new StringBuilder();
        HashMap<String, LinkedList<Integer>> userManagement = getCurrentUser().
                getCommunication().getUserManagement();
        folderContent.append("Users: \n");
        for (int i = 0; i < userManagement.get(userFolder).size(); i++) {
            User user =getContext().getUsers().get(userManagement.get(userFolder).get(i));
            if (user==null){
                continue;
            }
            UserName userName = getContext().getUserNames().get(user.getUserNameId());
            folderContent.append(userName.getUserNameText()).append("\n");
        }
        return new GetStringResponse(folderContent.toString());
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
