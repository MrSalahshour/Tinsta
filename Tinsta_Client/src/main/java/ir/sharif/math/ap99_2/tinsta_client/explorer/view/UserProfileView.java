package ir.sharif.math.ap99_2.tinsta_client.explorer.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetFileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetFileResponse;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UserProfileView extends JPanel {
    private String imageProfilePath;
    private final JLabel profileImageLabel= new JLabel();
    private final JTextArea profileDetailsTextArea = new JTextArea();
    private final ColorConfig colorConfig = new ColorConfig();
    private final int userId;


    public UserProfileView(int userId) {
        this.userId = userId;
        PanelConfig panelConfig = new PanelConfig();
        this.setLayout(null);
        this.setBounds(panelConfig.getUserProfilePanel().getX(), panelConfig.getUserProfilePanel().getY()
                , panelConfig.getUserProfilePanel().getWidth(), panelConfig.getUserProfilePanel().getHeight());
        this.setBackground(colorConfig.getColor5());

    }
    private void addElements(JScrollPane scrollPane){
        this.add(scrollPane);
        this.add(profileImageLabel);
    }

    public void configElements(){

        profileDetailsTextArea.setFont(profileDetailsTextArea.getFont().deriveFont(15.0f));
        profileDetailsTextArea.setBackground(colorConfig.getColor3());
        profileDetailsTextArea.setEditable(false);
        profileDetailsTextArea.setWrapStyleWord(true);
        profileDetailsTextArea.setOpaque(false);

        Image profileImage = getImage(getImageFile(getImageProfilePath()));
        profileImage = profileImage.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon profileImageIcon = new ImageIcon(profileImage);
        profileImageLabel.setIcon(profileImageIcon);

        JScrollPane scrollPane = new JScrollPane(profileDetailsTextArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        profileImageLabel.setBounds(0,0,200,200);
        scrollPane.setBounds(200,0,200,200);
        addElements(scrollPane);


    }

    public String getImageProfilePath() {
        return imageProfilePath;
    }

    public void setImageProfilePath(String imageProfilePath) {
        this.imageProfilePath = imageProfilePath;
    }

    public void setProfileDetailsTextArea(String profileDetailsTextArea) {
        this.profileDetailsTextArea.setText(profileDetailsTextArea);
    }

    public int getUserId() {
        return userId;
    }

    public Image getImage(File file){
        if (file==null)
            return null;
        else {
            return ImageLoader.getImageByPath(file);
        }
    }
    public File getImageFile(String path){
        GetFileResponse getFileResponse = (GetFileResponse)
                MainController.sendEvents(new GetFileEvent(path));
       return ImageLoader.contentToFile(getFileResponse);
    }

    public JTextArea getProfileDetailsTextArea() {
        return profileDetailsTextArea;
    }
}
