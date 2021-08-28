package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;

public class GetUserNameResponse extends Response {
    private UserName userName;

    public GetUserNameResponse(UserName userName) {
        this.userName = userName;
    }

    public GetUserNameResponse() {
    }

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }
}
