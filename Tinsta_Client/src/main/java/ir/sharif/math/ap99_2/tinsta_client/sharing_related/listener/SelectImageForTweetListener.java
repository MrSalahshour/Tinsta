package ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.PostTweetView;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.SelectImageForTweetEvent;

import javax.swing.*;
import java.io.File;

public class SelectImageForTweetListener {

    public void eventOccurred(SelectImageForTweetEvent selectImageForTweetEvent){
        PostTweetView postTweetView = (PostTweetView) selectImageForTweetEvent.getSource();
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(ImageLoader.getDefaultImageDirectoryPath()));
        int response = fileChooser.showOpenDialog(postTweetView);
        if (response == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        postTweetView.setImagePath(path);

    }
}
