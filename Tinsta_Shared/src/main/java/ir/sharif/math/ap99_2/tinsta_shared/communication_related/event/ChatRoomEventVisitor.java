package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToMultiUsersEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToOneUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToUserFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public interface ChatRoomEventVisitor extends EventVisitor {

    Response editMessage(Message message,String result);

    Response groupExist(String groupName);

    Response addUserToGP(String groupName,String userName);

    Response deleteSavedMessage(Message message);

    Response deleteMessageInChat(User userFrom, Message message);

    Response deleteMessageInGroup(Message message,String groupName);

    Response sendMessageToOne(Integer userId,SendMessageEvent sendMessageEvent);

    Response sendMessageToGroup(String groupName,SendMessageEvent sendMessageEvent);

    Response writeMessageForMyself(SendMessageEvent sendMessageEvent);

    Response showGroupChats(ShowGroupChatsEvent showGroupChatsEvent);

    Response showPrivateChats(ShowPrivateChatsEvent showPrivateChatsEvent);

    Response makeGroup(String groupName);

    Response sendMessageByUserNames(Message message, ForwardToMultiUsersEvent forwardToMultiUsersEvent);

    Response sendSingleMessage(Message message, ForwardToOneUserEvent forwardToOneUserEvent);

    Response sendMessageToUserFolder(Message message, ForwardToUserFolderEvent forwardToUserFolderEvent);

    Response sendMessageToAllUsers(Message message);

    Response deleteUnreadMessage(User userChat,int messageId);






}
