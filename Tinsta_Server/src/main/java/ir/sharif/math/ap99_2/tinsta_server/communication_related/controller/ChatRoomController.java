package ir.sharif.math.ap99_2.tinsta_server.communication_related.controller;


import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_server.sharing_related.controller.MessageController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SendMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowGroupChatsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ShowPrivateChatsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Group;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.MultiChat;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.SingleChat;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToMultiUsersEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToOneUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToUserFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.CantAddUserToGP;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;

import java.util.LinkedList;
import java.util.Map;


public class ChatRoomController extends Controller implements GetUser, GetLogger, ChatRoomEventVisitor {

    private final MessageController messageController;
    private final ClientHandler clientHandler;
    private final ChatRoomHandleController chatRoomHandleController;
    private LoggerController loggerController;

    public ChatRoomController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.messageController = new MessageController(this);
        this.chatRoomHandleController = new ChatRoomHandleController();
    }

    public MessageController getMessageController() {
        return messageController;
    }

    public Response sendMessageToGroup(String groupName, SendMessageEvent sendMessageEvent){
        User owner = getCurrentUser();
        Group group = owner.getMenu().getChatRoom().getMultiChat().getGroup(groupName);
        LinkedList<Integer> usersId = group.getAllUsersId();
        LinkedList<User> users = new LinkedList<>();
        for (Integer id : usersId) {
            users.add(getContext().getUsers().get(id));
        }
        Message message = getMessageController().makeMessage(sendMessageEvent);
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        for (User user:users) {
            if (user.getId()!=owner.getId()){
                user.getMenu().getChatRoom().getMultiChat()
                        .getGroup(groupName).addToMessagesId(message.getId());
                user.getMenu().getChatRoom().getMultiChat()
                        .getGroup(groupName).setCurrentMessageId(message.getId());
                getContext().getUsers().update(user);
                chatRooms.add(user.getMenu().getChatRoom());
            }
            else {
                group.addToMessagesId(message.getId());
                group.setCurrentMessageId(message.getId());
                getContext().getUsers().update(owner);
                chatRooms.add(owner.getMenu().getChatRoom());
            }
        }
        SaveMessageResponse saveMessageResponse = new SaveMessageResponse(message);
        saveMessageResponse.setChatRooms(chatRooms);
        return saveMessageResponse;
    }

    public Response writeMessageForMyself(SendMessageEvent sendMessageEvent) {
        User owner = getCurrentUser();
        Message message = messageController.makeMessage(sendMessageEvent);
        owner.getMenu().getChatRoom().writeMsgForMySelf(message);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "writeMessageForMyself",
                userName.getUserNameText(), "User Wrote a message for his or her own");
        getContext().getUsers().update(owner);
        SaveMessageResponse saveMessageResponse = new SaveMessageResponse(message);
        saveMessageResponse.setChatRoom(owner.getMenu().getChatRoom());
        return saveMessageResponse;
    }

    public Response deleteMessageInGroup(Message message,String groupName){
        User owner = getCurrentUser();
        Group group = owner.getMenu().getChatRoom().getMultiChat().getGroup(groupName);
        LinkedList<User> users = new LinkedList<>();
        for (Integer userId:group.getAllUsersId()) {
            users.add(getContext().getUsers().get(userId));
        }
        MultiChat multiChat = owner.getMenu().getChatRoom().getMultiChat();
        multiChat.deleteMessageInGroup(groupName,message,users);
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        for (User user:users) {
            getContext().getUsers().update(user);
            chatRooms.add(user.getMenu().getChatRoom());
        }
        if (!chatRooms.contains(owner.getMenu().getChatRoom()))
            chatRooms.add(owner.getMenu().getChatRoom());
        getContext().getUsers().update(owner);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "deleteMessageInGroup",
                userName.getUserNameText(), "User deleted a message in group");
        return new SaveChatRoomResponse(chatRooms);
    }

    public Response deleteMessageInChat(User userFrom,Message message){
        User owner = getCurrentUser();
        SingleChat singleChat = owner.getMenu().getChatRoom().searchChats(userFrom.getId());
        singleChat.deleteMessageInChat(message,userFrom,owner);
        getContext().getUsers().update(owner);
        getContext().getUsers().update(userFrom);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "deleteMessageInChat",
                userName.getUserNameText(), "User deleted a message in chat");
        LinkedList <ChatRoom> chatRooms = new LinkedList<>();
        chatRooms.add(owner.getMenu().getChatRoom());
        chatRooms.add(userFrom.getMenu().getChatRoom());
        return new SaveChatRoomResponse(chatRooms);
    }

    public Response deleteSavedMessage(Message message){
        User owner = getCurrentUser();
        ChatRoom chatRoom = owner.getMenu().getChatRoom();
        chatRoom.deleteSavedMessage(message);
        getContext().getUsers().update(owner);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "deleteSavedMessage",
                userName.getUserNameText(), "User deleted a message in saved message");
        return new SaveChatRoomResponse(chatRoom);
    }

    public Response editMessage(Message message, String content){
        message.setContent(content);
        getContext().getMessages().update(message);
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "editMessage",
                userName.getUserNameText(), "User edited a message ");
        return new SaveMessageResponse(message);
    }

    public Response sendSingleMessage(Message message, ForwardToOneUserEvent forwardToOneUserEvent) {
        String username = forwardToOneUserEvent.getUsername();
        if (!getContext().getUserNames().UserNameExist(username)) {
            return new ExceptionResponse(new UserNotExist("User Not Exist!"));
        }
        User user = getContext().getUsers().getByUserName(username);
        User owner = getCurrentUser();
        if (owner.getMenu().getChatRoom().searchChats(user.getId()) != null) {
            owner.getMenu().getChatRoom().searchChats(user.getId()).sendMessage(message,user,owner);
            return handleForwardToOneUser(user, owner);
        }
        if (owner.getMenu().getChatRoom().canStartChat(user,owner)) {
            owner.getMenu().getChatRoom().startChat(user,owner);
            owner.getMenu().getChatRoom().searchChats(user.getId()).sendMessage(message,user,owner);
            return handleForwardToOneUser(user, owner);
        }
        return new VoidResponse();
    }

    private Response handleForwardToOneUser(User user, User owner) {
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "sendSingleMessage",
                userName.getUserNameText(), "User sent a message for another user");
        getContext().getUsers().update(owner);
        getContext().getUsers().update(user);
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        chatRooms.add(owner.getMenu().getChatRoom());
        chatRooms.add(user.getMenu().getChatRoom());
        SaveChatRoomResponse saveChatRoomResponse = new SaveChatRoomResponse();
        saveChatRoomResponse.setChatRooms(chatRooms);
        return saveChatRoomResponse;
    }

    public Response sendMessageToAllUsers(Message message) {
        chatRoomHandleController.addToSendListForAllUsers(getCurrentUser(),getContext());
        LinkedList<ChatRoom> chatRooms = chatRoomHandleController
                .sendMultiMessage(message,getCurrentUser(),getContext());
        UserName userName = getContext().getUserNames().get(getCurrentUser().getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "sendMessageToAlUsers",
                userName.getUserNameText(), "User sent a message for all users");
        return new SaveChatRoomResponse(chatRooms);
    }

    @Override
    public Response deleteUnreadMessage(User user, int messageId) {
        Integer MessageId = messageId;
        User owner = getCurrentUser();
        owner.getMenu().getChatRoom().searchChats(user.getId()).getMyUnreadMessagesId().remove(MessageId);
        getContext().getUsers().update(owner);
        return new SaveChatRoomResponse(owner.getMenu().getChatRoom());
    }

    public Response sendMessageToUserFolder(Message message, ForwardToUserFolderEvent forwardToUserFolderEvent) {
        String folder = forwardToUserFolderEvent.getUserFolder();
        User owner = getCurrentUser();
        if (!owner.getCommunication().haveUserFolder(folder)) {
            return new ExceptionResponse(new UserFolderNotExist("User Folder Not Exist"));
        }
        chatRoomHandleController.addToSendListByUserFolder(folder,getCurrentUser(),getContext());
        LinkedList<ChatRoom> chatRooms = chatRoomHandleController
                .sendMultiMessage(message,getCurrentUser(),getContext());
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "sendMessageToUserFolder",
                userName.getUserNameText(), "User sent a message for all users in a folder");
        return new SaveChatRoomResponse(chatRooms);
    }

    public Response sendMessageByUserNames(Message message, ForwardToMultiUsersEvent forwardToMultiUsersEvent) {
        User owner = getCurrentUser();
        owner.getMenu().getChatRoom().getMultiChat().clearCurrentSendList();
        for (int i = 0; i < forwardToMultiUsersEvent.getUsernames().size(); i++) {
            String username = forwardToMultiUsersEvent.getUsernames().get(i);
            if (!getContext().getUserNames().UserNameExist(username)) {
                continue;
            }
            User user = getContext().getUsers().getByUserName(username);
            if (owner.getMenu().getChatRoom().searchChats(user.getId()) == null) {
                owner.getMenu().getChatRoom().startChat(user,owner);
            }
            chatRoomHandleController.addToSendListByUserName(username,getCurrentUser(),getContext());
        }
        LinkedList<ChatRoom> chatRooms = chatRoomHandleController
                .sendMultiMessage(message,getCurrentUser(),getContext());
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "ChatRoomController", "sendMessageByUserNames",
                userName.getUserNameText(), "User sent a message for selected Users");
        return new SaveChatRoomResponse(chatRooms);
    }

    public Response showPrivateChats(ShowPrivateChatsEvent showPrivateChatsEvent) {
        StringBuilder privateChats = new StringBuilder();
        privateChats.append("Your Chats: \n");
        User owner = getCurrentUser();
        for (SingleChat mySingleChat : owner.getMenu().getChatRoom().getMySingleChats()) {
            if (!mySingleChat.getMyUnreadMessagesId().isEmpty()){
                User user = getContext().getUsers().get(mySingleChat.getUserFromId());
                if (user==null)
                    continue;
                UserName userName = getContext().getUserNames().get(user.getUserNameId());
                privateChats.append(userName.getUserNameText()).append(" *")
                        .append(mySingleChat.getMyUnreadMessagesId().size()).append(" Unread Messages*\n");
            }

        }
        for (SingleChat mySingleChat : owner.getMenu().getChatRoom().getMySingleChats()) {
            if (mySingleChat.getMyUnreadMessagesId().isEmpty()){
                User user = getContext().getUsers().get(mySingleChat.getUserFromId());
                UserName userName = getContext().getUserNames().get(user.getUserNameId());
                privateChats.append(userName.getUserNameText()).append("\n");
            }
        }
        return new GetStringResponse(privateChats.toString());
    }

    public Response showGroupChats(ShowGroupChatsEvent showGroupChatsEvent){
        StringBuilder groupChats = new StringBuilder();
        groupChats.append("Your Groups: \n");
        MultiChat multiChat = getCurrentUser().getMenu().getChatRoom().getMultiChat();
        for (Group group : multiChat.getGroupChats()) {
            groupChats.append(group.getGroupName()).append("\n");
        }
        return new GetStringResponse(groupChats.toString());
    }

    public Response groupExist(String groupName){
        MultiChat multiChat = getCurrentUser().getMenu().getChatRoom().getMultiChat();
        boolean exist =  multiChat.isGroupExist(groupName);
        return new GroupExistResponse(exist);
    }

    public Response makeGroup(String groupName){
        User owner = getCurrentUser();
        MultiChat multiChat = owner.getMenu().getChatRoom().getMultiChat();
        multiChat.makeNewGroup(groupName,owner.getId());
        getContext().getUsers().update(owner);
        return new SaveChatRoomResponse(owner.getMenu().getChatRoom());
    }

    public Response addUserToGP(String groupName,String userName) {
        User user = getContext().getUsers().getByUserName(userName);
        User owner = getCurrentUser();
        MultiChat multiChat = owner.getMenu().getChatRoom().getMultiChat();
        if (!multiChat.canAddToGroup(user,owner.getCommunication())){
            return new ExceptionResponse(new CantAddUserToGP("Can't Add this User!"));
        }
        Group group = multiChat.getGroup(groupName);
        LinkedList<User> usersInGP = new LinkedList<>();
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        for (Integer userId:group.getAllUsersId()) {
            usersInGP.add(getContext().getUsers().get(userId));
        }
        user.getMenu().getChatRoom().getMultiChat().makeNewGroup(groupName,user.getId());
        Group newGroup = user.getMenu().getChatRoom().getMultiChat().getGroup(groupName);
        for (User gUser:usersInGP) {
            if (gUser.getId()!=owner.getId()){
                newGroup.addToUsersId(gUser.getId());
                gUser.getMenu().getChatRoom().getMultiChat().getGroup(groupName).addToUsersId(user.getId());
            }
            else {
                newGroup.addToUsersId(owner.getId());
                owner.getMenu().getChatRoom().getMultiChat().getGroup(groupName).addToUsersId(user.getId());
            }
        }
        for (User userInGP : usersInGP) {
            if (userInGP.getId()!=owner.getId() && user.getId()!=userInGP.getId()){
                getContext().getUsers().update(userInGP);
                chatRooms.add(userInGP.getMenu().getChatRoom());
            }
        }
        getContext().getUsers().update(user);
        chatRooms.add(user.getMenu().getChatRoom());
        getContext().getUsers().update(owner);
        chatRooms.add(owner.getMenu().getChatRoom());
        return new SaveChatRoomResponse(chatRooms);
    }

    public Response sendMessageToOne(Integer userId, SendMessageEvent sendMessageEvent){
        User owner = getCurrentUser();
        User user = getContext().getUsers().get(userId);
        SingleChat singleChat = owner.getMenu().getChatRoom().searchChats(userId);
        Message message = getMessageController().makeMessage(sendMessageEvent);
        singleChat.sendMessage(message,user,owner);
        getContext().getUsers().update(owner);
        getContext().getUsers().update(user);
        SaveMessageResponse saveMessageResponse = new SaveMessageResponse(message);
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        chatRooms.add(user.getMenu().getChatRoom());
        chatRooms.add(owner.getMenu().getChatRoom());
        saveMessageResponse.setChatRooms(chatRooms);
        return saveMessageResponse;
    }

    @Override
    public User getCurrentUser() {
        return clientHandler.getCurrentUser();
    }

    @Override
    public LoggerController getLogger() {
        if (loggerController==null){
            loggerController = new LoggerController(clientHandler);
        }
        return loggerController;
    }
}
