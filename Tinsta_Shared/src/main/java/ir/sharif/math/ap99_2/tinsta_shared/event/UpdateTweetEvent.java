package ir.sharif.math.ap99_2.tinsta_shared.event;

import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

public class UpdateTweetEvent extends Event{
    private Tweet tweet;

    public UpdateTweetEvent(Tweet tweet) {
        this.tweet = tweet;
    }

    public UpdateTweetEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        MainEventVisitor mainEventVisitor = (MainEventVisitor) eventVisitor;
        return mainEventVisitor.updateTweet(this);
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
