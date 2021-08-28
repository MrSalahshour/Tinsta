package ir.sharif.math.ap99_2.tinsta_shared.setting.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.Menu;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.io.Serializable;


@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Setting.class)
public class Setting implements Serializable {

    private boolean privateAccount;
    private boolean active;
    private boolean showLastSeenToAll;
    private boolean showLastSeenToNoOne;
    private boolean showLastSeenToFollowers;

    public Setting() {
        this.privateAccount = false;
        this.active = true;
        this.showLastSeenToAll = false;
        this.showLastSeenToNoOne = false;
        this.showLastSeenToFollowers = true;
    }




    public boolean isShowLastSeenToNoOne() {
        return showLastSeenToNoOne;
    }

    public void setShowLastSeenToNoOne(boolean showLastSeenToNoOne) {
        this.showLastSeenToNoOne = showLastSeenToNoOne;
        this.showLastSeenToFollowers = !showLastSeenToNoOne;
        this.showLastSeenToAll = !showLastSeenToNoOne;
    }

    public boolean isShowLastSeenToFollowers() {
        return showLastSeenToFollowers;
    }

    public void setShowLastSeenToFollowers(boolean showLastSeenToFollowers) {
        this.showLastSeenToFollowers = showLastSeenToFollowers;
        this.showLastSeenToNoOne = !showLastSeenToFollowers;
        this.showLastSeenToAll = !showLastSeenToFollowers;
    }

    public boolean isPrivateAccount() {
        return privateAccount;
    }

    public void setPrivateAccount(boolean privateAccount) {
        this.privateAccount = privateAccount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;

    }

    public boolean isShowLastSeenToAll() {
        return showLastSeenToAll;
    }

    public void setShowLastSeenToAll(boolean showLastSeenToAll) {
        this.showLastSeenToAll = showLastSeenToAll;
        this.showLastSeenToNoOne = !showLastSeenToAll;
        this.showLastSeenToFollowers = !showLastSeenToAll;
    }

    public void changePassword(String password, User owner) {
        owner.setPassword(password);
    }




}
