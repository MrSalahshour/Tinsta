package ir.sharif.math.ap99_2.tinsta_shared.user_info.model;


import ir.sharif.math.ap99_2.tinsta_shared.model.Model;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

public class UserName extends Model {
    private Integer userId;
    private String userNameText;

    public UserName(Integer userId, String userNameText)  {
        if (Validation.isValidUsername(userNameText)) {
            this.userNameText = userNameText;
            this.userId = userId;
        }
    }

    public UserName() {
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(String userNameText) {
        this.userNameText = userNameText;
    }


}
