package ir.sharif.math.ap99_2.tinsta_shared.home_page.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class GetCurrentHomePageTweetEvent extends HomePageEvent {

    public GetCurrentHomePageTweetEvent() {
    }

    public GetCurrentHomePageTweetEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        HomePageEventVisitor homePageEventVisitor = (HomePageEventVisitor) eventVisitor;
        return homePageEventVisitor.getCurrentTweet();
    }
}
