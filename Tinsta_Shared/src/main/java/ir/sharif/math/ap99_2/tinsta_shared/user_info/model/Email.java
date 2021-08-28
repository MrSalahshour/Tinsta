package ir.sharif.math.ap99_2.tinsta_shared.user_info.model;


import ir.sharif.math.ap99_2.tinsta_shared.model.Model;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

public class Email extends Model {
    private Integer userId;
    private boolean privacy;
    private String emailText;

    public Email(Integer userId, String email) {
        if (Validation.isValidEmail(email)) {
            this.emailText = email;
            this.privacy = true;
            this.userId = userId;
        }
    }

    public Email() {

    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public String getEmailText() {
        return emailText;
    }


    public void setEmailText(String emailText) {
        this.emailText = emailText;

    }

    public String toString() {
        return this.emailText;
    }


}
