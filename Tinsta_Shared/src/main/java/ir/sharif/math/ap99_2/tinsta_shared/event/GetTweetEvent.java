package ir.sharif.math.ap99_2.tinsta_shared.event;

import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class GetTweetEvent extends Event{
    private int id;

    public GetTweetEvent() {
    }

    public GetTweetEvent(Object source, int id) {
        super(source);
        this.id = id;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        MainEventVisitor mainEventVisitor = (MainEventVisitor) eventVisitor;
        return mainEventVisitor.getTweet(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
