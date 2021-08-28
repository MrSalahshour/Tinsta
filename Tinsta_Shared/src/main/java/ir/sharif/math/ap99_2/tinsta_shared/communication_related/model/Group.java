package ir.sharif.math.ap99_2.tinsta_shared.communication_related.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import java.util.LinkedList;

@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Group.class)
public class Group {
    private String groupName;
    private Integer userId;
    private LinkedList<Integer> allUsersId;
    private LinkedList<Integer> messagesId;
    private Integer currentMessageId;

    public Group(String groupName, Integer userId) {
        this.groupName = groupName;
        this.userId = userId;
        allUsersId = new LinkedList<>();
        messagesId = new LinkedList<>();
        currentMessageId = null;
    }

    public Group() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LinkedList<Integer> getAllUsersId() {
        return allUsersId;
    }

    public void setAllUsersId(LinkedList<Integer> allUsersId) {
        this.allUsersId = allUsersId;
    }

    public LinkedList<Integer> getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(LinkedList<Integer> messagesId) {
        this.messagesId = messagesId;
    }

    public Integer getCurrentMessageId() {
        return currentMessageId;
    }

    public void setCurrentMessageId(Integer currentMessageId) {
        this.currentMessageId = currentMessageId;
    }
    public boolean hasPreviousMessage() {
        if (messagesId.isEmpty())
            return false;
        return messagesId.indexOf(currentMessageId) != 0;
    }

    public boolean hasNextMessage() {
        if (messagesId.isEmpty())
            return false;
        return !(messagesId.indexOf(currentMessageId) == messagesId.size() - 1);
    }

    public void goNextMessage() {
        setCurrentMessageId(messagesId.get(messagesId.indexOf(getCurrentMessageId()) + 1));
    }

    public void goPreviousMessage() {
        setCurrentMessageId(messagesId.get(messagesId.indexOf(getCurrentMessageId()) - 1));
    }
    public void addToMessagesId(Integer messageId) {
        messagesId.add(messageId);

    }
    public void addToUsersId(Integer userId){
        allUsersId.add(userId);
    }

    public void removeFromMessageId(Integer messageId){
        messagesId.remove(messageId);
    }


}
