package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;





import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.BackToMenuEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatRoomView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton savedMessagesButton = new JButton("Saved Messages");
    private final JButton showPrivateChatsButton = new JButton("Show Private Chats");
    private final JButton showGroupChatsButton = new JButton("Show Group Chats");
    private final JButton goToPrivateChatsButton = new JButton("Go To Private Chats");
    private final JButton goToGroupChatsButton = new JButton("Go To Group Chats");
    private final JButton writeMessagesButton = new JButton("Write Message");
    private final JButton addToGroupButton = new JButton("Add To Group");
    private final JButton backToMenuButton = new JButton("Back To Menu ");
    private final JButton logoutButton = new JButton("Logout");
    private final ColorConfig colorConfig = new ColorConfig();
    private ShowSavedMessageListener showSavedMessageListener;
    private ShowPrivateChatsListener showPrivateChatsListener;
    private ShowGroupChatsListener showGroupChatsListener;
    private GoToPrivateChatsListener goToPrivateChatsListener;
    private GoToGroupChatsListener goToGroupChatsListener;
    private GoToForwardMessageListener goToForwardMessageListener;
    private GoToAddToGPListener goToAddToGPListener;
    private BackToLoginListener backToLoginListener;
    private BackToMenuListener backToMenuListener;


    public ChatRoomView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();

        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        GridLayout gridLayout = new GridLayout(3,3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements();
        addElements();
    }

    private void addElements(){
        this.add(showPrivateChatsButton);
        this.add(showGroupChatsButton);
        this.add(goToPrivateChatsButton);
        this.add(goToGroupChatsButton);
        this.add(savedMessagesButton);
        this.add(writeMessagesButton);
        this.add(addToGroupButton);
        this.add(backToMenuButton);
        this.add(logoutButton);
    }

    public JPanel getSource() {
        return source;
    }

    private void configElements(){
        backToMenuButton.setFont(backToMenuButton.getFont().deriveFont(15.0f));
        savedMessagesButton.setFont(savedMessagesButton.getFont().deriveFont(15.0f));
        showPrivateChatsButton.setFont(showPrivateChatsButton.getFont().deriveFont(15.0f));
        showGroupChatsButton.setFont(showGroupChatsButton.getFont().deriveFont(15.0f));
        writeMessagesButton.setFont(writeMessagesButton.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        goToGroupChatsButton.setFont(goToGroupChatsButton.getFont().deriveFont(15.0f));
        goToPrivateChatsButton.setFont(goToPrivateChatsButton.getFont().deriveFont(15.0f));
        addToGroupButton.setFont(addToGroupButton.getFont().deriveFont(15.0f));


        savedMessagesButton.setBackground(colorConfig.getColor3());
        showPrivateChatsButton.setBackground(colorConfig.getColor3());
        showGroupChatsButton.setBackground(colorConfig.getColor3());
        writeMessagesButton.setBackground(colorConfig.getColor3());
        backToMenuButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());
        goToGroupChatsButton.setBackground(colorConfig.getColor3());
        goToPrivateChatsButton.setBackground(colorConfig.getColor3());
        addToGroupButton.setBackground(colorConfig.getColor3());

        savedMessagesButton.setForeground(colorConfig.getColor0());
        showPrivateChatsButton.setForeground(colorConfig.getColor0());
        showGroupChatsButton.setForeground(colorConfig.getColor0());
        writeMessagesButton.setForeground(colorConfig.getColor0());
        backToMenuButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());
        goToGroupChatsButton.setForeground(colorConfig.getColor0());
        goToPrivateChatsButton.setForeground(colorConfig.getColor0());
        addToGroupButton.setForeground(colorConfig.getColor0());

        backToMenuButton.setFocusable(false);
        savedMessagesButton.setFocusable(false);
        logoutButton.setFocusable(false);
        showPrivateChatsButton.setFocusable(false);
        showGroupChatsButton.setFocusable(false);
        writeMessagesButton.setFocusable(false);
        goToGroupChatsButton.setFocusable(false);
        goToPrivateChatsButton.setFocusable(false);
        addToGroupButton.setFocusable(false);

        backToMenuButton.addActionListener(this);
        savedMessagesButton.addActionListener(this);
        logoutButton.addActionListener(this);
        showPrivateChatsButton.addActionListener(this);
        showGroupChatsButton.addActionListener(this);
        writeMessagesButton.addActionListener(this);
        goToGroupChatsButton.addActionListener(this);
        goToPrivateChatsButton.addActionListener(this);
        addToGroupButton.addActionListener(this);

    }

    public void setShowSavedMessageListener(ShowSavedMessageListener showSavedMessageListener) {
        this.showSavedMessageListener = showSavedMessageListener;
    }

    public void setShowPrivateChatsListener(ShowPrivateChatsListener showPrivateChatsListener) {
        this.showPrivateChatsListener = showPrivateChatsListener;
    }

    public void setShowGroupChatsListener(ShowGroupChatsListener showGroupChatsListener) {
        this.showGroupChatsListener = showGroupChatsListener;
    }

    public void setGoToPrivateChatsListener(GoToPrivateChatsListener goToPrivateChatsListener) {
        this.goToPrivateChatsListener = goToPrivateChatsListener;
    }

    public void setGoToGroupChatsListener(GoToGroupChatsListener goToGroupChatsListener) {
        this.goToGroupChatsListener = goToGroupChatsListener;
    }

    public void setGoToForwardMessageListener(GoToForwardMessageListener goToForwardMessageListener) {
        this.goToForwardMessageListener = goToForwardMessageListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToMenuListener(BackToMenuListener backToMenuListener) {
        this.backToMenuListener = backToMenuListener;
    }

    public void setGoToAddToGPListener(GoToAddToGPListener goToAddToGPListener) {
        this.goToAddToGPListener = goToAddToGPListener;
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
        if (savedMessagesButton == e.getSource()){
            ShowSavedMessageEvent showSavedMessageEvent = new ShowSavedMessageEvent(this);
            showSavedMessageListener.eventOccurred(showSavedMessageEvent);
        }
        if (showPrivateChatsButton == e.getSource()){
            ShowPrivateChatsEvent showPrivateChatsEvent = new ShowPrivateChatsEvent(this);
            showPrivateChatsListener.eventOccurred(showPrivateChatsEvent);
        }
        if (showGroupChatsButton == e.getSource()){
            ShowGroupChatsEvent showGroupChatsEvent = new ShowGroupChatsEvent(this);
            showGroupChatsListener.eventOccurred(showGroupChatsEvent);
        }
        if (goToPrivateChatsButton == e.getSource()){
            GoToPrivateChatsEvent goToPrivateChatsEvent = new GoToPrivateChatsEvent(this);
            goToPrivateChatsListener.eventOccurred(goToPrivateChatsEvent);
        }
        if (goToGroupChatsButton == e.getSource()){
            GoToGroupChatsEvent goToGroupChatsEvent = new GoToGroupChatsEvent(this);
            goToGroupChatsListener.eventOccurred(goToGroupChatsEvent);
        }
        if (addToGroupButton == e.getSource()){
            GoToAddToGPEvent goToAddToGPEvent = new GoToAddToGPEvent(this);
            goToAddToGPListener.eventOccurred(goToAddToGPEvent);
        }
        if (writeMessagesButton == e.getSource()){
            GoToForwardMessageEvent goToForwardMessageEvent = new GoToForwardMessageEvent(this);
            goToForwardMessageListener.eventOccurred(goToForwardMessageEvent);
        }
    }

    @Override
    public String toString() {
        return "ChatRoomView";
    }
}
