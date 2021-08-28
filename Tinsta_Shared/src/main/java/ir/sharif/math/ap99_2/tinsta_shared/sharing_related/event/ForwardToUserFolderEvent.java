package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;


public class ForwardToUserFolderEvent extends ChatRoomEvent {
    private String userFolder;
    private Message message;

    public ForwardToUserFolderEvent(Object source, String userFolder) {
        super(source);
        this.userFolder = userFolder;
    }

    public ForwardToUserFolderEvent() {
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getUserFolder() {
        return userFolder;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.sendMessageToUserFolder(message,this);
    }

    public void setUserFolder(String userFolder) {
        this.userFolder = userFolder;
    }

    public Message getMessage() {
        return message;
    }
}
