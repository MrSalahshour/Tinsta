package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class SendMsgEvent extends ChatRoomEvent {
    private Integer userId;
    private String groupName;
    private SendMessageEvent sendMessageEvent;

    public SendMsgEvent() {
    }

    public SendMsgEvent(Object source, Integer userId, SendMessageEvent sendMessageEvent) {
        super(source);
        this.userId = userId;
        this.sendMessageEvent = sendMessageEvent;
    }

    public SendMsgEvent(Object source, String groupName, SendMessageEvent sendMessageEvent) {
        super(source);
        this.groupName = groupName;
        this.sendMessageEvent = sendMessageEvent;
    }

    public SendMsgEvent(Object source, SendMessageEvent sendMessageEvent) {
        super(source);
        this.sendMessageEvent = sendMessageEvent;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        if (userId != null){
            return chatRoomEventVisitor.sendMessageToOne(userId,sendMessageEvent);
        }
        else if (groupName!=null){
            return chatRoomEventVisitor.sendMessageToGroup(groupName,sendMessageEvent);
        }
        else {
            return chatRoomEventVisitor.writeMessageForMyself(sendMessageEvent);
        }

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public SendMessageEvent getSendMessageEvent() {
        return sendMessageEvent;
    }

    public void setSendMessageEvent(SendMessageEvent sendMessageEvent) {
        this.sendMessageEvent = sendMessageEvent;
    }
}
