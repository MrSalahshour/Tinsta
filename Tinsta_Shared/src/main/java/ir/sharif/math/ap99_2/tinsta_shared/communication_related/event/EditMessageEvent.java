package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;


public class EditMessageEvent extends ChatRoomEvent {
    private Message message;
    private String result;

    public EditMessageEvent() {
    }

    public EditMessageEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ChatRoomEventVisitor chatRoomEventVisitor = (ChatRoomEventVisitor) eventVisitor;
        return chatRoomEventVisitor.editMessage(message,result);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Message getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}
