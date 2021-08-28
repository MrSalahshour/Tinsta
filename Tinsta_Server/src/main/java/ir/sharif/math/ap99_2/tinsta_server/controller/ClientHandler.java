package ir.sharif.math.ap99_2.tinsta_server.controller;


import ir.sharif.math.ap99_2.tinsta_server.communication_related.controller.ChatRoomController;
import ir.sharif.math.ap99_2.tinsta_server.communication_related.controller.CommunityController;
import ir.sharif.math.ap99_2.tinsta_server.communication_related.controller.SingleChatController;
import ir.sharif.math.ap99_2.tinsta_server.explorer.controller.ExplorerController;
import ir.sharif.math.ap99_2.tinsta_server.home_page.controller.HomePageController;
import ir.sharif.math.ap99_2.tinsta_server.notification.controller.NotificationController;
import ir.sharif.math.ap99_2.tinsta_server.profile.controller.InfoPrivacyController;
import ir.sharif.math.ap99_2.tinsta_server.profile.controller.ProfileController;
import ir.sharif.math.ap99_2.tinsta_server.setting.controller.SettingController;
import ir.sharif.math.ap99_2.tinsta_server.sharing_related.controller.MessageController;
import ir.sharif.math.ap99_2.tinsta_server.sharing_related.controller.TweetController;
import ir.sharif.math.ap99_2.tinsta_server.timeline.controller.TimeLineController;
import ir.sharif.math.ap99_2.tinsta_server.transmitters.ResponseSender;
import ir.sharif.math.ap99_2.tinsta_server.user_related.controller.RegisterController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
import ir.sharif.math.ap99_2.tinsta_shared.event.MainEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.ExplorerEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.ExplorerEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.HomePageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.HomePageEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.NotificationEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.NotificationEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.InfoPrivacyEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.InfoPrivacyEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ProfileEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.SettingEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.SettingEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.MessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.MessageEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.TweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.TweetEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.TimeLineEvent;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.TimeLineEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class ClientHandler extends Thread {
    private final ResponseSender responseSender;
    private volatile boolean running;
    private User currentUser;
    private final MainController mainEventVisitor;
    private final ChatRoomController chatRoomEventVisitor;
    private final SingleChatController singleChatEventVisitor;
    private final CommunityController communityEventVisitor;
    private final ExplorerController explorerEventVisitor;
    private final HomePageController homePageEventVisitor;
    private final NotificationController notificationEventVisitor;
    private final InfoPrivacyController infoPrivacyEventVisitor;
    private final ProfileController profileEventVisitor;
    private final SettingController settingEventVisitor;
    private final MessageController messageEventVisitor;
    private final TweetController tweetEventVisitor;
    private final TimeLineController timeLineEventVisitor;
    private final RegisterController registerEventVisitor;

    public ClientHandler(ResponseSender responseSender) {
        this.responseSender = responseSender;
        this.running = true;
        this.mainEventVisitor = new MainController(this);
        mainEventVisitor.getContext().getUsers().setClientHandler(this);
        this.chatRoomEventVisitor = new ChatRoomController(this);
        chatRoomEventVisitor.getContext().getUsers().setClientHandler(this);
        this.singleChatEventVisitor = new SingleChatController(this);
        singleChatEventVisitor.getContext().getUsers().setClientHandler(this);
        this.communityEventVisitor = new CommunityController(this);
        communityEventVisitor.getContext().getUsers().setClientHandler(this);
        this.explorerEventVisitor = new ExplorerController(this);
        explorerEventVisitor.getContext().getUsers().setClientHandler(this);
        this.homePageEventVisitor = new HomePageController(this);
        homePageEventVisitor.getContext().getUsers().setClientHandler(this);
        this.notificationEventVisitor = new NotificationController(this);
        notificationEventVisitor.getContext().getUsers().setClientHandler(this);
        this.infoPrivacyEventVisitor = new InfoPrivacyController(this);
        infoPrivacyEventVisitor.getContext().getUsers().setClientHandler(this);
        this.profileEventVisitor = new ProfileController(this);
        profileEventVisitor.getContext().getUsers().setClientHandler(this);
        this.settingEventVisitor = new SettingController(this);
        settingEventVisitor.getContext().getUsers().setClientHandler(this);
        this.messageEventVisitor = new MessageController(new ChatRoomController(this));
        messageEventVisitor.getContext().getUsers().setClientHandler(this);
        this.tweetEventVisitor =  new TweetController(this);
        tweetEventVisitor.getContext().getUsers().setClientHandler(this);
        this.timeLineEventVisitor = new TimeLineController(this);
        timeLineEventVisitor.getContext().getUsers().setClientHandler(this);
        this.registerEventVisitor = new RegisterController(this);
        registerEventVisitor.getContext().getUsers().setClientHandler(this);
    }

    public void run() {
        while (running) {
            Event event = responseSender.getEvent();
            Response response;
            if (event instanceof ChatRoomEvent){
                response = event.visit(chatRoomEventVisitor);
            }
            else if (event instanceof SingleChatEvent){
                response = event.visit(singleChatEventVisitor);
            }
            else if (event instanceof CommunityEvent){
                response = event.visit(communityEventVisitor);
            }
            else if (event instanceof ExplorerEvent){
                response = event.visit(explorerEventVisitor);
            }
            else if (event instanceof HomePageEvent){
                response = event.visit(homePageEventVisitor);
            }
            else if (event instanceof NotificationEvent){
                response = event.visit(notificationEventVisitor);
            }
            else if (event instanceof InfoPrivacyEvent){
                response = event.visit(infoPrivacyEventVisitor);
            }
            else if (event instanceof ProfileEvent){
                response = event.visit(profileEventVisitor);
            }
            else if (event instanceof SettingEvent){
                response = event.visit(settingEventVisitor);
            }
            else if (event instanceof MessageEvent){
                response = event.visit(messageEventVisitor);
            }
            else if (event instanceof TweetEvent){
                response = event.visit(tweetEventVisitor);
            }
            else if (event instanceof TimeLineEvent){
                response = event.visit(timeLineEventVisitor);
            }
            else if (event instanceof RegisterEvent){
                response = event.visit(registerEventVisitor);
            }
            else {
                response = event.visit(mainEventVisitor);
            }
            responseSender.sendResponse(response);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public synchronized User getCurrentUser() {
        if (currentUser==null)
            return null;
        return messageEventVisitor.getContext().getUsers().get(currentUser.getId());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ResponseSender getResponseSender() {
        return responseSender;
    }
}
