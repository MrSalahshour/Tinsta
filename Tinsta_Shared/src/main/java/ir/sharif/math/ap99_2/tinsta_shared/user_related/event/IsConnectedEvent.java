package ir.sharif.math.ap99_2.tinsta_shared.user_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class IsConnectedEvent extends RegisterEvent{
    @Override
    public Response visit(EventVisitor eventVisitor) {
        RegisterEventVisitor registerEventVisitor = (RegisterEventVisitor) eventVisitor;
        return registerEventVisitor.isConnected();
    }
}
