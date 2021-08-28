package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model;






public class Comment extends Tweet {

    private Integer tweetId;

    public Comment(Integer tweetId, Integer user, String text, String imagePath) {
        super(user, text,imagePath);
        this.tweetId = tweetId;

    }

    public Comment() {
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public Integer getTweetId() {
        return tweetId;
    }


}
