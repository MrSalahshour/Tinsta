package ir.sharif.math.ap99_2.tinsta_shared.user_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface RegisterEventVisitor extends EventVisitor {

    Response loginUser(LoginFormEvent loginFormEvent);

    Response registerUser(RegisterFormEvent registerFormEvent);

    Response logOut();

    Response isConnected();

    Response exit();
}
