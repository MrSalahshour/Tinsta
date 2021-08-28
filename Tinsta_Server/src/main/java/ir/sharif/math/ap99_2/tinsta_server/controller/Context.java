package ir.sharif.math.ap99_2.tinsta_server.controller;


import ir.sharif.math.ap99_2.tinsta_server.controller.database.*;

public class Context {
    private final UserDB users;
    private final MessageDB messages;
    private final TweetDB tweets ;
    private final CommentDB comments;
    private final LikeDB likes;
    private final PhoneNumberDB phoneNumbers;
    private final UserNameDB userNames;
    private final EmailDB emails;

    public Context() {
        users = new UserDB();
        messages = new MessageDB();
        tweets = new TweetDB();
        comments = new CommentDB();
        likes = new LikeDB();
        phoneNumbers = new PhoneNumberDB();
        userNames = new UserNameDB();
        emails = new EmailDB();
    }

    public UserDB getUsers() {
        return users;
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

    public LikeDB getLikes() {
        return likes;
    }

    public PhoneNumberDB getPhoneNumbers() {
        return phoneNumbers;
    }

    public UserNameDB getUserNames() {
        return userNames;
    }

    public EmailDB getEmails() {
        return emails;
    }

}
