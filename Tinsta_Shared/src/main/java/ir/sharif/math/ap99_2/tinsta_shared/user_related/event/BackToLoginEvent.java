package ir.sharif.math.ap99_2.tinsta_shared.user_related.event;

import java.util.EventObject;

public class BackToLoginEvent extends EventObject {
    private boolean isConnected;

    public BackToLoginEvent(Object source) {
        super(source);
        this.setConnected(true);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
