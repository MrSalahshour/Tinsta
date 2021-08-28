package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;



public class NextTweetEvent extends TweetEvent {
    private String origin;

    public NextTweetEvent(Object source) {
        super(source);
    }

    public NextTweetEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TweetEventVisitor tweetEventVisitor = (TweetEventVisitor) eventVisitor;
        return tweetEventVisitor.goNextTweetByOrigin(origin);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
