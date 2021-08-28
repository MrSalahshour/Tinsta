package ir.sharif.math.ap99_2.tinsta_shared.home_page.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.PostTweetEvent;

public interface HomePageEventVisitor extends EventVisitor {

    Response getCurrentTweet();

    Response getInfo(GetMyInfoEvent getMyInfoEvent);

    Response selectImageProfile(SelectImageForProfEvent selectImageForProfEvent);

    Response postTweet(PostTweetEvent postTweetEvent);



}
