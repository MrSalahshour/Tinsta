package ir.sharif.math.ap99_2.tinsta_shared.event;

import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface MainEventVisitor extends EventVisitor {

    Response getUser(GetUserEvent getUserEvent);

    Response getMessage(int id);

    Response getTweet(int id);

    Response getComment(int id);

    Response getClientUser();

    Response getUserName(int id);

    Response updateUser(UpdateUserEvent updateUserEvent);

    Response updateMessage(UpdateMessageEvent updateMessageEvent);

    Response updateTweet(UpdateTweetEvent updateTweetEvent);

    Response updateComment(UpdateCommentEvent updateCommentEvent);

    Response getFile(String path);

    Response setClientUser(Integer userId);
}
