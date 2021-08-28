package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.AddUserToGPListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.BackToChatRoomListener;
import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.AddUserToGPEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.BackToChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.CantAddUserToGP;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserToGPView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton addToGroupButton = new JButton("Add To Group");
    private final JLabel groupNameLabel = new JLabel("Group Name: ",SwingConstants.CENTER);
    private final JLabel userNameLabel = new JLabel("User Name: ",SwingConstants.CENTER);
    private final JTextField groupNameTextField = new JTextField(20);
    private final JTextField userNameTextField = new JTextField(20);
    private final JButton backToChatRoomButton = new JButton("Back To ChatRoom");
    private final JButton logoutButton = new JButton("Logout");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private BackToChatRoomListener backToChatRoomListener;
    private AddUserToGPListener addUserToGPListener;


    public AddUserToGPView(JPanel source) {
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
        this.add(addToGroupButton);
        this.add(groupNameLabel);
        this.add(groupNameTextField);
        this.add(userNameTextField);
        this.add(userNameLabel);
        this.add(backToChatRoomButton);
        this.add(logoutButton);
    }

    private void configElements(){
        backToChatRoomButton.setFont(backToChatRoomButton.getFont().deriveFont(15.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(15.0f));
        addToGroupButton.setFont(addToGroupButton.getFont().deriveFont(15.0f));
        groupNameLabel.setFont(groupNameLabel.getFont().deriveFont(15.0f));
        groupNameTextField.setFont(groupNameTextField.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        userNameTextField.setFont(groupNameTextField.getFont().deriveFont(15.0f));



        userNameLabel.setBackground(colorConfig.getColor3());
        addToGroupButton.setBackground(colorConfig.getColor3());
        groupNameLabel.setBackground(colorConfig.getColor3());
        backToChatRoomButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());

        userNameLabel.setForeground(colorConfig.getColor0());
        addToGroupButton.setForeground(colorConfig.getColor0());
        groupNameLabel.setForeground(colorConfig.getColor0());
        backToChatRoomButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());

        backToChatRoomButton.setFocusable(false);
        logoutButton.setFocusable(false);
        addToGroupButton.setFocusable(false);

        backToChatRoomButton.addActionListener(this);
        logoutButton.addActionListener(this);
        addToGroupButton.addActionListener(this);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();

        userNameTextField.setBounds(350,205,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        groupNameTextField.setBounds(350,250,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        userNameLabel.setBounds(250,205,labelConfig.getL100x20().getWidth(),
                labelConfig.getL100x20().getHeight());
        groupNameLabel.setBounds(250,250,labelConfig.getL100x20().getWidth(),
                labelConfig.getL100x20().getHeight());
        backToChatRoomButton.setBounds(200,10,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        addToGroupButton.setBounds(300,350,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());

    }

    public String getGroupNameTextField() {
        return groupNameTextField.getText();
    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToChatRoomListener(BackToChatRoomListener backToChatRoomListener) {
        this.backToChatRoomListener = backToChatRoomListener;
    }

    public void setAddUserToGPListener(AddUserToGPListener addUserToGPListener) {
        this.addUserToGPListener = addUserToGPListener;
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
        if (addToGroupButton == e.getSource()){
            if (checkValidation()){
                AddUserToGPEvent addUserToGPEvent = new AddUserToGPEvent
                        (source,getUserNameTextField(),getGroupNameTextField());
                try {
                    addUserToGPListener.eventOccurred(addUserToGPEvent);
                } catch (UserNotExist | GroupNotExist | CantAddUserToGP exception) {
                    String message = exception.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
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
}
