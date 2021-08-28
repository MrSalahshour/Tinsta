package ir.sharif.math.ap99_2.tinsta_shared.timeline.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface TimeLineEventVisitor extends EventVisitor {

    Response getCurrentTweet();

    Response refreshTimeLine();
}
