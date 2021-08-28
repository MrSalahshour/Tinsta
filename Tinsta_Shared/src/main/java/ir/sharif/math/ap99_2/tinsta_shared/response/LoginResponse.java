package ir.sharif.math.ap99_2.tinsta_shared.response;


import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class LoginResponse extends Response {
    private boolean success;
    private String token;
    private Integer userId;
    private User user;
    private UserName userName;

    public LoginResponse(boolean success) {
        this.success = success;
    }

    public LoginResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }
}
