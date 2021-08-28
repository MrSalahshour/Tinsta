package ir.sharif.math.ap99_2.tinsta_client.notification.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.UserProfileView;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToNotificationsListener;
import ir.sharif.math.ap99_2.tinsta_client.notification.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToNotificationsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMyFollowRequestView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToNotificationButton =new JButton("Back To Notification Menu");
    private final JButton acceptFollowReqButton=new JButton("Accept Request");
    private final JButton rejectFollowReqButton=new JButton("Reject Request");
    private final JButton rejectFollowReqWithNotifyButton =new JButton("Reject Request With Notify");
    private final JButton nextFollowReqButton=new JButton("Next Request");
    private final JButton previousFollowReqButton=new JButton("Previous Request");
    private UserProfileView userProfileView;
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToNotificationsListener goToNotificationsListener;
    private AcceptFollowReqListener acceptFollowReqListener;
    private RejectFollowReqListener rejectFollowReqListener;
    private RejectFollowReqWithNotifyListener rejectFollowReqWithNotifyListener;
    private NextFollowReqListener nextFollowReqListener;
    private PreviousFollowReqListener previousFollowReqListener;


    public ShowMyFollowRequestView(JPanel source) {
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

        this.add(backToNotificationButton);
        this.add(logoutButton);
        this.add(acceptFollowReqButton);
        this.add(rejectFollowReqButton);
        this.add(rejectFollowReqWithNotifyButton);
        this.add(nextFollowReqButton);
        this.add(previousFollowReqButton);
        this.add(userProfileView);

    }

    public void configElements(){
        acceptFollowReqButton.setFont(acceptFollowReqButton.getFont().deriveFont(12.0f));
        rejectFollowReqButton.setFont(rejectFollowReqButton.getFont().deriveFont(12.0f));
        rejectFollowReqWithNotifyButton.setFont(rejectFollowReqWithNotifyButton.getFont().deriveFont(12.0f));

        nextFollowReqButton.setFont(nextFollowReqButton.getFont().deriveFont(12.0f));
        previousFollowReqButton.setFont(previousFollowReqButton.getFont().deriveFont(12.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(12.0f));
        backToNotificationButton.setFont(backToNotificationButton.getFont().deriveFont(14.0f));



        logoutButton.setBackground(colorConfig.getColor3());
        backToNotificationButton.setBackground(colorConfig.getColor3());
        acceptFollowReqButton.setBackground(colorConfig.getColor3());
        rejectFollowReqButton.setBackground(colorConfig.getColor3());
        rejectFollowReqWithNotifyButton.setBackground(colorConfig.getColor3());
        nextFollowReqButton.setBackground(colorConfig.getColor3());
        previousFollowReqButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToNotificationButton.setForeground(colorConfig.getColor0());
        acceptFollowReqButton.setForeground(colorConfig.getColor0());
        rejectFollowReqButton.setForeground(colorConfig.getColor0());
        rejectFollowReqWithNotifyButton.setForeground(colorConfig.getColor0());
        nextFollowReqButton.setForeground(colorConfig.getColor0());
        previousFollowReqButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToNotificationButton.setFocusable(false);
        acceptFollowReqButton.setFocusable(false);
        rejectFollowReqButton.setFocusable(false);
        rejectFollowReqWithNotifyButton.setFocusable(false);
        nextFollowReqButton.setFocusable(false);
        previousFollowReqButton.setFocusable(false);

        PanelConfig panelConfig = new PanelConfig();
        ButtonConfig buttonConfig = new ButtonConfig();
        userProfileView.setBounds(200,100,panelConfig.getUserProfilePanel()
                .getWidth(),panelConfig.getUserProfilePanel().getHeight());
        acceptFollowReqButton.setBounds(50,400,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        rejectFollowReqButton.setBounds(300,400,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        rejectFollowReqWithNotifyButton.setBounds(550,400,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        nextFollowReqButton.setBounds(50,500,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        previousFollowReqButton.setBounds(300,500,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        logoutButton.setBounds(550,500,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());

        backToNotificationButton.setBounds(200,25,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());


        logoutButton.addActionListener(this);
        backToNotificationButton.addActionListener(this);
        acceptFollowReqButton.addActionListener(this);
        rejectFollowReqButton.addActionListener(this);
        rejectFollowReqWithNotifyButton.addActionListener(this);
        nextFollowReqButton.addActionListener(this);
        previousFollowReqButton.addActionListener(this);

        addElements();

    }

    public void setUserProfileView(UserProfileView userProfileView) {
        this.userProfileView = userProfileView;
    }

    public UserProfileView getUserProfileView() {
        return userProfileView;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToNotificationsListener(GoToNotificationsListener goToNotificationsListener) {
        this.goToNotificationsListener = goToNotificationsListener;
    }

    public void setAcceptFollowReqListener(AcceptFollowReqListener acceptFollowReqListener) {
        this.acceptFollowReqListener = acceptFollowReqListener;
    }

    public void setRejectFollowReqListener(RejectFollowReqListener rejectFollowReqListener) {
        this.rejectFollowReqListener = rejectFollowReqListener;
    }

    public void setRejectFollowReqWithNotifyListener(RejectFollowReqWithNotifyListener rejectFollowReqWithNotifyListener) {
        this.rejectFollowReqWithNotifyListener = rejectFollowReqWithNotifyListener;
    }

    public void setNextFollowReqListener(NextFollowReqListener nextFollowReqListener) {
        this.nextFollowReqListener = nextFollowReqListener;
    }

    public void setPreviousFollowReqListener(PreviousFollowReqListener previousFollowReqListener) {
        this.previousFollowReqListener = previousFollowReqListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToNotificationButton == e.getSource()){
            GoToNotificationsEvent goToNotificationsEvent = new GoToNotificationsEvent(source);
            goToNotificationsListener.eventOccurred(goToNotificationsEvent);
        }
        if (acceptFollowReqButton == e.getSource()){
            AcceptFollowReqEvent acceptFollowReqEvent = new AcceptFollowReqEvent(source);
            acceptFollowReqListener.eventOccurred(acceptFollowReqEvent);
        }
        if (rejectFollowReqButton == e.getSource()){
            RejectFollowReqEvent rejectFollowReqEvent = new RejectFollowReqEvent(source);
            rejectFollowReqListener.eventOccurred(rejectFollowReqEvent);
        }
        if (rejectFollowReqWithNotifyButton == e.getSource()){
            RejectFollowReqWithNotifyEvent rejectFollowReqWithNotifyEvent = new RejectFollowReqWithNotifyEvent(source);
            rejectFollowReqWithNotifyListener.eventOccurred(rejectFollowReqWithNotifyEvent);
        }
        if (nextFollowReqButton == e.getSource()){
            NextFollowReqEvent nextFollowReqEvent = new NextFollowReqEvent(this);
            nextFollowReqListener.eventOccurred(nextFollowReqEvent);
        }
        if (previousFollowReqButton == e.getSource()){
            PreviousFollowReqEvent previousFollowReqEvent = new PreviousFollowReqEvent(this);
            previousFollowReqListener.eventOccurred(previousFollowReqEvent);
        }

    }
}
