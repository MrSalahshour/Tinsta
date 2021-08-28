package ir.sharif.math.ap99_2.tinsta_client.home_page.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetFileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetFileResponse;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToHomePageEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GetMyInfoView extends JPanel implements ActionListener {
    private final JPanel source;
    private String imageProfilePath;
    private final JLabel profileImageLabel= new JLabel();
    private final JLabel nameLabel = new JLabel();
    private final JLabel lastNameLabel = new JLabel();
    private final JLabel userNameLabel = new JLabel();
    private final JLabel birthdayLabel = new JLabel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel phoneNumberLabel = new JLabel();
    private final JLabel biographyLabel = new JLabel("Bio: ");
    private final JTextArea biographyTextArea = new JTextArea();
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToHomePageButton =new JButton("Back To HomePage");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToHomePageListener goToHomePageListener;


    public GetMyInfoView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());



    }
    private void addElements(){
        this.add(nameLabel);
        this.add(lastNameLabel);
        this.add(userNameLabel);
        this.add(emailLabel);
        this.add(phoneNumberLabel);
        this.add(biographyLabel);
        this.add(backToHomePageButton);
        this.add(logoutButton);
        this.add(birthdayLabel);
        this.add(profileImageLabel);
    }

    public void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToHomePageButton.setFont(backToHomePageButton.getFont().deriveFont(15.0f));
        nameLabel.setFont(nameLabel.getFont().deriveFont(15.0f));
        lastNameLabel.setFont(lastNameLabel.getFont().deriveFont(15.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(15.0f));
        emailLabel.setFont(emailLabel.getFont().deriveFont(15.0f));
        birthdayLabel.setFont(birthdayLabel.getFont().deriveFont(15.0f));
        biographyLabel.setFont(biographyLabel.getFont().deriveFont(15.0f));
        phoneNumberLabel.setFont(phoneNumberLabel.getFont().deriveFont(15.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToHomePageButton.setBackground(colorConfig.getColor3());
        nameLabel.setBackground(colorConfig.getColor3());
        lastNameLabel.setBackground(colorConfig.getColor3());
        userNameLabel.setBackground(colorConfig.getColor3());
        emailLabel.setBackground(colorConfig.getColor3());
        birthdayLabel.setBackground(colorConfig.getColor3());
        biographyLabel.setBackground(colorConfig.getColor3());
        phoneNumberLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToHomePageButton.setForeground(colorConfig.getColor0());
        nameLabel.setForeground(colorConfig.getColor0());
        lastNameLabel.setForeground(colorConfig.getColor0());
        userNameLabel.setForeground(colorConfig.getColor0());
        emailLabel.setForeground(colorConfig.getColor0());
        birthdayLabel.setForeground(colorConfig.getColor0());
        biographyLabel.setForeground(colorConfig.getColor0());
        phoneNumberLabel.setForeground(colorConfig.getColor0());

        logoutButton.setFocusable(false);
        backToHomePageButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();



        Image profileImage = getImage(getImageFile(getImageProfilePath()));
        profileImage = profileImage.getScaledInstance(labelConfig.getL200x200()
                .getWidth(),labelConfig.getL200x200().getHeight(),Image.SCALE_DEFAULT);
        ImageIcon profileImageIcon = new ImageIcon(profileImage);
        profileImageLabel.setIcon(profileImageIcon);


        logoutButton.setBounds(550,10,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        backToHomePageButton.setBounds(300,10,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        profileImageLabel.setBounds(50,10,labelConfig.getL200x200()
                .getWidth(),labelConfig.getL200x200().getHeight());
        nameLabel.setBounds(250,100,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        lastNameLabel.setBounds(250,150,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        userNameLabel.setBounds(250,200,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        emailLabel.setBounds(250,250,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        birthdayLabel.setBounds(250,300,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        phoneNumberLabel.setBounds(250,340,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        biographyLabel.setBounds(260,365,labelConfig.getL70x50()
                .getWidth(),labelConfig.getL70x50().getHeight());

        nameLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        lastNameLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        userNameLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        emailLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        birthdayLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        phoneNumberLabel.setHorizontalAlignment(SwingUtilities.CENTER);


        biographyTextArea.setLineWrap(true);
        biographyTextArea.setWrapStyleWord(true);
        biographyTextArea.setEditable(false);
        biographyTextArea.setOpaque(false);

        ScrollPaneConfig scrollPaneConfig = new ScrollPaneConfig();


        JScrollPane scrollPane = new JScrollPane(biographyTextArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(310,380,scrollPaneConfig.getsP170x100().getWidth()
                ,scrollPaneConfig.getsP170x100().getHeight());
        this.add(scrollPane);
        logoutButton.addActionListener(this);
        backToHomePageButton.addActionListener(this);
        addElements();
    }

    public void setNameLabelText(String text){
        nameLabel.setText(text);
    }

    public void setLastNameLabelText(String text){
        lastNameLabel.setText(text);
    }

    public void setUserNameLabelText(String text){
        userNameLabel.setText(text);
    }

    public void setBirthdayLabelText(String text){
        birthdayLabel.setText(text);
    }

    public void setPhoneNumberLabelText(String text){
        phoneNumberLabel.setText(text);
    }

    public void setEmailLabelText(String text){
        emailLabel.setText(text);
    }

    public void setBiographyTextAreaText(String text){
        biographyTextArea.setText(text);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToHomePageListener(GoToHomePageListener goToHomePageListener) {
        this.goToHomePageListener = goToHomePageListener;
    }

    public String getImageProfilePath() {
        return imageProfilePath;
    }

    public void setImageProfilePath(String imageProfilePath) {
        this.imageProfilePath = imageProfilePath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToHomePageButton == e.getSource()){
            GoToHomePageEvent goToHomePageEvent = new GoToHomePageEvent(source);
            goToHomePageListener.eventOccurred(goToHomePageEvent);

        }
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
}
