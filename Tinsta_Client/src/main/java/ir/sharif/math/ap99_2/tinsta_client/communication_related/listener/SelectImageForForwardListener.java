package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.WriteMessageView;
import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SelectImageForForwardEvent;

import javax.swing.*;
import java.io.File;

public class SelectImageForForwardListener {

    public void eventOccurred(SelectImageForForwardEvent selectImageForForwardEvent){
        WriteMessageView writeMessageView = (WriteMessageView) selectImageForForwardEvent.getSource();
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(ImageLoader.getDefaultImageDirectoryPath()));
        int response = fileChooser.showOpenDialog(writeMessageView);
        if (response == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        writeMessageView.setImagePath(path);

    }
}
