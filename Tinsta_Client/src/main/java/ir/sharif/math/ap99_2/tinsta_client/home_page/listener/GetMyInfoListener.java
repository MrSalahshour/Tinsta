package ir.sharif.math.ap99_2.tinsta_client.home_page.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.GetMyInfoView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetClientUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GetMyInfoEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetMyInfoResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class GetMyInfoListener {

    public void eventOccurred(GetMyInfoEvent getMyInfoEvent){
        GetUserResponse getUserResponse = (GetUserResponse) MainController
                .sendEvents(new GetClientUserEvent(this));
        User user = getUserResponse.getUser();
        JPanel panel = (JPanel) getMyInfoEvent.getSource();
        panel.removeAll();
        GetMyInfoView getMyInfoView = new GetMyInfoView(panel);
        GetMyInfoResponse getMyInfoResponse = (GetMyInfoResponse) MainController.sendEvents(getMyInfoEvent);
        GetMyInfoEvent getMyInfoEventFinal = getMyInfoResponse.getGetMyInfoEvent();
        getMyInfoView.setImageProfilePath(user.getProfileImagePath());
        getMyInfoView.setBackToLoginListener(new BackToLoginListener());
        getMyInfoView.setGoToHomePageListener(new GoToHomePageListener());
        getMyInfoView.setBiographyTextAreaText(getMyInfoEventFinal.getBiographyText());
        getMyInfoView.setBirthdayLabelText(getMyInfoEventFinal.getBirthdayText());
        getMyInfoView.setEmailLabelText(getMyInfoEventFinal.getEmailText());
        getMyInfoView.setLastNameLabelText(getMyInfoEventFinal.getLastNameText());
        getMyInfoView.setNameLabelText(getMyInfoEventFinal.getNameText());
        getMyInfoView.setPhoneNumberLabelText(getMyInfoEventFinal.getPhoneNumberText());
        getMyInfoView.setUserNameLabelText(getMyInfoEventFinal.getUserNameText());
        getMyInfoView.configElements();
        panel.add(getMyInfoView);
        panel.revalidate();
        panel.repaint();

    }
}
