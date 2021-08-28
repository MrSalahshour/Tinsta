package ir.sharif.math.ap99_2.tinsta_client.setting.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.DeleteAccountListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.DeleteAccountEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToSettingEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.WrongPassword;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton deleteAccountButton = new JButton("Delete Your Account");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToSettingButton =new JButton("Back To Setting");
    private final JLabel passwordLabel = new JLabel("Password: ",SwingUtilities.CENTER);
    private final JPasswordField passwordTextField = new JPasswordField();
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToSettingListener goToSettingListener;
    private BackToLoginListener backToLoginListener;
    private DeleteAccountListener deleteAccountListener;


    public DeleteAccountView(JPanel source) {
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

    public String getPasswordTextField() {
        return String.valueOf(passwordTextField.getPassword());
    }

    public void setGoToSettingListener(GoToSettingListener goToSettingListener) {
        this.goToSettingListener = goToSettingListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setDeleteAccountListener(DeleteAccountListener deleteAccountListener) {
        this.deleteAccountListener = deleteAccountListener;
    }

    private void addElements(){
        this.add(logoutButton);
        this.add(backToSettingButton);
        this.add(deleteAccountButton);
        this.add(passwordTextField);
        this.add(passwordLabel);

    }

    private void configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        deleteAccountButton.setFont(deleteAccountButton.getFont().deriveFont(15.0f));
        backToSettingButton.setFont(backToSettingButton.getFont().deriveFont(15.0f));
        passwordLabel.setFont(backToSettingButton.getFont().deriveFont(15.0f));
        passwordTextField.setFont(backToSettingButton.getFont().deriveFont(15.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToSettingButton.setBackground(colorConfig.getColor3());
        passwordLabel.setBackground(colorConfig.getColor3());

        logoutButton.setForeground(colorConfig.getColor0());
        backToSettingButton.setForeground(colorConfig.getColor0());
        passwordLabel.setForeground(colorConfig.getColor0());
        deleteAccountButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backToSettingButton.setFocusable(false);
        deleteAccountButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();

        logoutButton.setBounds(200,510,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        backToSettingButton.setBounds(200,0,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        deleteAccountButton.setBounds(300,300,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        passwordTextField.setBounds(390,235,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        passwordLabel.setBounds(160,235,labelConfig.getL200x20()
                .getWidth(),labelConfig.getL200x20().getHeight());


        logoutButton.setBorder(border);
        backToSettingButton.setBorder(border);
        deleteAccountButton.setBorder(border);

        logoutButton.addActionListener(this);
        backToSettingButton.addActionListener(this);
        deleteAccountButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (deleteAccountButton == e.getSource()){
            if (checkValidation()){
                try {
                    DeleteAccountEvent deleteAccountEvent = new DeleteAccountEvent(source);
                    deleteAccountEvent.setPassword(getPasswordTextField());
                    deleteAccountListener.eventOccurred(deleteAccountEvent);
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

        if (!Validation.isValidPassword(getPasswordTextField())){
            String message = Validation.getPasswordValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "Password Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
