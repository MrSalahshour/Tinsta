package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ForwardMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface MessageEventVisitor extends EventVisitor {

    Response makeMessage(ForwardMessageEvent forwardMessageEvent);
}
