package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;

import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToChatRoomListener;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToChatroomEvent;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPrivateChatsView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToChatRoomMenuButton =new JButton("Back To ChatRoom Menu");
    private final JTextArea showPrivateChatsTextArea = new JTextArea();
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToChatRoomListener goToChatRoomListener;


    public ShowPrivateChatsView(JPanel source) {
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
        this.add(backToChatRoomMenuButton,BorderLayout.PAGE_START);
        this.add(logoutButton,BorderLayout.PAGE_END);
    }

    private JScrollPane configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToChatRoomMenuButton.setFont(backToChatRoomMenuButton.getFont().deriveFont(15.0f));
        showPrivateChatsTextArea.setFont(showPrivateChatsTextArea.getFont().deriveFont(20.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToChatRoomMenuButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToChatRoomMenuButton.setForeground(colorConfig.getColor0());

        showPrivateChatsTextArea.setEditable(false);
        showPrivateChatsTextArea.setWrapStyleWord(true);
        showPrivateChatsTextArea.setOpaque(false);
        showPrivateChatsTextArea.setLineWrap(true);


        JScrollPane scrollPane = new JScrollPane(showPrivateChatsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        logoutButton.setFocusable(false);
        backToChatRoomMenuButton.setFocusable(false);

        logoutButton.setBorder(border);
        backToChatRoomMenuButton.setBorder(border);

        logoutButton.addActionListener(this);
        backToChatRoomMenuButton.addActionListener(this);
        return scrollPane;

    }

    public void setShowPrivateChatsTextArea(String showPrivateChatsTextArea) {
        this.showPrivateChatsTextArea.setText(showPrivateChatsTextArea);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToCommunicationSecListener(GoToChatRoomListener goToChatRoomListener) {
        this.goToChatRoomListener = goToChatRoomListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToChatRoomMenuButton == e.getSource()){
            GoToChatroomEvent goToChatroomEvent = new GoToChatroomEvent(source);
            goToChatRoomListener.eventOccurred(goToChatroomEvent);
        }
    }
}
