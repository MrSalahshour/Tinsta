package ir.sharif.math.ap99_2.tinsta_shared.timeline.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.google.gson.annotations.Expose;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.Menu;

import java.io.Serializable;
import java.util.LinkedList;

@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Timeline.class)
public class Timeline implements Serializable {
    private LinkedList<Integer> myTweetsId;
    private Integer currentTweetId;

    public Timeline() {
        this.myTweetsId = new LinkedList<>();
        this.currentTweetId = null;
    }



    public void setMyTweetsId(LinkedList<Integer> myTweetsId) {
        this.myTweetsId = myTweetsId;
    }


    public LinkedList<Integer> getMyTweetsId() {
        return myTweetsId;
    }

    public void addToMyTweets(Integer tweetId) {
        myTweetsId.add(tweetId);
    }

    public Integer getCurrentTweetId() {
        return currentTweetId;
    }

    public void setCurrentTweetId(Integer currentTweetId) {
        this.currentTweetId = currentTweetId;
    }

    public boolean hasPreviousTweet() {
        if (myTweetsId.isEmpty())
            return false;
        return myTweetsId.indexOf(currentTweetId) != 0;
    }

    public boolean hasNextTweet() {
        if (myTweetsId.isEmpty())
            return false;
        return !(myTweetsId.indexOf(currentTweetId) == myTweetsId.size() - 1);
    }

    public void goNextTweet() {
        setCurrentTweetId(myTweetsId.get(myTweetsId.indexOf(getCurrentTweetId()) + 1));

    }

    public void goPreviousTweet() {
        setCurrentTweetId(myTweetsId.get(myTweetsId.indexOf(getCurrentTweetId()) - 1));
    }

}
