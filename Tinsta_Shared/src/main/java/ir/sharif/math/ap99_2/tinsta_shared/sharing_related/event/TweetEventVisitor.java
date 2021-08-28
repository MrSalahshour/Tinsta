package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;


public interface TweetEventVisitor extends EventVisitor {

    Response addToSavedMessage(Tweet tweet);

    Response likeTweet(Tweet tweet);

    Response tweetHasNextByOrigin(String origin);

    Response goNextTweetByOrigin(String origin);

    Response hasNextComment(Tweet tweet);

    Response goNextComment(Tweet tweet);

    Response addComment(Tweet parent,PostTweetEvent postTweetEvent);

    Response tweetHasPreviousByOrigin(String origin);

    Response goPreviousTweetByOrigin(String origin);

    Response hasPreviousComment(Tweet tweet);

    Response reTweet(Tweet tweet);

    Response addReport(Tweet tweet);

    Response goPreviousComment(Tweet tweet);





}
