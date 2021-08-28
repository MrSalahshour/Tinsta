package ir.sharif.math.ap99_2.tinsta_client.profile.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.listener.ChangeNameListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToChangeProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeNameEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNameView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JLabel nameLabel = new JLabel("Name: ",SwingUtilities.CENTER);
    private final JTextField nameTextField = new JTextField(15);
    private final JButton changeNameButton=new JButton("Change Name");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToProfileButton =new JButton("Back To Profile Menu");
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToChangeProfileListener goToChangeProfileListener;
    private BackToLoginListener backToLoginListener;
    private ChangeNameListener changeNameListener;


    public ChangeNameView(JPanel source) {
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
        this.add(nameLabel);
        this.add(nameTextField);
        this.add(changeNameButton);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        changeNameButton.setFont(changeNameButton.getFont().deriveFont(15.0f));
        backToProfileButton.setFont(backToProfileButton.getFont().deriveFont(15.0f));
        nameLabel.setFont(nameLabel.getFont().deriveFont(20.0f));
        nameTextField.setFont(nameTextField.getFont().deriveFont(25.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToProfileButton.setBackground(colorConfig.getColor3());
        nameLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToProfileButton.setForeground(colorConfig.getColor0());
        nameLabel.setForeground(colorConfig.getColor0());
        changeNameButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backToProfileButton.setFocusable(false);
        changeNameButton.setFocusable(false);


        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToProfileButton.setBorder(BorderFactory.createEmptyBorder());
        nameLabel.setBorder(BorderFactory.createEmptyBorder());
        nameTextField.setBorder(BorderFactory.createEmptyBorder());
        changeNameButton.setBorder(BorderFactory.createEmptyBorder());

        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();

        nameLabel.setBounds(155,220,labelConfig.getL150x50().getWidth(),labelConfig.getL150x50().getHeight());
        nameTextField.setBounds(350,220,textFieldConfig.gettF250x50()
                .getWidth(),textFieldConfig.gettF250x50().getHeight());
        changeNameButton.setBounds(280,320,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        backToProfileButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());

        logoutButton.addActionListener(this);
        backToProfileButton.addActionListener(this);
        changeNameButton.addActionListener(this);
    }

    public void setGoToChangeProfileListener(GoToChangeProfileListener goToChangeProfileListener) {
        this.goToChangeProfileListener = goToChangeProfileListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setChangeNameListener(ChangeNameListener changeNameListener) {
        this.changeNameListener = changeNameListener;
    }

    public String getNameTextField() {
        return nameTextField.getText();
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
        if (changeNameButton == e.getSource()){
            if (checkValidation()){
                ChangeNameEvent changeNameEvent = new ChangeNameEvent(source,getNameTextField());
                changeNameListener.eventOccurred(changeNameEvent);
            }
        }
    }
    public boolean checkValidation(){
        if (!Validation.isValidName(getNameTextField())){
            String message = Validation.getNameValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "Name Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
