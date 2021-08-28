package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model;


import ir.sharif.math.ap99_2.tinsta_shared.model.Model;

public class Like extends Model {
    private Integer userId;
    private Integer tweetId;

    public Like(Integer userId, Integer tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;

    }

    public Like() {
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTweetId() {
        return tweetId;
    }
}
