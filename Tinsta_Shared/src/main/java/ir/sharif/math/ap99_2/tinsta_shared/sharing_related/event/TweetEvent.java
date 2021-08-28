package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddCommentEvent.class, name = "AddCommentEvent"),
        @JsonSubTypes.Type(value = AddToSavedMessageEvent.class, name = "AddToSavedMessageEvent"),
        @JsonSubTypes.Type(value = HasNextCommentEvent.class, name = "HasNextCommentEvent"),
        @JsonSubTypes.Type(value = HasNextTweetEvent.class, name = "HasNextTweetEvent"),
        @JsonSubTypes.Type(value = HasPreviousCommentEvent.class, name = "HasPreviousCommentEvent"),
        @JsonSubTypes.Type(value = HasPreviousTweetEvent.class, name = "HasPreviousTweetEvent"),
        @JsonSubTypes.Type(value = LikeTweetEvent.class, name = "LikeTweetEvent"),
        @JsonSubTypes.Type(value = NextCommentEvent.class, name = "NextCommentEvent"),
        @JsonSubTypes.Type(value = NextTweetEvent.class, name = "NextTweetEvent"),
        @JsonSubTypes.Type(value = PreviousCommentEvent.class, name = "PreviousCommentEvent"),
        @JsonSubTypes.Type(value = PreviousTweetEvent.class, name = "PreviousTweetEvent"),
        @JsonSubTypes.Type(value = ReTweetEvent.class, name = "ReTweetEvent"),
        @JsonSubTypes.Type(value = ReportEvent.class, name = "ReportEvent"),



})
public abstract class TweetEvent extends Event {
    public TweetEvent(Object source) {
        super(source);
    }

    public TweetEvent() {
    }
}
