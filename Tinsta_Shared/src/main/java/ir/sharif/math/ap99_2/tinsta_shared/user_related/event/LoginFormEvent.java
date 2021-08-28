package ir.sharif.math.ap99_2.tinsta_shared.user_related.event;



import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.io.Serializable;

public class LoginFormEvent extends RegisterEvent implements Serializable {
    private String userName;
    private String password;

    public LoginFormEvent(Object source, String userName, String password) {
        super(source);
        this.userName = userName;
        this.password = password;
    }

    public LoginFormEvent() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        RegisterEventVisitor registerEventVisitor = (RegisterEventVisitor) eventVisitor;
        return registerEventVisitor.loginUser(this);
    }


}
