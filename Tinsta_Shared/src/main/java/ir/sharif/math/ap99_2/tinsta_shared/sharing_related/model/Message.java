package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model;



import ir.sharif.math.ap99_2.tinsta_shared.enums.MessageState;
import ir.sharif.math.ap99_2.tinsta_shared.model.Model;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.time.LocalDateTime;


public class Message extends Model {
    private String imagePath;
    private Integer ownerId;
    private String content;
    private String publishedTime;
    private MessageState state;

    public Message(Integer ownerId, String content, String imagePath) {
        this.ownerId = ownerId;
        this.content = content;
        this.publishedTime = LocalDateTime.now().toString();
        this.imagePath = imagePath;
        this.state = MessageState.SEND;

    }

    public Message() {
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public String getContent() {
        return content;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MessageState getState() {
        return state;
    }

    public void setState(MessageState state) {
        this.state = state;
    }

    public void addToSavedMessages(User user) {
        user.getMenu().getChatRoom().getSavedMessagesId().add(this.getId());
        user.getMenu().getChatRoom().setCurrentSavedMessageId(this.getId());
    }



}
