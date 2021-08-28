package ir.sharif.math.ap99_2.tinsta_client.profile.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.listener.ChangePhoneNumberListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToChangeProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangePhoneNumberEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.PhoneNumberAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePhoneNumberView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JLabel phoneNumberLabel = new JLabel("Phone Number: ",SwingUtilities.RIGHT);
    private final JTextField phoneNumberTextField = new JTextField(15);
    private final JButton changePhoneNumberButton =new JButton("Change Phone Number");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToProfileButton =new JButton("Back To Profile Menu");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToChangeProfileListener goToChangeProfileListener;
    private ChangePhoneNumberListener changePhoneNumberListener;


    public ChangePhoneNumberView(JPanel source) {
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
        this.add(phoneNumberLabel);
        this.add(phoneNumberTextField);
        this.add(changePhoneNumberButton);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        changePhoneNumberButton.setFont(changePhoneNumberButton.getFont().deriveFont(15.0f));
        backToProfileButton.setFont(backToProfileButton.getFont().deriveFont(15.0f));
        phoneNumberLabel.setFont(phoneNumberLabel.getFont().deriveFont(18.0f));
        phoneNumberTextField.setFont(phoneNumberTextField.getFont().deriveFont(25.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToProfileButton.setBackground(colorConfig.getColor3());
        phoneNumberLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToProfileButton.setForeground(colorConfig.getColor0());
        phoneNumberLabel.setForeground(colorConfig.getColor0());
        changePhoneNumberButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backToProfileButton.setFocusable(false);
        changePhoneNumberButton.setFocusable(false);


        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToProfileButton.setBorder(BorderFactory.createEmptyBorder());
        phoneNumberLabel.setBorder(BorderFactory.createEmptyBorder());
        phoneNumberTextField.setBorder(BorderFactory.createEmptyBorder());
        changePhoneNumberButton.setBorder(BorderFactory.createEmptyBorder());

        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();

        phoneNumberLabel.setBounds(190,220,labelConfig.getL150x50().getWidth(),labelConfig.getL150x50().getHeight());
        phoneNumberTextField.setBounds(350,220,textFieldConfig.gettF250x50()
                .getWidth(),textFieldConfig.gettF250x50().getHeight());
        changePhoneNumberButton.setBounds(280,320,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        backToProfileButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());



        logoutButton.addActionListener(this);
        backToProfileButton.addActionListener(this);
        changePhoneNumberButton.addActionListener(this);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToChangeProfileListener(GoToChangeProfileListener goToChangeProfileListener) {
        this.goToChangeProfileListener = goToChangeProfileListener;
    }

    public void setChangePhoneNumberListener(ChangePhoneNumberListener changePhoneNumberListener) {
        this.changePhoneNumberListener = changePhoneNumberListener;
    }

    public String getPhoneNumberTextField() {
        return phoneNumberTextField.getText();
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
        if (changePhoneNumberButton == e.getSource()){
            if (checkValidation()){
                try {
                    ChangePhoneNumberEvent changePhoneNumberEvent = new ChangePhoneNumberEvent
                            (source,getPhoneNumberTextField());
                    changePhoneNumberListener.eventOccurred(changePhoneNumberEvent);

                } catch (PhoneNumberAlreadyExist phoneNumberAlreadyExist) {
                    String message = phoneNumberAlreadyExist.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    public boolean checkValidation(){
        if (!Validation.isValidPhoneNumber(getPhoneNumberTextField())){
            String message = "PhoneNumber is Not Valid";
            JOptionPane.showMessageDialog(source, message,
                    "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
