package ir.sharif.math.ap99_2.tinsta_shared.profile.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.model.HomePage;
import ir.sharif.math.ap99_2.tinsta_shared.notification.model.Notification;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Birthday;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.io.Serializable;


@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Profile.class)
public class Profile implements Serializable {
    private Notification notification;

    public Profile( ) {
        this.notification = new Notification();
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Notification getNotification() {
        return notification;
    }

    public void changeName(String name, User owner){
            owner.setName(name);
    }

    public void changeLastName(String lastName,User owner) {
        owner.setLastName(lastName);

    }

    public void changeBiography(String biography,User owner) {
        owner.setBiography(biography);
    }

    public void changeBirthday(String year, String month, String day,User owner) {
        owner.setBirthday(new Birthday(year, month, day));
    }

}
