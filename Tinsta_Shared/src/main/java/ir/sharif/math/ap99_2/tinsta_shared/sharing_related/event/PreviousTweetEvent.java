package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;



public class PreviousTweetEvent extends TweetEvent {
    private String origin;

    public PreviousTweetEvent(Object source) {
        super(source);
    }

    public PreviousTweetEvent() {
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TweetEventVisitor tweetEventVisitor = (TweetEventVisitor) eventVisitor;
        return tweetEventVisitor.goPreviousTweetByOrigin(origin);
    }

    public String getOrigin() {
        return origin;
    }
}
