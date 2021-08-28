package ir.sharif.math.ap99_2.tinsta_server.timeline.controller;



import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetTweetResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.response.VoidResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Like;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.TimeLineEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class TimeLineController extends Controller implements GetUser, TimeLineEventVisitor {

    private final ClientHandler clientHandler;

    public TimeLineController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public Response getCurrentTweet() {
        if (getCurrentUser().getMenu().getTimeline().getCurrentTweetId()==null)
            return new GetTweetResponse(null);
        return new GetTweetResponse(getContext().getTweets().get(getCurrentUser()
                .getMenu().getTimeline().getCurrentTweetId()));
    }

    public Response refreshTimeLine() {
        User owner = getCurrentUser();
        owner.getMenu().getTimeline().getMyTweetsId().clear();
        for (int i = 0; i < owner.getCommunication().getFollowingsId().size(); i++) {
            User following =getContext().getUsers().get(owner.getCommunication().getFollowingsId().get(i));
            if (following==null)
                continue;
            if (following.getMenu().getSetting().isActive()){
                for (int j = 0; j < following.getMenu().getHomePage().getMyTweetsId().size(); j++) {
                    Tweet followingTweet = getContext().getTweets().get(following.getMenu()
                            .getHomePage().getMyTweetsId().get(j));
                    if (followingTweet.getReports()>=3 || owner.getCommunication()
                            .getMutedUsersId().contains(following.getId()) )
                        continue;
                    owner.getMenu().getTimeline().addToMyTweets(followingTweet.getId());
                }
                for (int j = 0; j < getContext().getUsers().all().size(); j++) {
                    User user = getContext().getUsers().all().get(j);
                    for (int k = 0; k < user.getMenu().getHomePage().getMyTweetsId().size(); k++) {
                        Tweet userTweet = getContext().getTweets().get(user.getMenu()
                                .getHomePage().getMyTweetsId().get(k));
                        for (int l = 0; l < userTweet.getLikesId().size(); l++) {
                            Like like = getContext().getLikes().get(userTweet.getLikesId().get(l));
                            if (like.getUserId().equals(following.getId())) {
                                if (userTweet.getReports()>=3)
                                    continue;
                                owner.getMenu().getTimeline().addToMyTweets(userTweet.getId());
                            }
                        }
                    }
                }
            }
        }

        if (owner.getMenu().getTimeline().getMyTweetsId().isEmpty()){
            owner.getMenu().getTimeline().setCurrentTweetId(null);
            return new VoidResponse();
        }
        owner.getMenu().getTimeline().setCurrentTweetId(owner.getMenu().getTimeline().getMyTweetsId().getLast());
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    @Override
    public User getCurrentUser() {
        return clientHandler.getCurrentUser();
    }
}
