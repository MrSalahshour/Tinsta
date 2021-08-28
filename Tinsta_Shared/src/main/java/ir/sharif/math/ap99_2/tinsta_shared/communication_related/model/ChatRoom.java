package ir.sharif.math.ap99_2.tinsta_shared.communication_related.model;


import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


import java.util.LinkedList;

public class ChatRoom {
    private LinkedList<Integer> savedMessagesId;
    private LinkedList<SingleChat> mySingleChats;
    private Integer currentSavedMessageId;
    private MultiChat multiChat;
    private int ID;

    public ChatRoom() {
        this.mySingleChats = new LinkedList<>();
        this.multiChat = new MultiChat();
        this.savedMessagesId = new LinkedList<>();
        this.ID =(int) (System.currentTimeMillis() & 0xfffffff);
    }



    public void setSavedMessagesId(LinkedList<Integer> savedMessagesId) {
        this.savedMessagesId = savedMessagesId;
    }

    public void setMySingleChats(LinkedList<SingleChat> mySingleChats) {
        this.mySingleChats = mySingleChats;
    }

    public void setMultiChat(MultiChat multiChat) {
        this.multiChat = multiChat;
    }

    public Integer getCurrentSavedMessageId() {
        return currentSavedMessageId;
    }

    public void setCurrentSavedMessageId(Integer currentSavedMessageId) {
        this.currentSavedMessageId = currentSavedMessageId;
    }

    public MultiChat getMultiChat() {
        return multiChat;
    }

    public LinkedList<Integer> getSavedMessagesId() {
        return savedMessagesId;
    }

    public LinkedList<SingleChat> getMySingleChats() {
        return mySingleChats;
    }

    public boolean canStartChat(User user, User owner) {
        if (user==null)
            return false;
        if (owner.getCommunication().getBlockedUsersId().contains(user.getId())
                || user.getCommunication().getBlockedUsersId().
                contains(owner.getId()))
            return false;
        return owner.getCommunication().isFollower(user)
                || user.getCommunication().isFollower(owner);
    }

    public void startChat(User user,User owner) {
        if (canStartChat(user,owner)) {
            getMySingleChats().add(new SingleChat(user.getId()));
            user.getMenu().getChatRoom().getMySingleChats().
                    add(new SingleChat(owner.getId()));
        }

    }

    public SingleChat searchChats(Integer userId) {
        for (SingleChat mySingleChat : mySingleChats) {
            int user1 = mySingleChat.getUserFromId();
            int user2 = userId;
            if (user1 == user2){
                return mySingleChat;
            }
        }
        return null;
    }

    public boolean hasChatWith(Integer userId) {
        if (userId==null)
            return false;
        for (SingleChat mySingleChat : mySingleChats) {
            if (mySingleChat.getUserFromId().equals(userId))
                return true;
        }
        return false;
    }

    public void addToSavedMessage(Message message,User owner) {
        message.addToSavedMessages(owner);
    }

    public void deleteSavedMessage(Message message){
        savedMessagesId.removeIf(messageId -> message.getId() == messageId);
        if (savedMessagesId.size()==0)
            setCurrentSavedMessageId(null);
        else
            setCurrentSavedMessageId(savedMessagesId.getLast());
    }
    public boolean hasNextSavedMessage() {
        if (savedMessagesId.isEmpty())
            return false;
        return !(savedMessagesId.indexOf(currentSavedMessageId) == savedMessagesId.size() - 1);
    }
    public boolean hasPreviousSavedMessage() {
        if (savedMessagesId.isEmpty())
            return false;
        return !(savedMessagesId.indexOf(currentSavedMessageId) == 0);
    }

    public void goNextSavedMessage() {
        if (hasNextSavedMessage()) {
            setCurrentSavedMessageId(savedMessagesId.get(savedMessagesId.indexOf(currentSavedMessageId) + 1));
        }
    }


    public void goPreviousSavedMessage() {
        if (hasPreviousSavedMessage())
            setCurrentSavedMessageId(savedMessagesId.get(savedMessagesId.indexOf(currentSavedMessageId) - 1));
    }

    public void writeMsgForMySelf(Message message) {
        savedMessagesId.add(message.getId());
        setCurrentSavedMessageId(message.getId());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
