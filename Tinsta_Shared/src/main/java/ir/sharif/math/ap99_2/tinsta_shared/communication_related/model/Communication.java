package ir.sharif.math.ap99_2.tinsta_shared.communication_related.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Communication.class)
public class Communication implements Serializable {
    private Integer userId;
    private LinkedList<Integer> followersId;
    private LinkedList<Integer> followingsId;
    private LinkedList<Integer> blockedUsersId;
    private LinkedList<Integer> mutedUsersId;
    private HashMap<String, LinkedList<Integer>> userManagement;


    public Communication(Integer userId) {
        this.userId = userId;
        this.followersId = new LinkedList<>();
        this.followingsId = new LinkedList<>();
        this.blockedUsersId = new LinkedList<>();
        this.mutedUsersId = new LinkedList<>();
        this.userManagement = new HashMap<>();
    }

    public Communication() {
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFollowersId(LinkedList<Integer> followersId) {
        this.followersId = followersId;
    }

    public void setFollowingsId(LinkedList<Integer> followingsId) {
        this.followingsId = followingsId;
    }

    public void setBlockedUsersId(LinkedList<Integer> blockedUsersId) {
        this.blockedUsersId = blockedUsersId;
    }

    public void setMutedUsersId(LinkedList<Integer> mutedUsersId) {
        this.mutedUsersId = mutedUsersId;
    }

    public void setUserManagement(HashMap<String, LinkedList<Integer>> userManagement) {
        this.userManagement = userManagement;
    }

    public HashMap<String, LinkedList<Integer>> getUserManagement() {
        return userManagement;
    }

    public Integer getUserId() {
        return userId;
    }

    public LinkedList<Integer> getFollowersId() {
        return followersId;
    }

    public LinkedList<Integer> getFollowingsId() {
        return followingsId;
    }

    public LinkedList<Integer> getBlockedUsersId() {
        return blockedUsersId;
    }

    public LinkedList<Integer> getMutedUsersId() {
        return mutedUsersId;
    }

    public boolean isFollower(User user) {
        return this.getFollowersId().contains(user.getId());
    }

    public boolean isFollowing(User user) {
        return this.getFollowingsId().contains(user.getId());
    }

    public void mute(User user) {
        getMutedUsersId().add(user.getId());
    }

    public void unMute(User user) {
        Integer userId = user.getId();
        getMutedUsersId().remove(userId);
    }

    public void createUsersFolder(String usersFolder) {
        getUserManagement().put(usersFolder, new LinkedList<>());
    }

    public void addToFolder(User user, String userFolder) {
        Integer userId = user.getId();
        if (canAddToFolder(user) && haveUserFolder(userFolder))
            getUserManagement().get(userFolder).add(userId);
    }

    public boolean canAddToFolder(User user) {
        for (Integer following : followingsId) {
            if (following.equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean removeFromFolder(User user, String userFolder) {
        Integer userId = user.getId();
        if (getUserManagement().get(userFolder).contains(userId)){
            getUserManagement().get(userFolder).remove(userId);
            return true;
        }
        return false;
    }

    public boolean haveUserFolder(String userFolder) {
        return userManagement.containsKey(userFolder);
    }



}
