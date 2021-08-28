package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;

public class GetTweetResponse extends Response {
    private Tweet tweet;

    public GetTweetResponse(Tweet tweet) {
        this.tweet = tweet;
    }

    public GetTweetResponse() {
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
