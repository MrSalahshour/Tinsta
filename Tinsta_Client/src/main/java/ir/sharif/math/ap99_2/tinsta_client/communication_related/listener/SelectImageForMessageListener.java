package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SelectImageForMessageEvent;

import javax.swing.*;
import java.io.File;

public class SelectImageForMessageListener {

    public void eventOccurred(SelectImageForMessageEvent selectImageForMessageEvent){
        ChatView chatView = (ChatView) selectImageForMessageEvent.getSource();
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(ImageLoader.getDefaultImageDirectoryPath()));
        int response = fileChooser.showOpenDialog(chatView);
        if (response == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        chatView.setImagePath(path);
    }
}
