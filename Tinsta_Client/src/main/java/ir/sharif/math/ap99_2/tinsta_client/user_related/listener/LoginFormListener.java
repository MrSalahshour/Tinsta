package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.user_related.view.MenuView;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.LoginFormEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;

import javax.swing.*;

public class LoginFormListener {

    public void eventOccurred(LoginFormEvent loginFormEvent) throws UserNotExist {
        Response response = MainController.sendEvents(loginFormEvent);
        if (response instanceof ExceptionResponse){
            throw new UserNotExist(((ExceptionResponse) response).getException().getMessage());
        }
        JPanel panel = (JPanel) loginFormEvent.getSource();
        panel.removeAll();
        MenuView menuView = new MenuView(panel);
        menuView.setBackToLoginListener(new BackToLoginListener());
        menuView.setGoToChatRoomListener(new GoToChatRoomListener());
        menuView.setGoToExplorerListener(new GoToExplorerListener());
        menuView.setGoToHomePageListener(new GoToHomePageListener());
        menuView.setGoToSettingListener(new GoToSettingListener());
        menuView.setGoToTimeLineListener(new GoToTimeLineListener());
        panel.add(menuView);
        panel.revalidate();
        panel.repaint();

    }


}
