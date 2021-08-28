package ir.sharif.math.ap99_2.tinsta_server.sharing_related.controller;



import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MainPages;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.PostTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.TweetEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Like;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class TweetController extends Controller implements GetUser, GetLogger, TweetEventVisitor {

    private final ClientHandler clientHandler;
    private LoggerController loggerController;
    public TweetController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }


    public Response likeTweet(Tweet tweet) {
        if (isLiked(tweet)) {
            User owner = getCurrentUser();
            Like like = new Like(owner.getId(),tweet.getId());
            tweet.addLikes(like);
            getContext().getLikes().add(like);
            UserName userName = getContext().getUserNames().get(owner.getUserNameId());
            getLogger().log(LoggerController.infoLevel(), "TweetController", "likeTweet",
                    userName.getUserNameText(), "User Liked a Tweet");
            if (tweet instanceof Comment)
                getContext().getComments().update((Comment) tweet);
            else
                getContext().getTweets().update(tweet);
            getContext().getUsers().update(owner);
        }
        return new SaveMessageResponse(tweet);
    }

    public Response addComment(Tweet tweet, PostTweetEvent postTweetEvent) {
        String comment = postTweetEvent.getContent();
        String imagePath = postTweetEvent.getImagePath();
        if (imagePath!=null){
            imagePath = ImageLoader.copyImageToResource(imagePath);
        }
        User owner = getCurrentUser();
        Comment newComment = tweet.addComments(owner, comment,imagePath);
        getContext().getComments().add(newComment);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "TweetController", "addComment",
                userName.getUserNameText(), "User posted a comment");
        getContext().getTweets().update(tweet);
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response addToSavedMessage(Tweet tweet) {
        User owner = getCurrentUser();
        tweet.addToSavedMessages(owner);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "TweetController", "addToSavedMessage",
                userName.getUserNameText(), "User added a message to saved message");
        getContext().getTweets().update(tweet);
        getContext().getUsers().update(owner);
        return new SaveChatRoomResponse(owner.getMenu().getChatRoom());
    }

    public Response addReport(Tweet tweet){
        tweet.addReport();
        User owner = getCurrentUser();
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "TweetController", "addReport",
                userName.getUserNameText(),
                "User added a Report to a Comment");
        getContext().getTweets().update(tweet);
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response reTweet(Tweet tweet) {
        User owner = getContext().getUsers().get(tweet.getOwnerId());
        User currentUser = getCurrentUser();
        tweet.addToRetweets(currentUser,owner);
        UserName userName = getContext().getUserNames().get(currentUser.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "TweetController", "reTweet"
                , userName.getUserNameText(), "User Retweeted a Tweet");
        getContext().getTweets().update(tweet);
        getContext().getUsers().update(owner);
        getContext().getUsers().update(currentUser);
        return new VoidResponse();
    }

    public Response hasNextComment(Tweet tweet) {
        return new GetBooleanResponse(tweet.hasNextComment());
    }

    public Response hasPreviousComment(Tweet tweet) {
        return new GetBooleanResponse(tweet.hasPreviousComment());
    }

    public Response goNextComment(Tweet tweet) {
        tweet.goNextComment();
        getContext().getTweets().update(tweet);
        return new VoidResponse();
    }

    public Response goPreviousComment(Tweet tweet) {
        tweet.goPreviousComment();
        getContext().getTweets().update(tweet);
        return new VoidResponse();
    }

    public Response tweetHasNextByOrigin(String origin) {
        if (checkExplorerOrigin(origin))
            return new GetBooleanResponse(getCurrentUser().getMenu().getExplorer().hasNextTweet());
        if (checkTimelineOrigin(origin))
            return new GetBooleanResponse(getCurrentUser().getMenu().getTimeline().hasNextTweet());
        if (checkHomePageOrigin(origin))
            return new GetBooleanResponse(getCurrentUser().getMenu().getHomePage().hasNextTweet());
        return new GetBooleanResponse(false);
    }

    public Response tweetHasPreviousByOrigin(String origin) {
        if (checkExplorerOrigin(origin))
            return new GetBooleanResponse(getCurrentUser().getMenu().getExplorer().hasPreviousTweet());
        if (checkTimelineOrigin(origin))
            return new GetBooleanResponse(getCurrentUser().getMenu().getTimeline().hasPreviousTweet());
        if (checkHomePageOrigin(origin))
            return new GetBooleanResponse(getCurrentUser().getMenu().getHomePage().hasPreviousTweet());
        return new GetBooleanResponse(false);
    }

    public Response goNextTweetByOrigin(String origin) {
        if (checkExplorerOrigin(origin)) {
            User owner = getCurrentUser();
            owner.getMenu().getExplorer().goNextTweet();
            getContext().getUsers().update(owner);
        }
        if (checkTimelineOrigin(origin)) {
            User owner = getCurrentUser();
            owner.getMenu().getTimeline().goNextTweet();
            getContext().getUsers().update(owner);

        }
        if (checkHomePageOrigin(origin)) {
            User owner = getCurrentUser();
            owner.getMenu().getHomePage().goNextTweet();
            getContext().getUsers().update(owner);
        }
        return new VoidResponse();
    }

    public Response goPreviousTweetByOrigin(String origin) {
        if (checkExplorerOrigin(origin)) {
            User owner = getCurrentUser();
            owner.getMenu().getExplorer().goPreviousTweet();
            getContext().getUsers().update(owner);
        }

        if (checkTimelineOrigin(origin)) {
            User owner = getCurrentUser();
            owner.getMenu().getTimeline().goPreviousTweet();
            getContext().getUsers().update(owner);
        }

        if (checkHomePageOrigin(origin)) {
            User owner = getCurrentUser();
            owner.getMenu().getHomePage().goPreviousTweet();
            getContext().getUsers().update(owner);
        }
        return new VoidResponse();
    }

    private boolean isLiked(Tweet tweet){
        for (int i = 0; i < tweet.getLikesId().size(); i++) {
            Like like = getContext().getLikes().get(tweet.getLikesId().get(i));
            if (like.getUserId().equals(getCurrentUser().getId())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkHomePageOrigin(String origin) {
        return origin.equals(MainPages.HomePage.getName());
    }

    public boolean checkTimelineOrigin(String origin) {
        return origin.equals(MainPages.TimeLine.getName());
    }

    public boolean checkExplorerOrigin(String origin) {
        return origin.equals(MainPages.Explorer.getName());
    }

    @Override
    public LoggerController getLogger() {
        if (loggerController==null){
            loggerController = new LoggerController(clientHandler);
        }
        return loggerController;
    }

    public User getCurrentUser() {
        return clientHandler.getCurrentUser();
    }

}
