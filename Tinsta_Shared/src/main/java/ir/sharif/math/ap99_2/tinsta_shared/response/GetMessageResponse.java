package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;

public class GetMessageResponse extends Response {
    private Message message;

    public GetMessageResponse(Message message) {
        this.message = message;
    }

    public GetMessageResponse() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
