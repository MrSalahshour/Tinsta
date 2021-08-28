package ir.sharif.math.ap99_2.tinsta_client.sharing_related.view;


import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForwardMessageView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backTo =new JButton("Back");
    private final JButton sendToOneUserButton =new JButton("Send To One User");
    private final JButton sendToAllUsersButton =new JButton("Send To All Users");
    private final JButton sendToMultipleUserNamesButton =new JButton("Send To Multiple UserNames");
    private final JButton sendToAUserFolderButton =new JButton("Send To a UserFolder");
    private final ColorConfig colorConfig = new ColorConfig();
    private final int messageId;
    private BackToLoginListener backToLoginListener;
    private BackToListener backToListener;
    private GoToForwardToOneUserListener goToForwardToOneUserListener;
    private GoToForwardToAllUsersListener goToForwardToAllUsersListener;
    private GoToForwardToMultiUsersListener goToForwardToMultiUsersListener;
    private GoToForwardToUserFolderListener goToForwardToUserFolderListener;




    public ForwardMessageView(JPanel source, int messageId) {
        this.source = source;
        this.messageId = messageId;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        GridLayout gridLayout = new GridLayout(2,3);
        this.setBorder(border);
        this.setLayout(gridLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();
    }
    private void addElements(){
        this.add(sendToOneUserButton);
        this.add(sendToAllUsersButton);
        this.add(sendToMultipleUserNamesButton);
        this.add(sendToAUserFolderButton);
        this.add(backTo);
        this.add(logoutButton);

    }

    private void configElements(Border border){
        sendToOneUserButton.setFont(sendToOneUserButton.getFont().deriveFont(14.0f));
        sendToAllUsersButton.setFont(sendToAllUsersButton.getFont().deriveFont(14.0f));
        sendToMultipleUserNamesButton.setFont(sendToMultipleUserNamesButton.getFont().deriveFont(14.0f));

        sendToAUserFolderButton.setFont(sendToAUserFolderButton.getFont().deriveFont(14.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(14.0f));
        backTo.setFont(backTo.getFont().deriveFont(14.0f));



        logoutButton.setBackground(colorConfig.getColor3());
        backTo.setBackground(colorConfig.getColor3());
        sendToOneUserButton.setBackground(colorConfig.getColor3());
        sendToAllUsersButton.setBackground(colorConfig.getColor3());
        sendToMultipleUserNamesButton.setBackground(colorConfig.getColor3());
        sendToAUserFolderButton.setBackground(colorConfig.getColor3());

        logoutButton.setBorder(border);
        backTo.setBorder(border);
        sendToOneUserButton.setBorder(border);
        sendToAllUsersButton.setBorder(border);
        sendToMultipleUserNamesButton.setBorder(border);
        sendToAUserFolderButton.setBorder(border);


        logoutButton.setForeground(colorConfig.getColor0());
        backTo.setForeground(colorConfig.getColor0());
        sendToOneUserButton.setForeground(colorConfig.getColor0());
        sendToAllUsersButton.setForeground(colorConfig.getColor0());
        sendToMultipleUserNamesButton.setForeground(colorConfig.getColor0());
        sendToAUserFolderButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backTo.setFocusable(false);
        sendToOneUserButton.setFocusable(false);
        sendToAllUsersButton.setFocusable(false);
        sendToMultipleUserNamesButton.setFocusable(false);
        sendToAUserFolderButton.setFocusable(false);



        logoutButton.addActionListener(this);
        backTo.addActionListener(this);
        sendToOneUserButton.addActionListener(this);
        sendToAllUsersButton.addActionListener(this);
        sendToMultipleUserNamesButton.addActionListener(this);
        sendToAUserFolderButton.addActionListener(this);

    }

    public int getMessageId() {
        return messageId;
    }

    public JPanel getSource() {
        return source;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setGoToForwardToOneUserListener(GoToForwardToOneUserListener goToForwardToOneUserListener) {
        this.goToForwardToOneUserListener = goToForwardToOneUserListener;
    }

    public void setGoToForwardToAllUsersListener(GoToForwardToAllUsersListener goToForwardToAllUsersListener) {
        this.goToForwardToAllUsersListener = goToForwardToAllUsersListener;
    }

    public void setGoToForwardToMultiUsersListener(GoToForwardToMultiUsersListener goToForwardToMultiUsersListener) {
        this.goToForwardToMultiUsersListener = goToForwardToMultiUsersListener;
    }

    public void setGoToForwardToUserFolderListener(GoToForwardToUserFolderListener goToForwardToUserFolderListener) {
        this.goToForwardToUserFolderListener = goToForwardToUserFolderListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backTo == e.getSource()){
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);

        }
        if (sendToAllUsersButton == e.getSource()){
            GoToForwardToAllUsersEvent goToForwardToAllUsersEvent = new GoToForwardToAllUsersEvent(this);
            goToForwardToAllUsersListener.eventOccurred(goToForwardToAllUsersEvent);

        }
        if (sendToOneUserButton == e.getSource()){
            GoToForwardToOneUserEvent goToForwardToOneUserEvent = new GoToForwardToOneUserEvent(this);
            goToForwardToOneUserListener.eventOccurred(goToForwardToOneUserEvent);

        }
        if (sendToAUserFolderButton == e.getSource()){
            GoToForwardToUserFolderEvent goToForwardToUserFolderEvent = new GoToForwardToUserFolderEvent(this);
            goToForwardToUserFolderListener.eventOccurred(goToForwardToUserFolderEvent);

        }
        if (sendToMultipleUserNamesButton == e.getSource()){
            GoToForwardToMultiUsersEvent goToForwardToMultiUsersEvent = new GoToForwardToMultiUsersEvent(this);
            goToForwardToMultiUsersListener.eventOccurred(goToForwardToMultiUsersEvent);
        }

    }
}
