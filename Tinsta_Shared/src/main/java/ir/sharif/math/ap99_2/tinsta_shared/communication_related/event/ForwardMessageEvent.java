package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.MessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.MessageEventVisitor;


public class ForwardMessageEvent extends MessageEvent {
    private String messageContent;
    private String imagePath;

    public ForwardMessageEvent() {

    }

    public ForwardMessageEvent(Object source, String messageContent, String imagePath) {
        super(source);
        this.messageContent = messageContent;
        this.imagePath = imagePath;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        MessageEventVisitor messageEventVisitor = (MessageEventVisitor) eventVisitor;
        return messageEventVisitor.makeMessage(this);
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
