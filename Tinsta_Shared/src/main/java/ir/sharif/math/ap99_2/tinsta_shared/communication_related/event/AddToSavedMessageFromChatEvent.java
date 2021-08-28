package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


public class AddToSavedMessageFromChatEvent extends SingleChatEvent {
    private User user;
    private String groupName;

    public AddToSavedMessageFromChatEvent() {
    }

    public AddToSavedMessageFromChatEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        SingleChatEventVisitor singleChatEventVisitor = (SingleChatEventVisitor) eventVisitor;
        return singleChatEventVisitor.addToSavedMessage(user,groupName);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
