package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.util.EventObject;


public class SendMessageEvent extends ChatRoomEvent {
    private String imagePath;
    private String content;
    private long milliDelay;
    private Message message;

    public SendMessageEvent(Object source, String imagePath, String content) {
        super(source);
        this.imagePath = imagePath;
        this.content = content;
    }
    public SendMessageEvent(Object source, String imagePath, String content , long milliDelay ) {
        super(source);
        this.imagePath = imagePath;
        this.content = content;
        this.milliDelay = milliDelay;
    }

    public SendMessageEvent() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getContent() {
        return content;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        return null;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMilliDelay() {
        return milliDelay;
    }

    public void setMilliDelay(long milliDelay) {
        this.milliDelay = milliDelay;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
