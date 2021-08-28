package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public interface SingleChatEventVisitor extends EventVisitor {

    Response addToSavedMessage(User user,String groupName);
    Response leftTheGroup(String groupName);
}
