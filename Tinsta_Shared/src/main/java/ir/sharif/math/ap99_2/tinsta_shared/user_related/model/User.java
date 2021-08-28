package ir.sharif.math.ap99_2.tinsta_shared.user_related.model;

import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.Communication;
import ir.sharif.math.ap99_2.tinsta_shared.model.Model;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Birthday;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class User extends Model implements Serializable {

    private String createdAt;
    private Menu menu;
    private Communication communication;
    private String name;
    private String lastName;
    private String password;
    private String biography;
    private String lastSeen;
    private boolean online;
    private Birthday birthday;
    private Integer phoneNumberId;
    private Integer userNameId;
    private Integer emailId;
    private String profileImagePath;

    public User(String name, String lastName, String password) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.biography = "No Biography";
        this.createdAt = LocalDateTime.now().toString();
        this.lastSeen = createdAt;
        this.menu = new Menu(this.getId());
        this.communication = new Communication(this.getId());
        this.online = true;
        this.birthday = new Birthday("1", "1", "1");

    }

    public User() {
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public Communication getCommunication() {
        return communication;
    }

    public boolean isOnline() {
        return online;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getUserNameId() {
        return userNameId;
    }

    public String getPassword() {
        return password;
    }

    public String getBiography() {
        return biography;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public Integer getPhoneNumberId() {
        return phoneNumberId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNumberId(Integer phoneNumberId) {
        this.phoneNumberId = phoneNumberId;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setUserNameId(Integer userNameId) {
        this.userNameId = userNameId;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }


    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id==user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



