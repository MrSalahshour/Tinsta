package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.user_related.view.MenuView;
import ir.sharif.math.ap99_2.tinsta_shared.response.ExceptionResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterFormEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.EmailAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNameAlreadyExist;

import javax.swing.*;

public class RegisterFormListener {

    public void eventOccurred(RegisterFormEvent registerFormEvent) throws EmailAlreadyExist, UserNameAlreadyExist {
        Response response = MainController.sendEvents(registerFormEvent);
        if (response instanceof ExceptionResponse){
            if (((ExceptionResponse) response).getException() instanceof EmailAlreadyExist){
                throw new EmailAlreadyExist(((ExceptionResponse) response).getException().getMessage());
            }
            else if (((ExceptionResponse) response).getException() instanceof UserNameAlreadyExist){
                throw new UserNameAlreadyExist(((ExceptionResponse) response).getException().getMessage());
            }
        }
        JPanel panel = (JPanel) registerFormEvent.getSource();
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
