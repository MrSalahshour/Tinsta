package ir.sharif.math.ap99_2.tinsta_client.user_related.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton homePageButton = new JButton("HomePage");
    private final JButton timeLineButton = new JButton("TimeLine");
    private final JButton explorerButton = new JButton("Explorer");
    private final JButton chatroomButton = new JButton("ChatRoom");
    private final JButton settingButton = new JButton("Setting");
    private final JButton logoutButton = new JButton("Logout");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToHomePageListener goToHomePageListener;
    private GoToTimeLineListener goToTimeLineListener;
    private GoToExplorerListener goToExplorerListener;
    private GoToChatRoomListener goToChatRoomListener;
    private GoToSettingListener goToSettingListener;

    public MenuView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        GridLayout gridLayout = new GridLayout(3,2);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        Border border = BorderFactory.createLineBorder(colorConfig.getColor0(),10);
        this.setBorder(border);
        this.setLayout(gridLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor0());
        configButtons();
        addButtons();

    }
    private void addButtons(){
        this.add(homePageButton);
        this.add(timeLineButton);
        this.add(explorerButton);
        this.add(chatroomButton);
        this.add(settingButton);
        this.add(logoutButton);
    }

    private void configButtons(){
        homePageButton.setFont(homePageButton.getFont().deriveFont(20.0f));
        timeLineButton.setFont(timeLineButton.getFont().deriveFont(20.0f));
        explorerButton.setFont(explorerButton.getFont().deriveFont(20.0f));
        chatroomButton.setFont(chatroomButton.getFont().deriveFont(20.0f));
        settingButton.setFont(settingButton.getFont().deriveFont(20.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(20.0f));


        homePageButton.setBackground(colorConfig.getColor3());
        timeLineButton.setBackground(colorConfig.getColor3());
        explorerButton.setBackground(colorConfig.getColor3());
        chatroomButton.setBackground(colorConfig.getColor3());
        settingButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());

        homePageButton.setForeground(colorConfig.getColor0());
        timeLineButton.setForeground(colorConfig.getColor0());
        explorerButton.setForeground(colorConfig.getColor0());
        chatroomButton.setForeground(colorConfig.getColor0());
        settingButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());

        homePageButton.setBorder(BorderFactory.createEmptyBorder());
        timeLineButton.setBorder(BorderFactory.createEmptyBorder());
        explorerButton.setBorder(BorderFactory.createEmptyBorder());
        chatroomButton.setBorder(BorderFactory.createEmptyBorder());
        settingButton.setBorder(BorderFactory.createEmptyBorder());
        logoutButton.setBorder(BorderFactory.createEmptyBorder());

        homePageButton.setFocusable(false);
        timeLineButton.setFocusable(false);
        explorerButton.setFocusable(false);
        chatroomButton.setFocusable(false);
        settingButton.setFocusable(false);
        logoutButton.setFocusable(false);

        homePageButton.addActionListener(this);
        timeLineButton.addActionListener(this);
        explorerButton.addActionListener(this);
        chatroomButton.addActionListener(this);
        settingButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToHomePageListener(GoToHomePageListener goToHomePageListener) {
        this.goToHomePageListener = goToHomePageListener;
    }

    public void setGoToTimeLineListener(GoToTimeLineListener goToTimeLineListener) {
        this.goToTimeLineListener = goToTimeLineListener;
    }

    public void setGoToExplorerListener(GoToExplorerListener goToExplorerListener) {
        this.goToExplorerListener = goToExplorerListener;
    }

    public void setGoToChatRoomListener(GoToChatRoomListener goToChatRoomListener) {
        this.goToChatRoomListener = goToChatRoomListener;
    }

    public void setGoToSettingListener(GoToSettingListener goToSettingListener) {
        this.goToSettingListener = goToSettingListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (homePageButton == e.getSource()){
            GoToHomePageEvent goToHomePageEvent = new GoToHomePageEvent(source);
            goToHomePageListener.eventOccurred(goToHomePageEvent);
        }
        if (timeLineButton == e.getSource()){
            GoToTimeLineEvent goToTimeLineEvent = new GoToTimeLineEvent(source);
            goToTimeLineListener.eventOccurred(goToTimeLineEvent);
        }
        if (explorerButton == e.getSource()){
            GoToExplorerEvent goToExplorerEvent = new GoToExplorerEvent(source);
            goToExplorerListener.eventOccurred(goToExplorerEvent);
        }
        if (chatroomButton == e.getSource()){
            GoToChatroomEvent goToChatroomEvent = new GoToChatroomEvent(source);
            goToChatRoomListener.eventOccurred(goToChatroomEvent);

        }
        if (settingButton == e.getSource()){
            GoToSettingEvent goToSettingEvent = new GoToSettingEvent(source);
            goToSettingListener.eventOccurred(goToSettingEvent);

        }
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }

    }
}
