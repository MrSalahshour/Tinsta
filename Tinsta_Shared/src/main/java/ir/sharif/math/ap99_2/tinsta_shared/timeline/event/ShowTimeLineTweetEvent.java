package ir.sharif.math.ap99_2.tinsta_shared.timeline.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;



public class ShowTimeLineTweetEvent extends TimeLineEvent {

    public ShowTimeLineTweetEvent(Object source) {
        super(source);
    }

    public ShowTimeLineTweetEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TimeLineEventVisitor timeLineEventVisitor = (TimeLineEventVisitor) eventVisitor;
        return timeLineEventVisitor.refreshTimeLine();
    }
}
