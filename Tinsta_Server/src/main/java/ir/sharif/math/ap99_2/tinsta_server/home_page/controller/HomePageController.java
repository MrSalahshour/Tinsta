package ir.sharif.math.ap99_2.tinsta_server.home_page.controller;


import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_server.controller.LoggerController;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetLogger;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GetMyInfoEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.HomePageEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.SelectImageForProfEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.model.HomePage;
import ir.sharif.math.ap99_2.tinsta_shared.response.*;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.PostTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.model.Timeline;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Email;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.PhoneNumber;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class HomePageController extends Controller implements GetUser, GetLogger, HomePageEventVisitor {
    private final ClientHandler clientHandler;
    private LoggerController loggerController;


    public HomePageController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public User getCurrentUser() {
        return clientHandler.getCurrentUser();
    }

    public Response getCurrentTweet() {
        if (getCurrentUser().getMenu().getHomePage().getCurrentTweetId()==null)
            return new GetTweetResponse(null);
        return new GetTweetResponse(getContext().getTweets().get(getCurrentUser()
                .getMenu().getHomePage().getCurrentTweetId()));
    }

    public Response getInfo(GetMyInfoEvent getMyInfoEvent)  {
        User owner =getCurrentUser();
        getMyInfoEvent.setNameText("Name : " + owner.getName());
        getMyInfoEvent.setLastNameText("LastName : " + owner.getLastName());
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getMyInfoEvent.setUserNameText("UserName : " + userName.getUserNameText());

        if (owner.getBirthday().toString().equals("1-1-1"))
            getMyInfoEvent.setBirthdayText("Birthday: You haven't set your birthday");
        else
            getMyInfoEvent.setBirthdayText("Birthday: " + owner.getBirthday().toString());
        Email email = getContext().getEmails().get(owner.getEmailId());
        getMyInfoEvent.setEmailText("Email: " + email.toString());
        PhoneNumber phoneNumber = getContext().getPhoneNumbers().get(owner.getPhoneNumberId());
        getMyInfoEvent.setPhoneNumberText("Phone Number: " + phoneNumber.getPhoneNumberText());
        getMyInfoEvent.setBiographyText(owner.getBiography());
        return new GetMyInfoResponse(getMyInfoEvent);
    }


    public Response postTweet(PostTweetEvent postTweetEvent)  {
        String content = postTweetEvent.getContent();
        String imagePath = postTweetEvent.getImagePath();
        if (imagePath!=null){
            imagePath = ImageLoader.copyImageToResource(imagePath);
        }
        User owner = getCurrentUser();
        Tweet newTweet = newTweet(owner,content,imagePath);
        getContext().getTweets().add(newTweet);
        UserName userName = getContext().getUserNames().get(owner.getUserNameId());
        getLogger().log(LoggerController.infoLevel(),
                "HomePageController", "postTweet", userName.getUserNameText(), "Tweeted a Post");
        getContext().getUsers().update(owner);
        return new VoidResponse();
    }

    public Tweet newTweet(User owner ,String content, String imagePath) {
        Tweet newTweet = new Tweet(owner.getId(), content, imagePath);
        HomePage homePage = owner.getMenu().getHomePage();
        Timeline timeline = owner.getMenu().getTimeline();
        homePage.addToMyTweets(newTweet.getId());
        timeline.addToMyTweets(newTweet.getId());
        timeline.setCurrentTweetId(newTweet.getId());
        homePage.setCurrentTweetId(newTweet.getId());
        getContext().getUsers().update(owner);
        return newTweet;
    }


    public Response selectImageProfile(SelectImageForProfEvent selectImageForProfEvent){
        User owner = getCurrentUser();
        String imagePath = selectImageForProfEvent.getImagePath();
        if (imagePath!=null){
            imagePath = ImageLoader.copyImageToResource(imagePath);
            owner.setProfileImagePath(imagePath);
        }
        getContext().getUsers().update(owner);
        return new SaveOfflineUserResponse(imagePath);
    }

    @Override
    public LoggerController getLogger() {
        if (loggerController==null){
            loggerController = new LoggerController(clientHandler);
        }
        return loggerController;
    }
}
