package ir.sharif.math.ap99_2.tinsta_client.home_page.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.BackToMenuEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton newTweetButton = new JButton("Post New Tweet");
    private final JButton showMyTweetsButton = new JButton("Show My Tweets");
    private final JButton changeProfileButton = new JButton("Change Profile");
    private final JButton communicationSectionButton = new JButton("Communication");
    private final JButton getInfoButton = new JButton("Get My info");
    private final JButton notificationButton = new JButton("Notifications");
    private final JButton backToMenuButton = new JButton("Back to Menu");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton selectProfileImage = new JButton("Select Profile Image");
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToPostNewTweetListener goToPostNewTweetListener;
    private ShowMyTweetsListener showMyTweetsListener;
    private GoToChangeProfileListener goToChangeProfileListener;
    private GoToCommunicationSecListener goToCommunicationSecListener;
    private GetMyInfoListener getMyInfoListener;
    private GoToNotificationsListener goToNotificationsListener;
    private BackToMenuListener backToMenuListener;
    private BackToLoginListener backToLoginListener;
    private SelectImageForProfListener selectImageForProfListener;



    public HomePageView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        GridLayout gridLayout = new GridLayout(3,3);
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(gridLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();

    }
    private void addElements(){
        this.add(backToMenuButton);
        this.add(selectProfileImage);
        this.add(logoutButton);
        this.add(newTweetButton);
        this.add(showMyTweetsButton);
        this.add(changeProfileButton);
        this.add(communicationSectionButton);
        this.add(getInfoButton);
        this.add(notificationButton);
    }

    private void configElements(Border border){
        newTweetButton.setFont(newTweetButton.getFont().deriveFont(15.0f));
        showMyTweetsButton.setFont(showMyTweetsButton.getFont().deriveFont(15.0f));
        changeProfileButton.setFont(changeProfileButton.getFont().deriveFont(15.0f));
        communicationSectionButton.setFont(communicationSectionButton.getFont().deriveFont(15.0f));
        getInfoButton.setFont(getInfoButton.getFont().deriveFont(15.0f));
        notificationButton.setFont(notificationButton.getFont().deriveFont(15.0f));
        backToMenuButton.setFont(backToMenuButton.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        selectProfileImage.setFont(selectProfileImage.getFont().deriveFont(15.0f));

        newTweetButton.setBackground(colorConfig.getColor3());
        showMyTweetsButton.setBackground(colorConfig.getColor3());
        changeProfileButton.setBackground(colorConfig.getColor3());
        communicationSectionButton.setBackground(colorConfig.getColor3());
        getInfoButton.setBackground(colorConfig.getColor3());
        notificationButton.setBackground(colorConfig.getColor3());
        backToMenuButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());
        selectProfileImage.setBackground(colorConfig.getColor3());

        newTweetButton.setForeground(colorConfig.getColor0());
        showMyTweetsButton.setForeground(colorConfig.getColor0());
        changeProfileButton.setForeground(colorConfig.getColor0());
        communicationSectionButton.setForeground(colorConfig.getColor0());
        getInfoButton.setForeground(colorConfig.getColor0());
        notificationButton.setForeground(colorConfig.getColor0());
        backToMenuButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());
        selectProfileImage.setForeground(colorConfig.getColor0());

        newTweetButton.setBorder(border);
        showMyTweetsButton.setBorder(border);
        changeProfileButton.setBorder(border);
        communicationSectionButton.setBorder(border);
        getInfoButton.setBorder(border);
        notificationButton.setBorder(border);
        backToMenuButton.setBorder(border);
        logoutButton.setBorder(border);
        selectProfileImage.setBorder(border);




        newTweetButton.setFocusable(false);
        showMyTweetsButton.setFocusable(false);
        changeProfileButton.setFocusable(false);
        communicationSectionButton.setFocusable(false);
        getInfoButton.setFocusable(false);
        notificationButton.setFocusable(false);
        backToMenuButton.setFocusable(false);
        logoutButton.setFocusable(false);
        selectProfileImage.setFocusable(false);

        newTweetButton.addActionListener(this);
        showMyTweetsButton.addActionListener(this);
        changeProfileButton.addActionListener(this);
        communicationSectionButton.addActionListener(this);
        getInfoButton.addActionListener(this);
        notificationButton.addActionListener(this);
        backToMenuButton.addActionListener(this);
        selectProfileImage.addActionListener(this);
        logoutButton.addActionListener(this);

    }

    public JPanel getSource() {
        return source;
    }

    public void setGoToPostNewTweetListener(GoToPostNewTweetListener goToPostNewTweetListener) {
        this.goToPostNewTweetListener = goToPostNewTweetListener;
    }

    public void setShowMyTweetsListener(ShowMyTweetsListener showMyTweetsListener) {
        this.showMyTweetsListener = showMyTweetsListener;
    }

    public void setGoToChangeProfileListener(GoToChangeProfileListener goToChangeProfileListener) {
        this.goToChangeProfileListener = goToChangeProfileListener;
    }

    public void setGoToCommunicationSecListener(GoToCommunicationSecListener goToCommunicationSecListener) {
        this.goToCommunicationSecListener = goToCommunicationSecListener;
    }

    public void setGetMyInfoListener(GetMyInfoListener getMyInfoListener) {
        this.getMyInfoListener = getMyInfoListener;
    }

    public void setGoToNotificationsListener(GoToNotificationsListener goToNotificationsListener) {
        this.goToNotificationsListener = goToNotificationsListener;
    }

    public void setBackToMenuListener(BackToMenuListener backToMenuListener) {
        this.backToMenuListener = backToMenuListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setSelectImageForProfListener(SelectImageForProfListener selectImageForProfListener) {
        this.selectImageForProfListener = selectImageForProfListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToMenuButton == e.getSource()){
            BackToMenuEvent backToMenuEvent = new BackToMenuEvent(source);
            backToMenuListener.eventOccurred(backToMenuEvent);
        }
        if (newTweetButton == e.getSource()){
            GoToPostNewTweetEvent goToPostNewTweetEvent = new GoToPostNewTweetEvent(this);
            goToPostNewTweetListener.eventOccurred(goToPostNewTweetEvent);

        }
        if (showMyTweetsButton == e.getSource()){
            ShowMyTweetsEvent showMyTweetsEvent = new ShowMyTweetsEvent(this);
            showMyTweetsListener.eventOccurred(showMyTweetsEvent);

        }
        if (changeProfileButton == e.getSource()){
            GoToChangeProfileEvent goToChangeProfileEvent = new GoToChangeProfileEvent(source);
            goToChangeProfileListener.eventOccurred(goToChangeProfileEvent);

        }
        if (communicationSectionButton == e.getSource()){
            GoToCommunicationSecEvent goToCommunicationSecEvent = new GoToCommunicationSecEvent(source);
            goToCommunicationSecListener.eventOccurred(goToCommunicationSecEvent);

        }
        if (getInfoButton == e.getSource()){
            GetMyInfoEvent getMyInfoEvent = new GetMyInfoEvent(source);
            getMyInfoListener.eventOccurred(getMyInfoEvent);

        }
        if (notificationButton == e.getSource()){
            GoToNotificationsEvent goToNotificationsEvent = new GoToNotificationsEvent(source);
            goToNotificationsListener.eventOccurred(goToNotificationsEvent);

        }
        if (selectProfileImage == e.getSource()){
            SelectImageForProfEvent selectImageForProfEvent = new SelectImageForProfEvent(this);
            selectImageForProfListener.eventOccurred(selectImageForProfEvent);
        }

    }

    @Override
    public String toString() {
        return "HomePageView";
    }
}
