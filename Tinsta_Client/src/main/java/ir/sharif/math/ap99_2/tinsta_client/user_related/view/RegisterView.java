package ir.sharif.math.ap99_2.tinsta_client.user_related.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.RegisterFormListener;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterFormEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.EmailAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNameAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JTextField userNameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JTextField lastNameField = new JTextField(15);
    private final JTextField nameField = new JTextField(15);
    private final JTextField emailField = new JTextField(15);
    private final JLabel userNameLabel = new JLabel("UserName: ");
    private final JLabel passwordLabel = new JLabel("Password: ");
    private final JLabel lastNameLabel = new JLabel("LastName: ");
    private final JLabel nameLabel = new JLabel("Name: ");
    private final JLabel emailLabel = new JLabel("Email: ");
    private final JLabel welcomeLabel = new JLabel("Welcome To Tinsta",SwingConstants.CENTER);
    private final JLabel loginQuestionLabel = new JLabel("Already have an account?",SwingConstants.CENTER);
    private final JButton registerButton = new JButton("Register");
    private final JButton backToLoginButton = new JButton("Login");
    private final ColorConfig colorConfig = new ColorConfig();
    private RegisterFormListener registerFormListener;
    private BackToLoginListener backToLoginListener;


    public RegisterView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        ColorConfig colorConfig = new ColorConfig();
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor3());
        configElements();
        addElements();
    }
    private void addElements(){
        this.add(nameField);
        this.add(nameLabel);
        this.add(lastNameField);
        this.add(lastNameLabel);
        this.add(emailLabel);
        this.add(emailField);
        this.add(userNameField);
        this.add(userNameLabel);
        this.add(passwordField);
        this.add(passwordLabel);
        this.add(registerButton);
        this.add(welcomeLabel);
        this.add(loginQuestionLabel);
        this.add(backToLoginButton);

    }

    private void configElements(){
        LabelConfig labelConfig = new LabelConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        ButtonConfig buttonConfig = new ButtonConfig();
        welcomeLabel.setBounds(300,0,labelConfig.getL200x100().getWidth()
                ,labelConfig.getL200x100().getHeight());
        nameLabel.setBounds(250,200,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());
        lastNameLabel.setBounds(250,225,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());
        userNameLabel.setBounds(250,250,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());
        passwordLabel.setBounds(250,275,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());
        emailLabel.setBounds(250,300,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());
        loginQuestionLabel.setBounds(340,425,labelConfig.getL120x20().getWidth()
                ,labelConfig.getL120x20().getHeight());


        nameField.setBounds(350,200,textFieldConfig.gettF200x20().getWidth()
                ,textFieldConfig.gettF200x20().getHeight());
        lastNameField.setBounds(350,225,textFieldConfig.gettF200x20().getWidth()
                ,textFieldConfig.gettF200x20().getHeight());
        userNameField.setBounds(350,250,textFieldConfig.gettF200x20().getWidth()
                ,textFieldConfig.gettF200x20().getHeight());
        passwordField.setBounds(350,275,textFieldConfig.gettF200x20().getWidth()
                ,textFieldConfig.gettF200x20().getHeight());
        emailField.setBounds(350,300,textFieldConfig.gettF200x20().getWidth()
                ,textFieldConfig.gettF200x20().getHeight());

        registerButton.setBounds(300,350,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        backToLoginButton.setBounds(340,450,buttonConfig.getB120x25().getWidth()
                ,buttonConfig.getB120x25().getHeight());
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(20.0f));
        registerButton.setFont(registerButton.getFont().deriveFont(15.0f));
        loginQuestionLabel.setFont(loginQuestionLabel.getFont().deriveFont(9.0f));
        backToLoginButton.setFont(backToLoginButton.getFont().deriveFont(10.0f));
        registerButton.setFocusable(false);
        backToLoginButton.setFocusable(false);

        userNameLabel.setForeground(colorConfig.getColor0());
        passwordLabel.setForeground(colorConfig.getColor0());
        lastNameLabel.setForeground(colorConfig.getColor0());
        nameLabel.setForeground(colorConfig.getColor0());
        emailLabel.setForeground(colorConfig.getColor0());
        welcomeLabel.setForeground(colorConfig.getColor0());
        loginQuestionLabel.setForeground(colorConfig.getColor0());

        registerButton.setForeground(colorConfig.getColor5());
        backToLoginButton.setForeground(colorConfig.getColor5());

        registerButton.addActionListener(this);
        backToLoginButton.addActionListener(this);


    }

    public String getUserNameField() {
        return userNameField.getText();
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public String getLastNameField() {
        return lastNameField.getText();
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public void setEmailField(String emailField) {
        this.emailField.setText(emailField);
    }

    public void setRegisterFormListener(RegisterFormListener registerFormListener) {
        this.registerFormListener = registerFormListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (registerButton == e.getSource()){
            if (checkValidation()){
                RegisterFormEvent registerFormEvent = new RegisterFormEvent(source,getUserNameField(),
                        getPasswordField(),getNameField(),getLastNameField(),getEmailField());
                try {
                    registerFormListener.eventOccurred(registerFormEvent);
                }
                catch (EmailAlreadyExist | UserNameAlreadyExist exception) {
                    String message = exception.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (backToLoginButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginEvent.setConnected(false);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
    }

    public boolean checkValidation(){
        if (!Validation.isValidName(getNameField())){
            String message = Validation.getNameValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "Name Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isValidLastName(getLastNameField())){
            String message = Validation.getNameValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "Last Name Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
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

        if (!Validation.isValidEmail(getEmailField())){
            String message = "Email is Not Valid";
            JOptionPane.showMessageDialog(source, message,
                    "Password Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
