package ir.sharif.math.ap99_2.tinsta_shared.event;

import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class GetCommentEvent extends Event {
    private int id;


    public GetCommentEvent() {
    }
    public GetCommentEvent(Object source, int id) {
        super(source);
        this.id = id;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        MainEventVisitor mainEventVisitor = (MainEventVisitor) eventVisitor;
        return mainEventVisitor.getComment(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
