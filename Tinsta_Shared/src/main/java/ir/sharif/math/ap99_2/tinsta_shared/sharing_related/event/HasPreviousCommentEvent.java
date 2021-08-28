package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

public class HasPreviousCommentEvent extends TweetEvent {
    private Tweet tweet;
    public HasPreviousCommentEvent(Object source, Tweet tweet) {
        super(source);
        this.tweet = tweet;
    }

    public HasPreviousCommentEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TweetEventVisitor tweetEventVisitor = (TweetEventVisitor) eventVisitor;
        return tweetEventVisitor.hasPreviousComment(tweet);
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
