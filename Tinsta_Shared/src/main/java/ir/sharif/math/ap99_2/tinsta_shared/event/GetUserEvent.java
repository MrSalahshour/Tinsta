package ir.sharif.math.ap99_2.tinsta_shared.event;

import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class GetUserEvent extends Event {
    private Integer id;
    private String username;

    public GetUserEvent() {
    }

    public GetUserEvent(Object source, Integer id) {
        super(source);
        this.id = id;

    }

    public GetUserEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        MainEventVisitor mainEventVisitor = (MainEventVisitor) eventVisitor;
        return mainEventVisitor.getUser(this);
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
