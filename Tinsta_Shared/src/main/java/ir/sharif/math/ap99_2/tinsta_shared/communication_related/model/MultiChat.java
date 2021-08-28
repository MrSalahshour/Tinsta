package ir.sharif.math.ap99_2.tinsta_shared.communication_related.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


import java.io.Serializable;
import java.util.LinkedList;

@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = MultiChat.class)
public class MultiChat implements Serializable {
    private LinkedList<Integer> currentSendListUsersId;
    private LinkedList<Group> groupChats;


    public MultiChat() {
        this.currentSendListUsersId = new LinkedList<>();
        this.groupChats = new LinkedList<>();
    }

    public void setCurrentSendListUsersId(LinkedList<Integer> currentSendListUsersId) {
        this.currentSendListUsersId = currentSendListUsersId;
    }

    public LinkedList<Integer> getCurrentSendListUsersId() {
        return currentSendListUsersId;
    }

    public LinkedList<Group> getGroupChats() {
        return groupChats;
    }

    public void setGroupChats(LinkedList<Group> groupChats) {
        this.groupChats = groupChats;
    }

    public void makeNewGroup(String groupName,int ownerId){
        Group group = new Group(groupName,ownerId);
        group.addToUsersId(ownerId);
        groupChats.add(group);
    }
    public boolean isGroupExist(String groupName){
        for (Group group:groupChats) {
            if (group.getGroupName().equals(groupName))
                return true;
        }
        return false;
    }
    public boolean canAddToGroup(User user,Communication userCommunication){
        return userCommunication.isFollowing(user);

    }

    public Group getGroup(String groupName){
        for (Group group:groupChats) {
            if (group.getGroupName().equals(groupName))
                return group;
        }
        return null;
    }
    public void deleteMessageInGroup(String groupName, Message message, LinkedList<User> usersInGroup){
        for (User user:usersInGroup) {
            Group group = user.getMenu().getChatRoom().getMultiChat().getGroup(groupName);
            group.removeFromMessageId(message.getId());
            if (group.getMessagesId().size()==0)
                group.setCurrentMessageId(null);
            else {
                group.setCurrentMessageId(group.getMessagesId().getLast());
            }
        }
    }

    public void clearCurrentSendList() {
        getCurrentSendListUsersId().clear();
    }





}
