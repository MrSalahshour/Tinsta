package ir.sharif.math.ap99_2.tinsta_shared.home_page.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.PostTweetEvent;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GetCurrentHomePageTweetEvent.class, name = "GetCurrentHomePageTweetEvent"),
        @JsonSubTypes.Type(value = GetMyInfoEvent.class, name = "GetMyInfoEvent"),
        @JsonSubTypes.Type(value = SelectImageForProfEvent.class, name = "SelectImageForProfEvent"),
        @JsonSubTypes.Type(value = PostTweetEvent.class, name = "PostTweetEvent"),

})
public abstract class HomePageEvent extends Event {

    public HomePageEvent() {
    }

    public HomePageEvent(Object source) {
        super(source);
    }
}
