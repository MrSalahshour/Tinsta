package ir.sharif.math.ap99_2.tinsta_client.sharing_related.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.ForwardToOneUserListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToOneUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForwardToOneUserView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JTextField userNameTextField = new JTextField();
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToButton =new JButton("Back");
    private final JButton forwardButton =new JButton("Forward");
    private final JLabel userNameLabel = new JLabel("User Name");
    private final ColorConfig colorConfig = new ColorConfig();
    private final int messageId;
    private BackToListener backToListener;
    private BackToLoginListener backToLoginListener;
    private ForwardToOneUserListener forwardToOneUserListener;


    public ForwardToOneUserView(JPanel source, int messageId) {
        this.source = source;
        this.messageId = messageId;
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
        this.add(userNameTextField);
        this.add(forwardButton);
        this.add(backToButton);
        this.add(logoutButton);
        this.add(userNameLabel);

    }

    private void configElements(){
        forwardButton.setFont(forwardButton.getFont().deriveFont(15.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToButton.setFont(backToButton.getFont().deriveFont(15.0f));
        userNameTextField.setFont(backToButton.getFont().deriveFont(15.0f));

        logoutButton.setBackground(colorConfig.getColor3());
        backToButton.setBackground(colorConfig.getColor3());
        forwardButton.setBackground(colorConfig.getColor3());
        userNameLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToButton.setForeground(colorConfig.getColor0());
        forwardButton.setForeground(colorConfig.getColor0());
        userNameLabel.setForeground(colorConfig.getColor0());



        logoutButton.setFocusable(false);
        backToButton.setFocusable(false);
        forwardButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();



        userNameLabel.setBounds(235,240,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());
        userNameTextField.setBounds(345,240,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        forwardButton.setBounds(300,300,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        backToButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());



        logoutButton.addActionListener(this);
        backToButton.addActionListener(this);
        forwardButton.addActionListener(this);


    }

    public int getMessageId() {
        return messageId;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setForwardToOneUserListener(ForwardToOneUserListener forwardToOneUserListener) {
        this.forwardToOneUserListener = forwardToOneUserListener;
    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToButton == e.getSource()){
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }
        if (forwardButton == e.getSource()){
            if (checkValidation()){
                ForwardToOneUserEvent forwardToOneUserEvent = new ForwardToOneUserEvent(this,getUserNameTextField());
                try {
                    forwardToOneUserListener.eventOccurred(forwardToOneUserEvent);
                } catch (UserNotExist userNotExist) {
                    String message = userNotExist.getMessage();
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
