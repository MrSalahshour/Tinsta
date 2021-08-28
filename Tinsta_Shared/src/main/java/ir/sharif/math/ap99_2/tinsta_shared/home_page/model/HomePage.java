package ir.sharif.math.ap99_2.tinsta_shared.home_page.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.profile.model.Profile;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.model.Timeline;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.Menu;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.io.Serializable;


@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = HomePage.class)
public class HomePage extends Timeline implements Serializable {
    private Profile profile;

    public HomePage() {
        this.profile = new Profile();
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

}
