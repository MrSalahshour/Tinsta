package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


public class DeleteMessageEvent extends ChatRoomEvent {
    private boolean savedMessage;
    private boolean chat;
    private boolean group;
    private Message message;
    private User userFrom;
    private String groupName;

    public DeleteMessageEvent() {
    }

    public DeleteMessageEvent(Object source) {
        super(source);
        savedMessage = false;
        chat = false;
        group = false;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        if (savedMessage)
            return chatRoomEventVisitor.deleteSavedMessage(message);
        if (chat)
            return chatRoomEventVisitor.deleteMessageInChat(userFrom,message);
        if (group)
            return chatRoomEventVisitor.deleteMessageInGroup(message,groupName);
        return null;
    }

    public void setSavedMessage(boolean savedMessage) {
        this.savedMessage = savedMessage;
    }

    public void setChat(boolean chat) {
        this.chat = chat;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isSavedMessage() {
        return savedMessage;
    }

    public boolean isChat() {
        return chat;
    }

    public boolean isGroup() {
        return group;
    }

    public Message getMessage() {
        return message;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public String getGroupName() {
        return groupName;
    }

}
