package ir.sharif.math.ap99_2.tinsta_shared.user_info.model;


import ir.sharif.math.ap99_2.tinsta_shared.model.Model;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import java.util.LinkedList;


public class PhoneNumber extends Model {
    private Integer userId;
    private boolean privacy;
    private String phoneNumberText;

    public PhoneNumber(Integer userId, String phoneNumberText) {
        if (Validation.isValidPhoneNumber(phoneNumberText)) {
            this.phoneNumberText = phoneNumberText;
            this.privacy = true;
            this.userId = userId;
        }

    }

    public PhoneNumber() {
    }

    public static void setAllPhoneNumbers(LinkedList<PhoneNumber> allPhoneNumbers) {
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

    public String getPhoneNumberText() {
        return phoneNumberText;
    }


    public void setPhoneNumberText(String phoneNumberText) {
        this.phoneNumberText = phoneNumberText;
    }

    public String toString() {
        return phoneNumberText;
    }

}
