package ir.sharif.math.ap99_2.tinsta_shared.event;

import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;

public class UpdateMessageEvent extends Event{
    private Message message;

    public UpdateMessageEvent(Message message) {
        this.message = message;
    }

    public UpdateMessageEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        MainEventVisitor mainEventVisitor = (MainEventVisitor) eventVisitor;
        return mainEventVisitor.updateMessage(this);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
