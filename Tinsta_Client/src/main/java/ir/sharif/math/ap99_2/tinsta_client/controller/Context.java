package ir.sharif.math.ap99_2.tinsta_client.controller;


import ir.sharif.math.ap99_2.tinsta_client.controller.database.*;

public class Context {
    private final MessageDB messages;
    private final TweetDB tweets ;
    private final CommentDB comments;
    private final UserNameDB userNames;
    private final ChatRoomDB chatRooms;
    private final OfflineUserDB offlineUsers;
    private final EventDB events;

    public Context() {
        messages = new MessageDB();
        tweets = new TweetDB();
        comments = new CommentDB();
        userNames = new UserNameDB();
        chatRooms = new ChatRoomDB();
        offlineUsers = new OfflineUserDB();
        events = new EventDB();
    }

    public MessageDB getMessages() {
        return messages;
    }

    public TweetDB getTweets() {
        return tweets;
    }

    public CommentDB getComments() {
        return comments;
    }

    public UserNameDB getUserNames() {
        return userNames;
    }

    public ChatRoomDB getChatRooms() {
        return chatRooms;
    }

    public OfflineUserDB getOfflineUsers() {
        return offlineUsers;
    }

    public EventDB getEvents() {
        return events;
    }
}
