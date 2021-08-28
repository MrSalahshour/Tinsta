package ir.sharif.math.ap99_2.tinsta_client.explorer.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.StartChatListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatRoomView;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.CommunicationMenuView;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.controller.enums.MainViews;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ShowUserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MainPages;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GetUserProfileTextEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.SearchUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotInYourLists;

import javax.swing.*;

public class SearchUserListener {

    public void eventOccurred(SearchUserEvent searchUserEvent) throws UserNotExist, UserNotInYourLists {

        if (searchUserEvent.getSource().toString().equals(MainViews.CommunicationMenuView.getName())){
            searchUserEvent.setOrigin(MainPages.Community.getName());
            Response response = MainController.sendEvents(searchUserEvent);
            User user = null;
            if (response instanceof ExceptionResponse){
                if (((ExceptionResponse) response).getException() instanceof UserNotExist){
                    throw new UserNotExist(((ExceptionResponse) response).getException().getMessage());
                }
                if (((ExceptionResponse) response).getException() instanceof UserNotInYourLists){
                    throw new UserNotInYourLists(((ExceptionResponse) response).getException().getMessage());
                }
            }
            else {
                GetUserResponse getUserResponse = (GetUserResponse) response;
                user = getUserResponse.getUser();
            }
            CommunicationMenuView communicationMenuView = (CommunicationMenuView) searchUserEvent.getSource();
            JPanel panel = communicationMenuView.getSource();
            panel.removeAll();
            ShowUserProfileView showUserProfileView = new ShowUserProfileView(communicationMenuView);
            assert user != null;
            showUserProfileView.setUserId(user.getId());
            UserProfileView userProfileView = new UserProfileView(showUserProfileView.getUserId());
            GetStringResponse getStringResponse = (GetStringResponse) MainController
                    .sendEvents(new GetUserProfileTextEvent(this,user));
            userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
            userProfileView.setImageProfilePath(user.getProfileImagePath());
            userProfileView.configElements();
            showUserProfileView.setUserProfileView(userProfileView);
            showUserProfileView.setUserId(user.getId());
            showUserProfileView.setBackToListener(new BackToListener());
            showUserProfileView.setBlockOrUnBlockListener(new BlockOrUnBlockListener());
            showUserProfileView.setMuteOrUnMuteListener(new MuteOrUnMuteListener());
            showUserProfileView.setStartChatListener(new StartChatListener());
            showUserProfileView.setFollowOrUnfollowListener(new FollowOrUnfollowListener());
            showUserProfileView.setBackToLoginListener(new BackToLoginListener());
            showUserProfileView.configElements();
            panel.add(showUserProfileView);
            panel.revalidate();
            panel.repaint();
        }
        else {

            searchUserEvent.setOrigin(MainPages.Explorer.getName());
            Response response = MainController.sendEvents(searchUserEvent);
            User user;
            if (response instanceof ExceptionResponse){
                throw new UserNotExist(((ExceptionResponse) response).getException().getMessage());
            }
            else {
                GetUserResponse getUserResponse = (GetUserResponse) response;
                user = getUserResponse.getUser();
            }
            GetStringResponse getStringResponse = (GetStringResponse) MainController
                    .sendEvents(new GetUserProfileTextEvent(this,user));
            JPanel panel;
            JPanel source;
            if (searchUserEvent.getSource() instanceof ChatRoomView){
                ChatRoomView chatRoomView = (ChatRoomView) searchUserEvent.getSource();
                source = chatRoomView;
                panel = chatRoomView.getSource();
            }
            else {
                ExplorerView explorerView = (ExplorerView) searchUserEvent.getSource();
                source = explorerView;
                panel = explorerView.getSource();
            }
            panel.removeAll();
            ShowUserProfileView showUserProfileView = new ShowUserProfileView(source);
            UserProfileView userProfileView = new UserProfileView(showUserProfileView.getUserId());
            userProfileView.setProfileDetailsTextArea(getStringResponse.getContent());
            userProfileView.setImageProfilePath(user.getProfileImagePath());
            userProfileView.configElements();
            showUserProfileView.setUserProfileView(userProfileView);
            showUserProfileView.setUserId(user.getId());
            showUserProfileView.setBackToListener(new BackToListener());
            showUserProfileView.setBlockOrUnBlockListener(new BlockOrUnBlockListener());
            showUserProfileView.setMuteOrUnMuteListener(new MuteOrUnMuteListener());
            showUserProfileView.setStartChatListener(new StartChatListener());
            showUserProfileView.setFollowOrUnfollowListener(new FollowOrUnfollowListener());
            showUserProfileView.setBackToLoginListener(new BackToLoginListener());
            showUserProfileView.configElements();
            panel.add(showUserProfileView);
            panel.revalidate();
            panel.repaint();

        }
    }

}
