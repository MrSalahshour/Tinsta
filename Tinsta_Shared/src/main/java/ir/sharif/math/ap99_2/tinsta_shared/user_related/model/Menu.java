package ir.sharif.math.ap99_2.tinsta_shared.user_related.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.model.Explorer;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.model.HomePage;
import ir.sharif.math.ap99_2.tinsta_shared.setting.model.Setting;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.model.Timeline;

import java.io.Serializable;


@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Menu.class)
public class Menu implements Serializable {

    private Integer userId;
    private ChatRoom chatRoom;
    private Explorer explorer;
    private HomePage homePage;
    private Setting setting;
    private Timeline timeline;

    public Menu(Integer userId) {
        this.userId = userId;
        this.chatRoom = new ChatRoom();
        this.explorer = new Explorer();
        this.setting = new Setting();
        this.timeline = new Timeline();
        this.homePage = new HomePage();
    }

    public Menu() {
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void setExplorer(Explorer explorer) {
        this.explorer = explorer;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Integer getUserId() {
        return userId;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public Explorer getExplorer() {
        return explorer;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public Setting getSetting() {
        return setting;
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
