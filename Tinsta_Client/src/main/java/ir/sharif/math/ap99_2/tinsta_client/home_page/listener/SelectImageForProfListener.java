package ir.sharif.math.ap99_2.tinsta_client.home_page.listener;



import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.home_page.view.HomePageView;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.SelectImageForProfEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveOfflineUserResponse;

import javax.swing.*;
import java.io.File;

public class SelectImageForProfListener {

    public void eventOccurred(SelectImageForProfEvent selectImageForProfEvent){
        HomePageView homePageView = (HomePageView) selectImageForProfEvent.getSource();
        String path;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(ImageLoader.getDefaultImageDirectoryPath()));
        int response = fileChooser.showOpenDialog(homePageView);
        if (response == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
            selectImageForProfEvent.setImagePath(path);
            SaveOfflineUserResponse saveOfflineUserResponse =
                    (SaveOfflineUserResponse) MainController.sendEvents(selectImageForProfEvent);
            Context context = new Context();
            OfflineUser offlineUser = context.getOfflineUsers().get(MainController.getUserId());
            offlineUser.setProfileImagePath(saveOfflineUserResponse.getProfileImagePath());
            context.getOfflineUsers().update(offlineUser);
            String message = "Done!";
            JOptionPane.showMessageDialog(homePageView, message,"Changed Your Profile Image!"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
