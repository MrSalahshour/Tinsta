package ir.sharif.math.ap99_2.tinsta_shared.communication_related.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


import java.io.Serializable;
import java.util.LinkedList;

@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = SingleChat.class)
public class SingleChat implements Serializable {
    private Integer userFromId;
    private LinkedList<Integer> allMessagesId;
    private LinkedList<Integer> myUnreadMessagesId;
    private Integer currentMessageId;

    public SingleChat(Integer userFromId) {
        this.userFromId = userFromId;
        this.allMessagesId = new LinkedList<>();
        this.myUnreadMessagesId = new LinkedList<>();
        this.currentMessageId = null;
    }

    public SingleChat() {
    }

    public void setUserFromId(Integer userFromId) {
        this.userFromId = userFromId;
    }


    public void setAllMessagesId(LinkedList<Integer> allMessagesId) {
        this.allMessagesId = allMessagesId;
    }

    public void setMyUnreadMessagesId(LinkedList<Integer> myUnreadMessagesId) {
        this.myUnreadMessagesId = myUnreadMessagesId;
    }

    public Integer getCurrentMessageId() {
        return currentMessageId;
    }

    public void setCurrentMessageId(Integer currentMessageId) {
        this.currentMessageId = currentMessageId;
    }

    public Integer getUserFromId() {
        return userFromId;
    }


    public LinkedList<Integer> getAllMessagesId() {
        return allMessagesId;
    }

    public LinkedList<Integer> getMyUnreadMessagesId() {
        return myUnreadMessagesId;
    }

    public void addToAllMessagesId(Message message) {
        allMessagesId.add(message.getId());

    }

    public void addToUnReadMessagesId(Message message) {
        myUnreadMessagesId.add(message.getId());
    }

    public void sendMessage(Message message, User userFrom, User owner) {
        userFrom.getMenu().getChatRoom().searchChats(owner.getId()).addToUnReadMessagesId(message);
        userFrom.getMenu().getChatRoom().searchChats(owner.getId()).addToAllMessagesId(message);
        userFrom.getMenu().getChatRoom().searchChats(owner.getId()).setCurrentMessageId(message.getId());
        owner.getMenu().getChatRoom().searchChats(userFrom.getId()).addToAllMessagesId(message);
        owner.getMenu().getChatRoom().searchChats(userFrom.getId()).setCurrentMessageId(message.getId());
    }
    public void deleteMessageInChat(Message message,User userFrom,User owner){
        Integer messageId = message.getId();
        allMessagesId.remove(messageId);
        userFrom.getMenu().getChatRoom().searchChats(owner.getId())
                .getAllMessagesId().remove(messageId);
        if (currentMessageId.equals(messageId)){
            if (allMessagesId.size()==0)
                setCurrentMessageId(null);
            else {
                setCurrentMessageId(allMessagesId.getLast());
            }
        }
        if (userFrom.getMenu().getChatRoom().searchChats(owner.getId())
                .getCurrentMessageId().equals(messageId)){
            if (userFrom.getMenu().getChatRoom().searchChats(owner.getId())
                    .getAllMessagesId().size()==0)
                setCurrentMessageId(null);
            else {
                setCurrentMessageId(userFrom.getMenu().getChatRoom()
                        .searchChats(owner.getId()).getAllMessagesId().getLast());
            }
        }
    }


    public boolean hasPreviousMessage() {
        if (allMessagesId.isEmpty())
            return false;
        return allMessagesId.indexOf(currentMessageId) != 0;
    }

    public boolean hasNextMessage() {
        if (allMessagesId.isEmpty())
            return false;
        return !(allMessagesId.indexOf(currentMessageId) == allMessagesId.size() - 1);
    }

    public void goNextMessage() {
        setCurrentMessageId(allMessagesId.get(allMessagesId.indexOf(getCurrentMessageId()) + 1));
    }

    public void goPreviousMessage() {
        setCurrentMessageId(allMessagesId.get(allMessagesId.indexOf(getCurrentMessageId()) - 1));
    }



}
