package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;


public class ReTweetEvent extends TweetEvent {
    private Tweet tweet;

    public ReTweetEvent(Object source) {
        super(source);
    }

    public ReTweetEvent() {
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TweetEventVisitor tweetEventVisitor = (TweetEventVisitor) eventVisitor;
        return tweetEventVisitor.reTweet(tweet);
    }

    public Tweet getTweet() {
        return tweet;
    }
}
