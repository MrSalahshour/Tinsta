package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.CreateNewFolderListener;
import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.CreateNewFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToCommunicationSecEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderAlreadyExist;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserFolderView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToCommunicationMenuButton =new JButton("Back To Communication Menu");
    private final JLabel userFolderLabel = new JLabel("User Folder: ");
    private final JTextField userFolderTextField = new JTextField();
    private final JButton createNewFolderButton = new JButton("Create");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToCommunicationSecListener goToCommunicationSecListener;
    private CreateNewFolderListener createNewFolderListener;


    public CreateUserFolderView(JPanel source) {
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
        this.add(createNewFolderButton);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToCommunicationMenuButton.setFont(backToCommunicationMenuButton.getFont().deriveFont(15.0f));
        createNewFolderButton.setFont(createNewFolderButton.getFont().deriveFont(15.0f));
        userFolderLabel.setFont(userFolderLabel.getFont().deriveFont(20.0f));
        userFolderTextField.setFont(userFolderTextField.getFont().deriveFont(25.0f));



        logoutButton.setBackground(colorConfig.getColor3());
        backToCommunicationMenuButton.setBackground(colorConfig.getColor3());
        createNewFolderButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToCommunicationMenuButton.setForeground(colorConfig.getColor0());
        createNewFolderButton.setForeground(colorConfig.getColor0());
        userFolderLabel.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToCommunicationMenuButton.setFocusable(false);
        createNewFolderButton.setFocusable(false);

        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToCommunicationMenuButton.setBorder(BorderFactory.createEmptyBorder());
        userFolderTextField.setBorder(BorderFactory.createEmptyBorder());
        userFolderLabel.setBorder(BorderFactory.createEmptyBorder());
        createNewFolderButton.setBorder(BorderFactory.createEmptyBorder());

        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();

        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        backToCommunicationMenuButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        createNewFolderButton.setBounds(300,320,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        userFolderLabel.setBounds(200,220,labelConfig.getL150x50()
                .getWidth(),labelConfig.getL150x50().getHeight());
        userFolderTextField.setBounds(350,220,textFieldConfig.gettF250x50()
                .getWidth(),textFieldConfig.gettF250x50().getHeight());


        logoutButton.addActionListener(this);
        backToCommunicationMenuButton.addActionListener(this);
        createNewFolderButton.addActionListener(this);

    }

    public String getUserFolderTextField() {
        return userFolderTextField.getText();
    }
        public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToCommunicationSecListener(GoToCommunicationSecListener goToCommunicationSecListener) {
        this.goToCommunicationSecListener = goToCommunicationSecListener;
    }

    public void setCreateNewFolderListener(CreateNewFolderListener createNewFolderListener) {
        this.createNewFolderListener = createNewFolderListener;
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
        if (createNewFolderButton == e.getSource()){
            CreateNewFolderEvent createNewFolderEvent = new CreateNewFolderEvent(source,getUserFolderTextField());
            try {
                createNewFolderListener.eventOccurred(createNewFolderEvent);
            } catch (UserFolderAlreadyExist userFolderAlreadyExist) {
                String message = userFolderAlreadyExist.getMessage();
                JOptionPane.showMessageDialog(source, message,
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
