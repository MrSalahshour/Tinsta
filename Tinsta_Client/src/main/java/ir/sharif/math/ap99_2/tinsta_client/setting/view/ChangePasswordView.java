package ir.sharif.math.ap99_2.tinsta_client.setting.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.ChangePasswordListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ChangePasswordEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToSettingEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.WrongPassword;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JLabel currentPasswordLabel = new JLabel("Current Password:",SwingUtilities.CENTER);
    private final JLabel newPasswordLabel = new JLabel("New Password:",SwingUtilities.CENTER);
    private final JPasswordField currentPasswordTextField = new JPasswordField(10);
    private final JPasswordField newPasswordTextField = new JPasswordField(10);
    private final JButton changePasswordButton =new JButton("Change Password");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToSettingButton =new JButton("Back To Setting");
    private final ColorConfig colorConfig = new ColorConfig();
    private ChangePasswordListener changePasswordListener;
    private GoToSettingListener goToSettingListener;
    private BackToLoginListener backToLoginListener;


    public ChangePasswordView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();

        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();
    }

    public String getCurrentPasswordTextField() {
        return String.valueOf(currentPasswordTextField.getPassword());
    }

    public String getNewPasswordTextField() {
        return String.valueOf(newPasswordTextField.getPassword());
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setChangePasswordListener(ChangePasswordListener changePasswordListener) {
        this.changePasswordListener = changePasswordListener;
    }

    public void setGoToSettingListener(GoToSettingListener goToSettingListener) {
        this.goToSettingListener = goToSettingListener;
    }

    private void addElements(){
        this.add(logoutButton);
        this.add(backToSettingButton);
        this.add(currentPasswordLabel);
        this.add(currentPasswordTextField);
        this.add(newPasswordLabel);
        this.add(newPasswordTextField);
        this.add(changePasswordButton);
    }

    private void configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(13.0f));
        currentPasswordLabel.setFont(currentPasswordLabel.getFont().deriveFont(17.0f));
        backToSettingButton.setFont(backToSettingButton.getFont().deriveFont(13.0f));
        newPasswordLabel.setFont(newPasswordLabel.getFont().deriveFont(17.0f));
        currentPasswordTextField.setFont(currentPasswordTextField.getFont().deriveFont(15.0f));
        newPasswordTextField.setFont(newPasswordTextField.getFont().deriveFont(15.0f));
        changePasswordButton.setFont(changePasswordButton.getFont().deriveFont(15.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToSettingButton.setBackground(colorConfig.getColor3());
        newPasswordLabel.setBackground(colorConfig.getColor3());
        currentPasswordLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToSettingButton.setForeground(colorConfig.getColor0());
        newPasswordLabel.setForeground(colorConfig.getColor0());
        currentPasswordLabel.setForeground(colorConfig.getColor0());
        changePasswordButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backToSettingButton.setFocusable(false);
        changePasswordButton.setFocusable(false);


        logoutButton.setBorder(border);
        backToSettingButton.setBorder(border);


        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();
        backToSettingButton.setBounds(200,0,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        logoutButton.setBounds(200,510,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        changePasswordButton.setBounds(300,350,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        currentPasswordTextField.setBounds(400,205,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        newPasswordTextField.setBounds(400,250,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        currentPasswordLabel.setBounds(200,205,labelConfig.getL200x20()
                .getWidth(),labelConfig.getL200x20().getHeight());
        newPasswordLabel.setBounds(200,250,labelConfig.getL200x20()
                .getWidth(),labelConfig.getL200x20().getHeight());

        changePasswordButton.addActionListener(this);
        logoutButton.addActionListener(this);
        backToSettingButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (changePasswordButton == e.getSource()){
            if (checkValidation()){
                try{
                    ChangePasswordEvent changePasswordEvent = new ChangePasswordEvent
                            (source,getCurrentPasswordTextField(),getNewPasswordTextField());
                    changePasswordListener.eventOccurred(changePasswordEvent);
                } catch (WrongPassword wrongPassword) {
                    String message = wrongPassword.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToSettingButton == e.getSource()){
            GoToSettingEvent goToSettingEvent = new GoToSettingEvent(source);
            goToSettingListener.eventOccurred(goToSettingEvent);
        }

    }
    public boolean checkValidation(){
        if (!Validation.isValidPassword(getCurrentPasswordTextField())){
            String message = Validation.getPasswordValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "Password Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isValidPassword(getNewPasswordTextField())){
            String message = Validation.getPasswordValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "Password Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
