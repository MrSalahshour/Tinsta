package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;



public class AddCommentEvent extends TweetEvent {
    private Tweet parent;
    private PostTweetEvent postTweetEvent;

    public AddCommentEvent(Object source) {
        super(source);
    }

    public AddCommentEvent() {
    }

    public void setParent(Tweet parent) {
        this.parent = parent;
    }

    public void setPostTweetEvent(PostTweetEvent postTweetEvent) {
        this.postTweetEvent = postTweetEvent;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TweetEventVisitor tweetEventVisitor = (TweetEventVisitor) eventVisitor;
        return tweetEventVisitor.addComment(parent,postTweetEvent);
    }

    public Tweet getParent() {
        return parent;
    }

    public PostTweetEvent getPostTweetEvent() {
        return postTweetEvent;
    }
}
