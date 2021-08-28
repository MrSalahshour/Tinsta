package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class GetUserResponse extends Response {
    private User user;

    public GetUserResponse(User user) {
        this.user = user;
    }

    public GetUserResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
