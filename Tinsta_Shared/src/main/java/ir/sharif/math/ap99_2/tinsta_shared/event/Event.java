package ir.sharif.math.ap99_2.tinsta_shared.event;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.AddToSavedMessageFromChatEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.CommunityEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SingleChatEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.ExplorerEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.HomePageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.NotificationEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.InfoPrivacyEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.SettingEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.MessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.TweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.TimeLineEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterEvent;

import java.io.Serializable;
import java.util.EventObject;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatRoomEvent.class, name = "ChatRoomEvent"),
        @JsonSubTypes.Type(value = CommunityEvent.class, name = "CommunityEvent"),
        @JsonSubTypes.Type(value = SingleChatEvent.class, name = "SingleChatEvent"),
        @JsonSubTypes.Type(value = GetClientUserEvent.class, name = "GetClientUserEvent"),
        @JsonSubTypes.Type(value = GetCommentEvent.class, name = "GetCommentEvent"),
        @JsonSubTypes.Type(value = GetMessageEvent.class, name = "GetMessageEvent"),
        @JsonSubTypes.Type(value = GetTweetEvent.class, name = "GetTweetEvent"),
        @JsonSubTypes.Type(value = GetUserEvent.class, name = "GetUserEvent"),
        @JsonSubTypes.Type(value = GetUserNameEvent.class, name = "GetUserNameEvent"),
        @JsonSubTypes.Type(value = UpdateUserEvent.class, name = "UpdateUserEvent"),
        @JsonSubTypes.Type(value = ExplorerEvent.class, name = "ExplorerEvent"),
        @JsonSubTypes.Type(value = HomePageEvent.class, name = "HomePageEvent"),
        @JsonSubTypes.Type(value = NotificationEvent.class, name = "NotificationEvent"),
        @JsonSubTypes.Type(value = InfoPrivacyEvent.class, name = "InfoPrivacyEvent"),
        @JsonSubTypes.Type(value = ProfileEvent.class, name = "ProfileEvent"),
        @JsonSubTypes.Type(value = SettingEvent.class, name = "SettingEvent"),
        @JsonSubTypes.Type(value = MessageEvent.class, name = "MessageEvent"),
        @JsonSubTypes.Type(value = TweetEvent.class, name = "TweetEvent"),
        @JsonSubTypes.Type(value = TimeLineEvent.class, name = "TimeLineEvent"),
        @JsonSubTypes.Type(value = RegisterEvent.class, name = "RegisterEvent"),
        @JsonSubTypes.Type(value = GetFileEvent.class, name = "GetFileEvent"),
        @JsonSubTypes.Type(value = UpdateCommentEvent.class, name = "UpdateCommentEvent"),
        @JsonSubTypes.Type(value = UpdateMessageEvent.class, name = "UpdateMessageEvent"),
        @JsonSubTypes.Type(value = UpdateTweetEvent.class, name = "UpdateTweetEvent"),
        @JsonSubTypes.Type(value = SetClientUserEvent.class, name = "SetClientUserEvent"),



})
public abstract class Event implements Comparable<Event> {
    @JsonIgnore
    private Object source;

    private String token;

    protected int ID;

    public Event() {
        this.ID =(int) (System.currentTimeMillis() & 0xfffffff);
    }

    public Event(Object source) {
        this.source = source;
        this.ID =(int) (System.currentTimeMillis() & 0xfffffff);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public abstract Response visit(EventVisitor eventVisitor);

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int compareTo(Event event) {
        int compareId= event.getID();
        return this.getID()-compareId;
    }
}
