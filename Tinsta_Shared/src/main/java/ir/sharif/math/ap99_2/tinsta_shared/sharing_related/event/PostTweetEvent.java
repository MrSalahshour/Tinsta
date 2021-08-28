package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.HomePageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.HomePageEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class PostTweetEvent extends HomePageEvent {
    private String imagePath;
    private String content;


    public PostTweetEvent(Object source, String imagePath, String content) {
        super(source);
        this.imagePath = imagePath;
        this.content = content;
    }

    public PostTweetEvent() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getContent() {
        return content;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        HomePageEventVisitor homePageEventVisitor = (HomePageEventVisitor) eventVisitor;
        return homePageEventVisitor.postTweet(this);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
