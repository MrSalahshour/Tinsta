package ir.sharif.math.ap99_2.tinsta_server.communication_related.controller;

import ir.sharif.math.ap99_2.tinsta_server.controller.Context;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.util.LinkedList;
import java.util.Map;

public class ChatRoomHandleController {

    public void addToSendListForAllUsers(User owner, Context context) {
        owner.getMenu().getChatRoom().getMultiChat().clearCurrentSendList();
        LinkedList<Integer> currentSendList = owner.getMenu().getChatRoom().getMultiChat().getCurrentSendListUsersId();
        for (int i = 0; i < owner.getCommunication().getFollowersId().size(); i++) {
            User follower = context.getUsers().get(owner.getCommunication().getFollowersId().get(i));
            if (follower != null)
                currentSendList.add(follower.getId());
        }
        for (int i = 0; i < owner.getCommunication().getFollowingsId().size(); i++) {
            User following = context.getUsers().get(owner.getCommunication().getFollowingsId().get(i));
            if (following != null)
                currentSendList.add(following.getId());
        }
        context.getUsers().update(owner);
    }

    public void addToSendListByUserName(String username, User owner, Context context) {
        User user = context.getUsers().getByUserName(username);
        if (user != null)
            owner.getMenu().getChatRoom().getMultiChat().getCurrentSendListUsersId().add(user.getId());
        context.getUsers().update(owner);
    }

    public void addToSendListByUserFolder(String userFolder, User owner, Context context) {
        owner.getMenu().getChatRoom().getMultiChat().clearCurrentSendList();
        if (owner.getCommunication().haveUserFolder(userFolder)) {
            for (Map.Entry<String, LinkedList<Integer>> entry : owner.getCommunication()
                    .getUserManagement().entrySet()) {
                if (entry.getKey().equals(userFolder)) {
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        User user = context.getUsers().get(entry.getValue().get(i));
                        if (user == null) {
                            continue;
                        }
                        owner.getMenu().getChatRoom().getMultiChat().getCurrentSendListUsersId().add(user.getId());
                    }
                }
            }
        }
        context.getUsers().update(owner);
    }

    public LinkedList<ChatRoom> sendMultiMessage(Message message, User owner, Context context) {

        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        for (Integer userId : owner.getMenu().getChatRoom()
                .getMultiChat().getCurrentSendListUsersId()) {
            User user = context.getUsers().get(userId);
            if (owner.getMenu().getChatRoom().searchChats(user.getId()) != null
                    && user.getMenu().getChatRoom().searchChats(owner.getId()) != null) {
                owner.getMenu().getChatRoom().searchChats(user.getId()).sendMessage(message, user, owner);
            } else {
                if (owner.getMenu().getChatRoom().canStartChat(user, owner)) {
                    owner.getMenu().getChatRoom().startChat(user, owner);
                    owner.getMenu().getChatRoom().searchChats(user.getId()).sendMessage(message, user, owner);
                }
            }
            context.getUsers().update(owner);
            context.getUsers().update(user);
            chatRooms.add(user.getMenu().getChatRoom());
        }
        chatRooms.add(owner.getMenu().getChatRoom());
        return chatRooms;
    }

}
