package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.BackToChatRoomListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.ForwardMessageListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.SelectImageForForwardListener;
import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ScrollPaneConfig;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.BackToChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ForwardMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SelectImageForForwardEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteMessageView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton backToChatRoom = new JButton("Back To ChatRoom");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton selectImageButton = new JButton("Select Image");
    private final JButton ForwardButton = new JButton("Forward");
    private final JTextArea messageTextArea = new JTextArea();
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private BackToChatRoomListener backToChatRoomListener;
    private SelectImageForForwardListener selectImageForForwardListener;
    private ForwardMessageListener forwardMessageListener;
    private String imagePath;


    public WriteMessageView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();

        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        BorderLayout borderLayout = new BorderLayout();
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        JScrollPane areaScrollPane = new JScrollPane(messageTextArea);

        ScrollPaneConfig scrollPaneConfig = new ScrollPaneConfig();

        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(scrollPaneConfig.getsP250x250().getWidth()
                , scrollPaneConfig.getsP250x250().getHeight()));
        configElements(border);
        addElements(areaScrollPane);

    }

    private void addElements(JScrollPane areaScrollPane){
        this.add(backToChatRoom,BorderLayout.PAGE_START);
        this.add(ForwardButton,BorderLayout.LINE_START);
        this.add(selectImageButton,BorderLayout.LINE_END);
        this.add(logoutButton,BorderLayout.PAGE_END);
        this.add(areaScrollPane,BorderLayout.CENTER);

    }

    private void configElements(Border border){
        backToChatRoom.setFont(backToChatRoom.getFont().deriveFont(15.0f));
        messageTextArea.setFont(messageTextArea.getFont().deriveFont(15.0f));
        selectImageButton.setFont(selectImageButton.getFont().deriveFont(20.0f));
        ForwardButton.setFont(ForwardButton.getFont().deriveFont(20.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));

        ForwardButton.setBackground(colorConfig.getColor3());
        selectImageButton.setBackground(colorConfig.getColor3());
        backToChatRoom.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());
        messageTextArea.setBackground(colorConfig.getColor6());

        ForwardButton.setForeground(colorConfig.getColor0());
        selectImageButton.setForeground(colorConfig.getColor0());
        backToChatRoom.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());

        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setLineWrap(true);

        backToChatRoom.setBorder(border);
        messageTextArea.setBorder(border);
        logoutButton.setBorder(border);
        selectImageButton.setBorder(border);
        ForwardButton.setBorder(border);

        backToChatRoom.setFocusable(false);
        ForwardButton.setFocusable(false);
        logoutButton.setFocusable(false);
        selectImageButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();

        backToChatRoom.setPreferredSize(new Dimension(buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight()));
        logoutButton.setPreferredSize(new Dimension(buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight()));
        ForwardButton.setPreferredSize(new Dimension(buttonConfig.getB200x200()
                .getWidth(),buttonConfig.getB200x200().getHeight()));
        selectImageButton.setPreferredSize(new Dimension(buttonConfig.getB200x200()
                .getWidth(),buttonConfig.getB200x200().getHeight()));

        backToChatRoom.addActionListener(this);
        ForwardButton.addActionListener(this);
        logoutButton.addActionListener(this);
        selectImageButton.addActionListener(this);

    }

    public String getMessageTextArea() {
        return messageTextArea.getText();
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setBackToChatRoomListener(BackToChatRoomListener backToChatRoomListener) {
        this.backToChatRoomListener = backToChatRoomListener;
    }

    public void setSelectImageForForwardListener(SelectImageForForwardListener selectImageForForwardListener) {
        this.selectImageForForwardListener = selectImageForForwardListener;
    }

    public void setForwardMessageListener(ForwardMessageListener forwardMessageListener) {
        this.forwardMessageListener = forwardMessageListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToChatRoom == e.getSource()){
            BackToChatRoomEvent backToChatRoomEvent = new BackToChatRoomEvent(source);
            backToChatRoomListener.eventOccurred(backToChatRoomEvent);
        }
        if (selectImageButton == e.getSource()){
            SelectImageForForwardEvent selectImageForForwardEvent = new SelectImageForForwardEvent(this);
            selectImageForForwardListener.eventOccurred(selectImageForForwardEvent);
        }
        if (ForwardButton == e.getSource()){
            ForwardMessageEvent forwardMessageEvent = new ForwardMessageEvent(source,getMessageTextArea(),getImagePath());
            forwardMessageListener.eventOccurred(forwardMessageEvent);
        }
    }
}
