package ir.sharif.math.ap99_2.tinsta_server.notification.controller;


import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.NotificationEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.ShowSystemMessagesEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.model.Notification;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.util.LinkedList;


public class NotificationController extends Controller implements GetUser, GetLogger, NotificationEventVisitor {

    private final ClientHandler clientHandler;
    private LoggerController loggerController;

    public NotificationController(ClientHandler clientHandler) {

        this.clientHandler = clientHandler;
    }

    public Response rejectRequestWithoutNotify() {
        User owner = getCurrentUser();
        User request = getContext().getUsers().get(owner.getMenu().getHomePage().getProfile()
                .getNotification().getCurrentFollowRequestId());
        request.getMenu().getHomePage().getProfile().getNotification().setShowRejectRequest(false);
        UserName ownerUserName = getContext().getUserNames().get(owner.getUserNameId());
        rejectFollowRequest(owner,request,ownerUserName);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "NotificationController",
                "rejectRequestWithoutNotify", userName.getUserNameText()
                , "User Rejected a Follow Request");
        getContext().getUsers().update(owner);
        getContext().getUsers().update(request);
        return new VoidResponse();
    }

    public Response rejectRequestWithNotify(){
        User owner = getCurrentUser();
        User request = getContext().getUsers().get(owner.getMenu().getHomePage().getProfile()
                .getNotification().getCurrentFollowRequestId());
        request.getMenu().getHomePage().getProfile().getNotification().setShowRejectRequest(true);
        UserName ownerUserName = getContext().getUserNames().get(owner.getUserNameId());
        rejectFollowRequest(owner,request,ownerUserName);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "NotificationController",
                "rejectRequestWithNotify", userName.getUserNameText(), "User Rejected a Follow Request");
        getContext().getUsers().update(owner);
        getContext().getUsers().update(request);
        return new VoidResponse();
    }

    public Response acceptRequest(){
        User owner = getCurrentUser();
        User request = getContext().getUsers().get(owner.getMenu().getHomePage().getProfile()
                .getNotification().getCurrentFollowRequestId());
        UserName ownerUserName = getContext().getUserNames().get(owner.getUserNameId());
        acceptFollowRequest(request,owner,ownerUserName);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(), "NotificationController",
                "acceptRequest", userName.getUserNameText(), "User Accepted a Follow Request");
        getContext().getUsers().update(owner);
        getContext().getUsers().update(request);
        return new VoidResponse();
    }

    public Response hasNextFollowRequest() {
        return new HasNextFollowReqResponse(getCurrentUser().getMenu().getHomePage()
                .getProfile().getNotification().hasNextFollowRequest());
    }

    public Response goNextFollowRequest() {
        User owner = getCurrentUser();
        owner.getMenu().getHomePage().getProfile().getNotification().goNextFollowRequest();
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response goPreviousFollowRequest() {
        User owner = getCurrentUser();
        owner.getMenu().getHomePage().getProfile().getNotification().goPreviousFollowRequest();
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Response hasPreviousFollowRequest() {
        return new HasPreviousFollowReqResponse(getCurrentUser().getMenu().getHomePage()
                .getProfile().getNotification().hasPreviousFollowRequest());
    }

    public Response getCurrentFollowReqId(){
        return new GetIntegerResponse(getCurrentUser().getMenu().getHomePage()
                .getProfile().getNotification().getCurrentFollowRequestId());
    }

    public Response dontHaveFollowRequest(){
        if (getCurrentUser().getMenu().getHomePage()
                .getProfile().getNotification().getCurrentFollowRequestId() == null)
            return new DontHaveFollowReqResponse(true);
        else
            return new DontHaveFollowReqResponse(false);
    }

    public void acceptFollowRequest(User user, User owner, UserName ownerUserName) {
        Integer userId = user.getId();
        owner.getCommunication().getFollowersId().add(userId);
        Notification notification = owner.getMenu().getHomePage().getProfile().getNotification();
        notification.getFollowRequestsUserId().remove(userId);
        if(notification.getFollowRequestsUserId().isEmpty())
            notification.setCurrentFollowRequestId(null);
        else
            notification.setCurrentFollowRequestId(notification.getFollowRequestsUserId().getLast());
        user.getCommunication().getFollowingsId().add(owner.getId());
        String message = ownerUserName.getUserNameText() + " Accepted Your Follow Request";
        user.getMenu().getHomePage().getProfile().getNotification().addMessages(message);

    }

    private void rejectFollowRequest(User owner,User user,UserName ownerUserName) {
        Integer userId =user.getId();
        Notification notification = owner.getMenu().getHomePage().getProfile().getNotification();
        notification.getFollowRequestsUserId().remove(userId);
        if(notification.getFollowRequestsUserId().isEmpty())
            notification.setCurrentFollowRequestId(null);
        else
            notification.setCurrentFollowRequestId(notification.getFollowRequestsUserId().getLast());
        if (notification.isShowRejectRequest()) {
            String message = ownerUserName.getUserNameText() + " Rejected Your Follow Request";
            user.getMenu().getHomePage().getProfile().getNotification().addMessages(message);
        }
    }

    public Response showMySystemMessages(ShowSystemMessagesEvent showSystemMessagesEvent) {
        LinkedList<String> systemMessages = getCurrentUser().getMenu().getHomePage()
                .getProfile().getNotification().getSystemMessages();
        if (systemMessages.isEmpty()){
            return new GetStringResponse(null);
        }
        StringBuilder systemMessage = new StringBuilder();
        for (String message : systemMessages) {
            systemMessage.append("* ").append(message).append(" *").append("\n");
        }
        return new GetStringResponse(systemMessage.toString());
    }

    @Override
    public User getCurrentUser() {
        return clientHandler.getCurrentUser();
    }

    @Override
    public LoggerController getLogger() {
        if (loggerController==null){
            loggerController = new LoggerController(clientHandler);
        }
        return loggerController;
    }
}
