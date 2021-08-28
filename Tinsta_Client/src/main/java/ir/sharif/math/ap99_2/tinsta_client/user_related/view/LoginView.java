package ir.sharif.math.ap99_2.tinsta_client.user_related.view;


import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToRegisterListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.LoginFormListener;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToRegisterEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.LoginFormEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JTextField userNameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JLabel userNameLabel = new JLabel("UserName: ");
    private final JLabel passwordLabel = new JLabel("Password: ");
    private final JLabel welcomeLabel = new JLabel("Welcome To Tinsta",SwingConstants.CENTER);
    private final JLabel registerQuestionLabel = new JLabel("Don't have an account?",SwingConstants.CENTER);
    private final JButton loginButton = new JButton("Login");
    private final JButton backToRegisterButton = new JButton("Register");
    private final ColorConfig colorConfig = new ColorConfig();
    private LoginFormListener loginFormListener;
    private BackToRegisterListener backToRegisterListener;



    public LoginView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();

        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor3());
        configElements();
        addElements();

    }
    private void addElements(){
        this.add(userNameField);
        this.add(userNameLabel);
        this.add(passwordField);
        this.add(passwordLabel);
        this.add(loginButton);
        this.add(welcomeLabel);
        this.add(registerQuestionLabel);
        this.add(backToRegisterButton);
    }

    private void configElements(){
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        userNameField.setBounds(350,205,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        passwordField.setBounds(350,250,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());

        LabelConfig labelConfig = new LabelConfig();

        userNameLabel.setBounds(250,205,labelConfig.getL100x20().getWidth(),
                labelConfig.getL100x20().getHeight());
        passwordLabel.setBounds(250,250,labelConfig.getL100x20().getWidth(),
                labelConfig.getL100x20().getHeight());
        welcomeLabel.setBounds(300,0,labelConfig.getL200x100().getWidth(),
                labelConfig.getL200x100().getHeight());
        registerQuestionLabel.setBounds(340,425,labelConfig.getL120x20()
                .getWidth(),labelConfig.getL120x20().getHeight());

        ButtonConfig buttonConfig = new ButtonConfig();

        backToRegisterButton.setBounds(340,450,buttonConfig.getB120x25()
                .getWidth(),buttonConfig.getB120x25().getHeight());
        loginButton.setBounds(300,350,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());

        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(20.0f));
        loginButton.setFont(loginButton.getFont().deriveFont(15.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(17.0f));
        userNameField.setFont(userNameField.getFont().deriveFont(15.0f));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(17.0f));
        passwordField.setFont(passwordField.getFont().deriveFont(15.0f));
        registerQuestionLabel.setFont(registerQuestionLabel.getFont().deriveFont(9.0f));
        backToRegisterButton.setFont(backToRegisterButton.getFont().deriveFont(10.0f));

        userNameLabel.setForeground(colorConfig.getColor0());
        passwordLabel.setForeground(colorConfig.getColor0());
        welcomeLabel.setForeground(colorConfig.getColor0());
        registerQuestionLabel.setForeground(colorConfig.getColor0());
        loginButton.setForeground(colorConfig.getColor5());
        backToRegisterButton.setForeground(colorConfig.getColor5());



        loginButton.setFocusable(false);
        backToRegisterButton.setFocusable(false);

        loginButton.addActionListener(this);
        backToRegisterButton.addActionListener(this);

    }


    public String getUserNameField() {
        return userNameField.getText();
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public void setLoginFormListener(LoginFormListener loginFormListener) {
        this.loginFormListener = loginFormListener;
    }

    public void setBackToRegisterListener(BackToRegisterListener backToRegisterListener) {
        this.backToRegisterListener = backToRegisterListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (loginButton == e.getSource()){
            if (checkValidation()){
                LoginFormEvent loginFormEvent = new LoginFormEvent
                        (source,getUserNameField(),getPasswordField());
                try{
                    loginFormListener.eventOccurred(loginFormEvent);
                }
                catch (UserNotExist userNotExist){
                    String message = userNotExist.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (backToRegisterButton == e.getSource()){
            BackToRegisterEvent backToRegisterEvent = new BackToRegisterEvent(source);
            backToRegisterListener.eventOccurred(backToRegisterEvent);
        }
    }
    public boolean checkValidation(){
        if (!Validation.isValidUsername(getUserNameField())){
            String message = Validation.getUsernameValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "User Name Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isValidPassword(getPasswordField())){
            String message = Validation.getPasswordValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "Password Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
