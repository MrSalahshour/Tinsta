package ir.sharif.math.ap99_2.tinsta_shared.home_page.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class GetMyInfoEvent extends HomePageEvent {
    private String nameText;
    private String lastNameText;
    private String userNameText;
    private String birthdayText;
    private String phoneNumberText;
    private String emailText;
    private String biographyText;

    public GetMyInfoEvent() {
    }

    public GetMyInfoEvent(Object source) {
        super(source);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        HomePageEventVisitor homePageEventVisitor = (HomePageEventVisitor) eventVisitor;
        return homePageEventVisitor.getInfo(this);
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getLastNameText() {
        return lastNameText;
    }

    public void setLastNameText(String lastNameText) {
        this.lastNameText = lastNameText;
    }

    public String getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(String userNameText) {
        this.userNameText = userNameText;
    }

    public String getBirthdayText() {
        return birthdayText;
    }

    public void setBirthdayText(String birthdayText) {
        this.birthdayText = birthdayText;
    }

    public String getPhoneNumberText() {
        return phoneNumberText;
    }

    public void setPhoneNumberText(String phoneNumberText) {
        this.phoneNumberText = phoneNumberText;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public String getBiographyText() {
        return biographyText;
    }

    public void setBiographyText(String biographyText) {
        this.biographyText = biographyText;
    }

}
