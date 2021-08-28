package ir.sharif.math.ap99_2.tinsta_client.notification.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.notification.listener.ShowMyFollowRequestsListener;
import ir.sharif.math.ap99_2.tinsta_client.notification.listener.ShowSystemMessagesListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.ShowMyFollowRequestsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.notification.event.ShowSystemMessagesEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToHomePageEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationMenuView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToHomePageButton =new JButton("Back To HomePage");
    private final JButton showSystemMessagesButton=new JButton("Show System Messages");
    private final JButton showFollowRequestButton =new JButton("Show My Follow Request");
    private BackToLoginListener backToLoginListener;
    private GoToHomePageListener goToHomePageListener;
    private ShowSystemMessagesListener showSystemMessagesListener;
    private ShowMyFollowRequestsListener showMyFollowRequestsListener;
    private final ColorConfig colorConfig = new ColorConfig();


    public NotificationMenuView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        BorderLayout borderLayout = new BorderLayout();
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();

    }
    private void addElements(){

        this.add(showSystemMessagesButton,BorderLayout.LINE_END);
        this.add(backToHomePageButton,BorderLayout.PAGE_START);
        this.add(logoutButton,BorderLayout.PAGE_END);
        this.add(showFollowRequestButton,BorderLayout.LINE_START);

    }

    private void configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToHomePageButton.setFont(backToHomePageButton.getFont().deriveFont(15.0f));
        showFollowRequestButton.setFont(showFollowRequestButton.getFont().deriveFont(20.0f));
        showSystemMessagesButton.setFont(showSystemMessagesButton.getFont().deriveFont(20.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToHomePageButton.setBackground(colorConfig.getColor3());
        showFollowRequestButton.setBackground(colorConfig.getColor3());
        showSystemMessagesButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToHomePageButton.setForeground(colorConfig.getColor0());
        showFollowRequestButton.setForeground(colorConfig.getColor0());
        showSystemMessagesButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToHomePageButton.setFocusable(false);
        showFollowRequestButton.setFocusable(false);
        showSystemMessagesButton.setFocusable(false);

        logoutButton.setBorder(border);
        backToHomePageButton.setBorder(border);
        showFollowRequestButton.setBorder(border);
        showSystemMessagesButton.setBorder(border);

        showFollowRequestButton.setPreferredSize(new Dimension(350,200));
        showSystemMessagesButton.setPreferredSize(new Dimension(350,200));


        logoutButton.addActionListener(this);
        backToHomePageButton.addActionListener(this);
        showFollowRequestButton.addActionListener(this);
        showSystemMessagesButton.addActionListener(this);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToHomePageListener(GoToHomePageListener goToHomePageListener) {
        this.goToHomePageListener = goToHomePageListener;
    }

    public void setShowSystemMessagesListener(ShowSystemMessagesListener showSystemMessagesListener) {
        this.showSystemMessagesListener = showSystemMessagesListener;
    }

    public void setShowMyFollowRequestsListener(ShowMyFollowRequestsListener showMyFollowRequestsListener) {
        this.showMyFollowRequestsListener = showMyFollowRequestsListener;
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
        if (showSystemMessagesButton == e.getSource()){
            ShowSystemMessagesEvent showSystemMessagesEvent = new ShowSystemMessagesEvent(source);
            showSystemMessagesListener.eventOccurred(showSystemMessagesEvent);
        }
        if (showFollowRequestButton == e.getSource()){
            ShowMyFollowRequestsEvent showMyFollowRequestsEvent = new ShowMyFollowRequestsEvent(source);
            showMyFollowRequestsListener.eventOccurred(showMyFollowRequestsEvent);
        }

    }
}
