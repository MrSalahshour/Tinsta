package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;



import java.util.EventObject;


public class GetUserIdEvent extends EventObject {
    private final String username;

    public GetUserIdEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }



}
