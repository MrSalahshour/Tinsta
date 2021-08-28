package ir.sharif.math.ap99_2.tinsta_shared.explorer.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ShowExplorerTweetEvent extends ExplorerEvent {


    public ShowExplorerTweetEvent() {
    }

    public ShowExplorerTweetEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ExplorerEventVisitor explorerEventVisitor = (ExplorerEventVisitor) eventVisitor;
        return explorerEventVisitor.refreshExplorer();
    }
}
