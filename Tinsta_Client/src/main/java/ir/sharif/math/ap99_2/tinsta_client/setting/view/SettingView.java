package ir.sharif.math.ap99_2.tinsta_client.setting.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton changePrivacyButton = new JButton("Change Privacy Setting");
    private final JButton changeLastSeenButton = new JButton("Change LastSeen Setting");
    private final JButton changePasswordButton = new JButton("Change Password");
    private final JButton deActiveButton = new JButton("DeActive the Account");
    private final JButton deleteAccountButton = new JButton("Delete Account");
    private final JButton settingStatusButton = new JButton("Show Setting Status");
    private final JButton backToMenuButton = new JButton("Back To Menu");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton refreshServerButton = new JButton("Refresh Server");
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToChangePrivacyListener goToChangePrivacyListener;
    private GoToChangeLastSeenListener goToChangeLastSeenListener;
    private GoToChangePasswordListener goToChangePasswordListener;
    private GoToDeActiveAccountListener goToDeActiveAccountListener;
    private GoToDeleteAccountListener goToDeleteAccountListener;
    private ShowSettingStatusListener showSettingStatusListener;
    private BackToLoginListener backToLoginListener;
    private BackToMenuListener backToMenuListener;


    public SettingView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();

        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();

    }

    public void setGoToChangePrivacyListener(GoToChangePrivacyListener goToChangePrivacyListener) {
        this.goToChangePrivacyListener = goToChangePrivacyListener;
    }

    public void setGoToChangeLastSeenListener(GoToChangeLastSeenListener goToChangeLastSeenListener) {
        this.goToChangeLastSeenListener = goToChangeLastSeenListener;
    }

    public void setGoToChangePasswordListener(GoToChangePasswordListener goToChangePasswordListener) {
        this.goToChangePasswordListener = goToChangePasswordListener;
    }

    public void setGoToDeActiveAccountListener(GoToDeActiveAccountListener goToDeActiveAccountListener) {
        this.goToDeActiveAccountListener = goToDeActiveAccountListener;
    }

    public void setGoToDeleteAccountListener(GoToDeleteAccountListener goToDeleteAccountListener) {
        this.goToDeleteAccountListener = goToDeleteAccountListener;
    }

    public void setShowSettingStatusListener(ShowSettingStatusListener showSettingStatusListener) {
        this.showSettingStatusListener = showSettingStatusListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToMenuListener(BackToMenuListener backToMenuListener) {
        this.backToMenuListener = backToMenuListener;
    }

    private void addElements(){
        this.add(backToMenuButton);
        this.add(changePrivacyButton);
        this.add(changeLastSeenButton);
        this.add(logoutButton);
        this.add(changePasswordButton);
        this.add(deleteAccountButton);
        this.add(deActiveButton);
        this.add(settingStatusButton);
        this.add(refreshServerButton);
    }

    private void configElements(Border border){
        backToMenuButton.setFont(backToMenuButton.getFont().deriveFont(12.0f));
        refreshServerButton.setFont(refreshServerButton.getFont().deriveFont(12.0f));
        changePrivacyButton.setFont(changePrivacyButton.getFont().deriveFont(15.0f));
        changeLastSeenButton.setFont(changeLastSeenButton.getFont().deriveFont(15.0f));
        changePasswordButton.setFont(changePasswordButton.getFont().deriveFont(15.0f));
        deleteAccountButton.setFont(deleteAccountButton.getFont().deriveFont(15.0f));
        deActiveButton.setFont(deActiveButton.getFont().deriveFont(15.0f));
        settingStatusButton.setFont(settingStatusButton.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(12.0f));

        changePrivacyButton.setBackground(colorConfig.getColor3());
        refreshServerButton.setBackground(colorConfig.getColor3());
        changeLastSeenButton.setBackground(colorConfig.getColor3());
        changePasswordButton.setBackground(colorConfig.getColor3());
        deleteAccountButton.setBackground(colorConfig.getColor3());
        deActiveButton.setBackground(colorConfig.getColor3());
        settingStatusButton.setBackground(colorConfig.getColor3());
        backToMenuButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());

        changePrivacyButton.setForeground(colorConfig.getColor0());
        changeLastSeenButton.setForeground(colorConfig.getColor0());
        changePasswordButton.setForeground(colorConfig.getColor0());
        deleteAccountButton.setForeground(colorConfig.getColor0());
        deActiveButton.setForeground(colorConfig.getColor0());
        settingStatusButton.setForeground(colorConfig.getColor0());
        backToMenuButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());
        refreshServerButton.setForeground(colorConfig.getColor0());


        changePrivacyButton.setBorder(border);
        changeLastSeenButton.setBorder(border);
        changePasswordButton.setBorder(border);
        deleteAccountButton.setBorder(border);
        deActiveButton.setBorder(border);
        settingStatusButton.setBorder(border);
        backToMenuButton.setBorder(border);
        logoutButton.setBorder(border);


        ButtonConfig buttonConfig = new ButtonConfig();

        backToMenuButton.setBounds(200,0,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        refreshServerButton.setBounds(340,62,buttonConfig.getB120x25()
                .getWidth(),buttonConfig.getB120x25().getHeight());
        logoutButton.setBounds(200,510,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        changePrivacyButton.setBounds(75,100,buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight());
        changeLastSeenButton.setBounds(415,100,buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight());
        changePasswordButton.setBounds(75,225,buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight());
        deActiveButton.setBounds(415,225,buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight());
        settingStatusButton.setBounds(75,350,buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight());
        deleteAccountButton.setBounds(415,350,buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight());

        backToMenuButton.setFocusable(false);
        changePrivacyButton.setFocusable(false);
        logoutButton.setFocusable(false);
        changeLastSeenButton.setFocusable(false);
        changePasswordButton.setFocusable(false);
        deleteAccountButton.setFocusable(false);
        deActiveButton.setFocusable(false);
        settingStatusButton.setFocusable(false);
        refreshServerButton.setFocusable(false);


        backToMenuButton.addActionListener(this);
        changePrivacyButton.addActionListener(this);
        logoutButton.addActionListener(this);
        changeLastSeenButton.addActionListener(this);
        changePasswordButton.addActionListener(this);
        deleteAccountButton.addActionListener(this);
        deActiveButton.addActionListener(this);
        settingStatusButton.addActionListener(this);
        refreshServerButton.addActionListener(this);

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
        if (settingStatusButton == e.getSource()){
            ShowSettingStatusEvent showSettingStatusEvent = new ShowSettingStatusEvent(source);
            showSettingStatusListener.eventOccurred(showSettingStatusEvent);
        }
        if (deleteAccountButton == e.getSource()){
            GoToDeleteAccountEvent goToDeleteAccountEvent = new GoToDeleteAccountEvent(source);
            goToDeleteAccountListener.eventOccurred(goToDeleteAccountEvent);
        }
        if (deActiveButton == e.getSource()){
            GoToDeActiveAccountEvent goToDeActiveAccountEvent = new GoToDeActiveAccountEvent(source);
            goToDeActiveAccountListener.eventOccurred(goToDeActiveAccountEvent);
        }
        if (changePasswordButton == e.getSource()){
            GoToChangePasswordEvent goToChangePasswordEvent = new GoToChangePasswordEvent(source);
            goToChangePasswordListener.eventOccurred(goToChangePasswordEvent);
        }
        if (changeLastSeenButton == e.getSource()){
            GoToChangeLastSeenEvent goToChangeLastSeenEvent = new GoToChangeLastSeenEvent(source);
            goToChangeLastSeenListener.eventOccurred(goToChangeLastSeenEvent);
        }
        if (changePrivacyButton == e.getSource()){
            GoToChangePrivacyEvent goToChangePrivacyEvent = new GoToChangePrivacyEvent(source);
            goToChangePrivacyListener.eventOccurred(goToChangePrivacyEvent);
        }
        if (refreshServerButton == e.getSource()){
            MainController.refreshServer();
        }
    }
}
