package ir.sharif.math.ap99_2.tinsta_client.explorer.view;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.StartChatListener;
import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.BlockOrUnBlockListener;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.FollowOrUnfollowListener;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.MuteOrUnMuteListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.StartChatEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.BlockOrUnblockEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.FollowOrUnfollowEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.MuteOrUnMuteEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowUserProfileView extends JPanel implements ActionListener {

    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToButton =new JButton("Back");
    private final JButton followOrUnFollowButton =new JButton("Follow & UnFollow");
    private final JButton muteOrUnMuteButton =new JButton("Mute & UnMute");
    private final JButton blockOrUnBlockButton =new JButton("Block & UnBlock");
    private final JButton sendMessageButton =new JButton("Send Message");
    private final JButton reportUserButton =new JButton("Report User");
    private UserProfileView userProfileView ; //should set this
    private final ColorConfig colorConfig = new ColorConfig();
    private int userId;
    private BackToLoginListener backToLoginListener;
    private BackToListener backToListener;
    private FollowOrUnfollowListener followOrUnfollowListener;
    private MuteOrUnMuteListener muteOrUnMuteListener;
    private BlockOrUnBlockListener blockOrUnBlockListener;
    private StartChatListener startChatListener;


    public ShowUserProfileView(JPanel source) {
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

        this.add(backToButton);
        this.add(logoutButton);
        this.add(followOrUnFollowButton);
        this.add(muteOrUnMuteButton);
        this.add(blockOrUnBlockButton);
        this.add(sendMessageButton);
        this.add(userProfileView);
        this.add(reportUserButton);

    }

    // should call this after setting userProfileView
    public void configElements(){
        followOrUnFollowButton.setFont(followOrUnFollowButton.getFont().deriveFont(14.0f));
        muteOrUnMuteButton.setFont(muteOrUnMuteButton.getFont().deriveFont(14.0f));
        blockOrUnBlockButton.setFont(blockOrUnBlockButton.getFont().deriveFont(14.0f));

        sendMessageButton.setFont(sendMessageButton.getFont().deriveFont(14.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(14.0f));
        backToButton.setFont(backToButton.getFont().deriveFont(14.0f));
        reportUserButton.setFont(reportUserButton.getFont().deriveFont(14.0f));



        logoutButton.setBackground(colorConfig.getColor3());
        backToButton.setBackground(colorConfig.getColor3());
        followOrUnFollowButton.setBackground(colorConfig.getColor3());
        muteOrUnMuteButton.setBackground(colorConfig.getColor3());
        blockOrUnBlockButton.setBackground(colorConfig.getColor3());
        sendMessageButton.setBackground(colorConfig.getColor3());
        reportUserButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToButton.setForeground(colorConfig.getColor0());
        followOrUnFollowButton.setForeground(colorConfig.getColor0());
        muteOrUnMuteButton.setForeground(colorConfig.getColor0());
        blockOrUnBlockButton.setForeground(colorConfig.getColor0());
        sendMessageButton.setForeground(colorConfig.getColor0());
        reportUserButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToButton.setFocusable(false);
        followOrUnFollowButton.setFocusable(false);
        muteOrUnMuteButton.setFocusable(false);
        blockOrUnBlockButton.setFocusable(false);
        sendMessageButton.setFocusable(false);
        reportUserButton.setFocusable(false);

        PanelConfig panelConfig = new PanelConfig();
        ButtonConfig buttonConfig = new ButtonConfig();


        userProfileView.setBounds(200,100,panelConfig.getUserProfilePanel()
                .getWidth(),panelConfig.getUserProfilePanel().getHeight());

        reportUserButton.setBounds(340,50,buttonConfig.getB120x25()
                .getWidth(),buttonConfig.getB120x25().getHeight());

        followOrUnFollowButton.setBounds(50,400,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        muteOrUnMuteButton.setBounds(300,400,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        blockOrUnBlockButton.setBounds(550,400,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());

        sendMessageButton.setBounds(50,500,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        backToButton.setBounds(300,500,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        logoutButton.setBounds(550,500,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());


        logoutButton.addActionListener(this);
        backToButton.addActionListener(this);
        followOrUnFollowButton.addActionListener(this);
        muteOrUnMuteButton.addActionListener(this);
        blockOrUnBlockButton.addActionListener(this);
        sendMessageButton.addActionListener(this);
        reportUserButton.addActionListener(this);
        addElements();

    }

    public void configUserProfileView(){
        PanelConfig panelConfig = new PanelConfig();
        userProfileView.setBounds(200,100,panelConfig.getUserProfilePanel()
                .getWidth(),panelConfig.getUserProfilePanel().getHeight());
        this.add(userProfileView);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserProfileView(UserProfileView userProfileView) {
        this.userProfileView = userProfileView;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setFollowOrUnfollowListener(FollowOrUnfollowListener followOrUnfollowListener) {
        this.followOrUnfollowListener = followOrUnfollowListener;
    }

    public void setMuteOrUnMuteListener(MuteOrUnMuteListener muteOrUnMuteListener) {
        this.muteOrUnMuteListener = muteOrUnMuteListener;
    }

    public void setBlockOrUnBlockListener(BlockOrUnBlockListener blockOrUnBlockListener) {
        this.blockOrUnBlockListener = blockOrUnBlockListener;
    }

    public void setStartChatListener(StartChatListener startChatListener) {
        this.startChatListener = startChatListener;
    }

    public UserProfileView getUserProfileView() {
        return userProfileView;
    }


    public JPanel getSource() {
        return source;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (sendMessageButton == e.getSource()){
            StartChatEvent startChatEvent = new StartChatEvent(source,getUserId());
            startChatListener.eventOccurred(startChatEvent);
        }
        if (backToButton == e.getSource()){
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (followOrUnFollowButton == e.getSource()){
            FollowOrUnfollowEvent followOrUnfollowEvent = new FollowOrUnfollowEvent(this);
            followOrUnfollowListener.eventOccurred(followOrUnfollowEvent);
        }
        if (muteOrUnMuteButton == e.getSource()){
            MuteOrUnMuteEvent muteOrUnMuteEvent = new MuteOrUnMuteEvent(this);
            muteOrUnMuteListener.eventOccurred(muteOrUnMuteEvent);
        }
        if (blockOrUnBlockButton == e.getSource()){
            BlockOrUnblockEvent blockOrUnblockEvent = new BlockOrUnblockEvent(this);
            blockOrUnBlockListener.eventOccurred(blockOrUnblockEvent);
        }
        if (reportUserButton == e.getSource()){
            String message = "Done!";
            JOptionPane.showMessageDialog(this, message,
                    "Report User!",JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
