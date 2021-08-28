package ir.sharif.math.ap99_2.tinsta_server.controller;

import ir.sharif.math.ap99_2.tinsta_server.transmitters.SocketResponseSender;
import ir.sharif.math.ap99_2.tinsta_shared.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.io.File;

public class MainController extends Controller implements MainEventVisitor {
    private final ClientHandler clientHandler;

    public MainController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Response getUser(GetUserEvent getUserEvent) {
        User user;
        if (getUserEvent.getId() == null){
            user = getContext().getUsers().getByUserName(getUserEvent.getUsername());
        }
        else {
            user = getContext().getUsers().get(getUserEvent.getId());
        }
        return new GetUserResponse(user);
    }

    @Override
    public Response getMessage(int id) {
        Message message = getContext().getMessages().get(id);
        return new GetMessageResponse(message);
    }

    @Override
    public Response getTweet(int id) {
        Tweet tweet = getContext().getTweets().get(id);
        return new GetTweetResponse(tweet);
    }

    @Override
    public Response getComment(int id) {
        Comment comment = getContext().getComments().get(id);
        return new GetCommentResponse(comment);
    }

    @Override
    public Response getClientUser() {
        User user = getContext().getUsers().get(clientHandler.getCurrentUser().getId());
        return new GetUserResponse(user);
    }

    @Override
    public Response getUserName(int id) {
        UserName userName = getContext().getUserNames().get(id);
        return new GetUserNameResponse(userName);
    }

    @Override
    public Response updateUser(UpdateUserEvent updateUserEvent) {
        getContext().getUsers().update(updateUserEvent.getUser());
        return new VoidResponse();
    }

    @Override
    public Response updateMessage(UpdateMessageEvent updateMessageEvent) {
        getContext().getMessages().update(updateMessageEvent.getMessage());
        return new VoidResponse();
    }

    @Override
    public Response updateTweet(UpdateTweetEvent updateTweetEvent) {
        getContext().getTweets().update(updateTweetEvent.getTweet());
        return new VoidResponse();
    }

    @Override
    public Response updateComment(UpdateCommentEvent updateCommentEvent) {
        getContext().getComments().update(updateCommentEvent.getComment());
        return new VoidResponse();
    }

    @Override
    public Response getFile(String path) {
        File file = new File(path);
        return ImageLoader.base64Converter(file);
    }

    @Override
    public Response setClientUser(Integer userId) {
        User user = getContext().getUsers().get(userId);
        clientHandler.setCurrentUser(user);
        SocketResponseSender socketResponseSender = (SocketResponseSender) clientHandler.getResponseSender();
        String token = socketResponseSender.generateNewToken();
        socketResponseSender.setToken(token);
        return new GetStringResponse(token);
    }

}
