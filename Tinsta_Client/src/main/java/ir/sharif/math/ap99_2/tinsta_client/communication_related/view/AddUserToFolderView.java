package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.AddToUserFolderListener;
import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.AddToUserFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToCommunicationSecEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.CantAddToUserFolder;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserToFolderView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToCommunicationMenuButton =new JButton("Back To Communication Menu");
    private final JLabel userFolderLabel = new JLabel("User Folder: ");
    private final JLabel userNameLabel = new JLabel("UserName: ");
    private final JTextField userFolderTextField = new JTextField();
    private final JTextField userNameTextField = new JTextField();
    private final JButton addToUserFolderButton = new JButton("Add");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToCommunicationSecListener goToCommunicationSecListener;
    private AddToUserFolderListener addToUserFolderListener;


    public AddUserToFolderView(JPanel source) {
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
    private void addElements( ){
        this.add(backToCommunicationMenuButton);
        this.add(logoutButton);
        this.add(userFolderLabel);
        this.add(userFolderTextField);
        this.add(addToUserFolderButton);
        this.add(userNameLabel);
        this.add(userNameTextField);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToCommunicationMenuButton.setFont(backToCommunicationMenuButton.getFont().deriveFont(15.0f));
        addToUserFolderButton.setFont(addToUserFolderButton.getFont().deriveFont(15.0f));
        userFolderLabel.setFont(userFolderLabel.getFont().deriveFont(15.0f));
        userFolderTextField.setFont(userFolderTextField.getFont().deriveFont(20.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(15.0f));
        userNameTextField.setFont(userNameTextField.getFont().deriveFont(20.0f));



        logoutButton.setBackground(colorConfig.getColor3());
        backToCommunicationMenuButton.setBackground(colorConfig.getColor3());
        addToUserFolderButton.setBackground(colorConfig.getColor3());
        userNameLabel.setBackground(colorConfig.getColor3());
        userFolderLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToCommunicationMenuButton.setForeground(colorConfig.getColor0());
        addToUserFolderButton.setForeground(colorConfig.getColor0());
        userFolderLabel.setForeground(colorConfig.getColor0());
        userNameLabel.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToCommunicationMenuButton.setFocusable(false);
        addToUserFolderButton.setFocusable(false);

        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToCommunicationMenuButton.setBorder(BorderFactory.createEmptyBorder());
        userFolderTextField.setBorder(BorderFactory.createEmptyBorder());
        userFolderLabel.setBorder(BorderFactory.createEmptyBorder());
        addToUserFolderButton.setBorder(BorderFactory.createEmptyBorder());

        LabelConfig labelConfig = new LabelConfig();
        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();

        logoutButton.setBounds(200,475,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        backToCommunicationMenuButton.setBounds(200,10,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        addToUserFolderButton.setBounds(300,350,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        userNameLabel.setBounds(250,205,labelConfig.getL100x20()
                .getWidth(),labelConfig.getL100x20().getHeight());
        userFolderLabel.setBounds(250,250,labelConfig.getL100x20()
                .getWidth(),labelConfig.getL100x20().getHeight());
        userFolderTextField.setBounds(350,250,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        userNameTextField.setBounds(350,205,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());


        logoutButton.addActionListener(this);
        backToCommunicationMenuButton.addActionListener(this);
        addToUserFolderButton.addActionListener(this);

    }

    public String getUserFolderTextField() {
        return userFolderTextField.getText();
    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }
        public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToCommunicationSecListener(GoToCommunicationSecListener goToCommunicationSecListener) {
        this.goToCommunicationSecListener = goToCommunicationSecListener;
    }

    public void setAddToUserFolderListener(AddToUserFolderListener addToUserFolderListener) {
        this.addToUserFolderListener = addToUserFolderListener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToCommunicationMenuButton == e.getSource()){
            GoToCommunicationSecEvent goToCommunicationSecEvent = new GoToCommunicationSecEvent(source);
            goToCommunicationSecListener.eventOccurred(goToCommunicationSecEvent);
        }
        if (addToUserFolderButton == e.getSource()){
            if (checkValidation()){
                AddToUserFolderEvent addToUserFolderEvent = new AddToUserFolderEvent
                        (source,getUserNameTextField(),getUserFolderTextField());
                try {
                    addToUserFolderListener.eventOccurred(addToUserFolderEvent);
                } catch (UserFolderNotExist | UserNotExist | CantAddToUserFolder exception) {
                    String message = exception.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "Error",JOptionPane.ERROR_MESSAGE);
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
