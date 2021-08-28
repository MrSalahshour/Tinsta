package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;



public class ReportEvent extends TweetEvent {
    private Tweet tweet;

    public ReportEvent(Object source) {
        super(source);
    }

    public ReportEvent() {
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TweetEventVisitor tweetEventVisitor = (TweetEventVisitor) eventVisitor;
        return tweetEventVisitor.addReport(tweet);
    }

    public Tweet getTweet() {
        return tweet;
    }
}
