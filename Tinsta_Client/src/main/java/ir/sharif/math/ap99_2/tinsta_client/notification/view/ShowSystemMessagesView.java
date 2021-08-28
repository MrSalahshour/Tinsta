package ir.sharif.math.ap99_2.tinsta_client.notification.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToNotificationsListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToNotificationsEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowSystemMessagesView extends JPanel implements ActionListener {

    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToNotificationButton =new JButton("Back To Notification Menu");
    private final JTextArea systemMessagesTextArea = new JTextArea();
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToNotificationsListener goToNotificationsListener;


    public ShowSystemMessagesView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        BorderLayout borderLayout = new BorderLayout();
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        JScrollPane scrollPane =configElements(border);
        addElements(scrollPane);


    }
    private void addElements(JScrollPane scrollPane){

        this.add(scrollPane,BorderLayout.CENTER);
        this.add(backToNotificationButton,BorderLayout.PAGE_START);
        this.add(logoutButton,BorderLayout.PAGE_END);

    }

    private JScrollPane configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToNotificationButton.setFont(backToNotificationButton.getFont().deriveFont(15.0f));
        systemMessagesTextArea.setFont(systemMessagesTextArea.getFont().deriveFont(20.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToNotificationButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToNotificationButton.setForeground(colorConfig.getColor0());

        systemMessagesTextArea.setEditable(false);
        systemMessagesTextArea.setWrapStyleWord(true);
        systemMessagesTextArea.setOpaque(false);
        systemMessagesTextArea.setLineWrap(true);


        JScrollPane scrollPane = new JScrollPane(systemMessagesTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        logoutButton.setFocusable(false);
        backToNotificationButton.setFocusable(false);

        logoutButton.setBorder(border);
        backToNotificationButton.setBorder(border);

        logoutButton.addActionListener(this);
        backToNotificationButton.addActionListener(this);
        return scrollPane;

    }

    public void setSystemMessagesTextArea(String systemMessagesTextArea) {
        this.systemMessagesTextArea.setText(systemMessagesTextArea);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToNotificationsListener(GoToNotificationsListener goToNotificationsListener) {
        this.goToNotificationsListener = goToNotificationsListener;
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
    }
}
