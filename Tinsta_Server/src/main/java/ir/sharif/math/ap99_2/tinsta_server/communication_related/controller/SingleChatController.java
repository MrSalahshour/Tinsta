package ir.sharif.math.ap99_2.tinsta_server.communication_related.controller;


import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Context;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SingleChatEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.MultiChat;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveChatRoomResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.VoidResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.CantAddUserToGP;

import java.util.LinkedList;

public class SingleChatController extends Controller implements GetUser, GetLogger, SingleChatEventVisitor {

    private final ChatRoomController chatRoomController;

    public SingleChatController(ClientHandler clientHandler) {
        this.chatRoomController = new ChatRoomController(clientHandler);
    }


    public Response addToSavedMessage(User user, String groupName) {
        User owner = getCurrentUser();
        if (user!=null){
            Message message = getContext().getMessages().get(owner.getMenu()
                    .getChatRoom().searchChats(user.getId()).getCurrentMessageId());
            owner.getMenu().getChatRoom().addToSavedMessage(message,owner);
            getContext().getUsers().update(owner);
            getContext().getUsers().update(user);
        }
        else {
            Group group = owner.getMenu().getChatRoom().getMultiChat().getGroup(groupName);
            Message message = getContext().getMessages().get(group.getCurrentMessageId());
            owner.getMenu().getChatRoom().addToSavedMessage(message,owner);
            getContext().getUsers().update(owner);
        }
        return new SaveChatRoomResponse(owner.getMenu().getChatRoom());

    }

    @Override
    public Response leftTheGroup(String groupName) {
        User owner = getCurrentUser();
        MultiChat multiChat = owner.getMenu().getChatRoom().getMultiChat();
        Group group = multiChat.getGroup(groupName);
        LinkedList<User> usersInGP = new LinkedList<>();
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        for (Integer userId : group.getAllUsersId()) {
            usersInGP.add(getContext().getUsers().get(userId));
        }
        Integer ownerId= owner.getId();
        for (User gUser:usersInGP) {
            if (gUser.getId()!=owner.getId()){
                gUser.getMenu().getChatRoom().getMultiChat().getGroup(groupName)
                        .getAllUsersId().remove(ownerId);
            }
        }
        owner.getMenu().getChatRoom().getMultiChat().getGroupChats().remove(group);

        for (User userInGP : usersInGP) {
            if (userInGP.getId()!=owner.getId()){
                getContext().getUsers().update(userInGP);
                chatRooms.add(userInGP.getMenu().getChatRoom());
            }
        }
        getContext().getUsers().update(owner);
        chatRooms.add(owner.getMenu().getChatRoom());
        return new SaveChatRoomResponse(chatRooms);
    }

    @Override
    public User getCurrentUser() {
        return chatRoomController.getCurrentUser();
    }

    @Override
    public LoggerController getLogger() {
        return chatRoomController.getLogger();
    }
}
