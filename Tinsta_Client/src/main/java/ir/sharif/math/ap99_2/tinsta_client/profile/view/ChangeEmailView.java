package ir.sharif.math.ap99_2.tinsta_client.profile.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.listener.ChangeEmailListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToChangeProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeEmailEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.EmailAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeEmailView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JLabel emailLabel = new JLabel("Email: ",SwingUtilities.CENTER);
    private final JTextField emailTextField = new JTextField(15);
    private final JButton changeEmailButton =new JButton("Change Email");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToProfileButton =new JButton("Back To Profile Menu");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToChangeProfileListener goToChangeProfileListener;
    private ChangeEmailListener changeEmailListener;


    public ChangeEmailView(JPanel source) {
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
        this.add(logoutButton);
        this.add(backToProfileButton);
        this.add(emailLabel);
        this.add(emailTextField);
        this.add(changeEmailButton);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        changeEmailButton.setFont(changeEmailButton.getFont().deriveFont(15.0f));
        backToProfileButton.setFont(backToProfileButton.getFont().deriveFont(15.0f));
        emailLabel.setFont(emailLabel.getFont().deriveFont(18.0f));
        emailTextField.setFont(emailTextField.getFont().deriveFont(20.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToProfileButton.setBackground(colorConfig.getColor3());
        emailLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToProfileButton.setForeground(colorConfig.getColor0());
        emailLabel.setForeground(colorConfig.getColor0());
        changeEmailButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backToProfileButton.setFocusable(false);
        changeEmailButton.setFocusable(false);


        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToProfileButton.setBorder(BorderFactory.createEmptyBorder());
        emailLabel.setBorder(BorderFactory.createEmptyBorder());
        emailTextField.setBorder(BorderFactory.createEmptyBorder());
        changeEmailButton.setBorder(BorderFactory.createEmptyBorder());

        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();

        emailLabel.setBounds(155,220,labelConfig.getL150x50().getWidth(),labelConfig.getL150x50().getHeight());
        emailTextField.setBounds(350,220,textFieldConfig.gettF250x50()
                .getWidth(),textFieldConfig.gettF250x50().getHeight());
        changeEmailButton.setBounds(280,320,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        backToProfileButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());


        logoutButton.addActionListener(this);
        backToProfileButton.addActionListener(this);
        changeEmailButton.addActionListener(this);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToChangeProfileListener(GoToChangeProfileListener goToChangeProfileListener) {
        this.goToChangeProfileListener = goToChangeProfileListener;
    }

    public void setChangeEmailListener(ChangeEmailListener changeEmailListener) {
        this.changeEmailListener = changeEmailListener;
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToProfileButton == e.getSource()){
            GoToChangeProfileEvent goToChangeProfileEvent = new GoToChangeProfileEvent(source);
            goToChangeProfileListener.eventOccurred(goToChangeProfileEvent);
        }
        if (changeEmailButton == e.getSource()){
            if (checkValidation()){
                try{
                    ChangeEmailEvent changeEmailEvent = new ChangeEmailEvent(source,getEmailTextField());
                    changeEmailListener.eventOccurred(changeEmailEvent);
                } catch (EmailAlreadyExist emailAlreadyExist) {
                    String message = emailAlreadyExist.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public boolean checkValidation(){
        if (!Validation.isValidEmail(getEmailTextField())){
            String message = "Email is Not Valid";
            JOptionPane.showMessageDialog(source, message,
                    "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
