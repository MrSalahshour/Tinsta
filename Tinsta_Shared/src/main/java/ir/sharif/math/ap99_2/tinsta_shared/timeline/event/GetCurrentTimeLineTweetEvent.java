package ir.sharif.math.ap99_2.tinsta_shared.timeline.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class GetCurrentTimeLineTweetEvent extends TimeLineEvent {

    public GetCurrentTimeLineTweetEvent(Object source) {
        super(source);
    }

    public GetCurrentTimeLineTweetEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        TimeLineEventVisitor timeLineEventVisitor = (TimeLineEventVisitor) eventVisitor;
        return timeLineEventVisitor.getCurrentTweet();
    }
}
