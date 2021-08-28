package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.BackToChatRoomListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.GetUserIdListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.StartChatListener;
import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.BackToChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GetUserIdEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.StartChatEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrivateChatsMenuView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton goToExistingChatButton = new JButton("Go To Existing Chat");
    private final JButton startNewChatButton = new JButton("Start New Chat");
    private final JLabel userNameLabel = new JLabel("User Name: ",SwingConstants.RIGHT);
    private final JTextField userNameTextField = new JTextField(15);
    private final JButton backToChatRoomButton = new JButton("Back To ChatRoom");
    private final JButton logoutButton = new JButton("Logout");
    private final ColorConfig colorConfig = new ColorConfig();
    private StartChatListener startChatListener;
    private BackToLoginListener backToLoginListener;
    private GetUserIdListener getUserIdListener;
    private BackToChatRoomListener backToChatRoomListener;
    private int userId;

    public PrivateChatsMenuView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();

        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);

        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements();
        addElements();
    }

    private void addElements(){
        this.add(startNewChatButton);
        this.add(userNameLabel);
        this.add(userNameTextField);
        this.add(goToExistingChatButton);
        this.add(backToChatRoomButton);
        this.add(logoutButton);
    }

    private void configElements(){
        backToChatRoomButton.setFont(backToChatRoomButton.getFont().deriveFont(15.0f));
        goToExistingChatButton.setFont(goToExistingChatButton.getFont().deriveFont(15.0f));
        startNewChatButton.setFont(startNewChatButton.getFont().deriveFont(15.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(20.0f));
        userNameTextField.setFont(userNameTextField.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        userNameTextField.setFont(userNameTextField.getFont().deriveFont(25.0f));



        goToExistingChatButton.setBackground(colorConfig.getColor3());
        startNewChatButton.setBackground(colorConfig.getColor3());
        userNameLabel.setBackground(colorConfig.getColor3());
        backToChatRoomButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());

        goToExistingChatButton.setForeground(colorConfig.getColor0());
        startNewChatButton.setForeground(colorConfig.getColor0());
        userNameLabel.setForeground(colorConfig.getColor0());
        backToChatRoomButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());

        backToChatRoomButton.setFocusable(false);
        goToExistingChatButton.setFocusable(false);
        logoutButton.setFocusable(false);
        startNewChatButton.setFocusable(false);
        userNameLabel.setFocusable(false);

        backToChatRoomButton.addActionListener(this);
        goToExistingChatButton.addActionListener(this);
        logoutButton.addActionListener(this);
        startNewChatButton.addActionListener(this);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();

        backToChatRoomButton.setBounds(200,10,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        userNameLabel.setBounds(150,220,labelConfig.getL150x50().getWidth(),labelConfig.getL150x50().getHeight());
        userNameTextField.setBounds(360,220,textFieldConfig.gettF250x50()
                .getWidth(),textFieldConfig.gettF250x50().getHeight());
        goToExistingChatButton.setBounds(175,320,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        startNewChatButton.setBounds(425,320,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStartChatListener(StartChatListener startChatListener) {
        this.startChatListener = startChatListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGetUserIdListener(GetUserIdListener getUserIdListener) {
        this.getUserIdListener = getUserIdListener;
    }

    public void setBackToChatRoomListener(BackToChatRoomListener backToChatRoomListener) {
        this.backToChatRoomListener = backToChatRoomListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToChatRoomButton == e.getSource()){
            BackToChatRoomEvent backToChatRoomEvent = new BackToChatRoomEvent(source);
            backToChatRoomListener.eventOccurred(backToChatRoomEvent);
        }
        if (goToExistingChatButton == e.getSource()){
            goToChat();
        }
        if (startNewChatButton == e.getSource()){
            goToChat();
        }

    }
    public boolean checkValidation(){
        if (!Validation.isValidUsername(getUserNameTextField())){
            String message = Validation.getUsernameValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "User Name Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private void goToChat(){
        if (checkValidation()){
            GetUserIdEvent getUserIdEvent = new GetUserIdEvent(this,getUserNameTextField());
            try {
                getUserIdListener.eventOccurred(getUserIdEvent);
                StartChatEvent startChatEvent = new StartChatEvent(source,getUserId());
                startChatListener.eventOccurred(startChatEvent);
            } catch (UserNotExist userNotExist) {
                String message = userNotExist.getMessage();
                JOptionPane.showMessageDialog(source, message,
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
