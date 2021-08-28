package ir.sharif.math.ap99_2.tinsta_client.sharing_related.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.ForwardToMultiUsersListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToMultiUsersEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ForwardToMultiUsersView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JTextField userNameTextField = new JTextField();
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToButton =new JButton("Back");
    private final JButton forwardButton =new JButton("Forward");
    private final JButton addButton =new JButton("Add");
    private final JLabel userNameLabel = new JLabel("User Name");
    private final LinkedList<String> userNames = new LinkedList<>();
    private final ColorConfig colorConfig = new ColorConfig();
    private final int messageId;
    private BackToLoginListener backToLoginListener;
    private BackToListener backToListener;
    private ForwardToMultiUsersListener forwardToMultiUsersListener;


    public ForwardToMultiUsersView(JPanel source, int messageId) {
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
        this.add(addButton);

    }

    private void configElements(){
        forwardButton.setFont(forwardButton.getFont().deriveFont(15.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToButton.setFont(backToButton.getFont().deriveFont(15.0f));
        userNameTextField.setFont(backToButton.getFont().deriveFont(15.0f));
        addButton.setFont(addButton.getFont().deriveFont(15.0f));

        logoutButton.setBackground(colorConfig.getColor3());
        backToButton.setBackground(colorConfig.getColor3());
        forwardButton.setBackground(colorConfig.getColor3());
        userNameLabel.setBackground(colorConfig.getColor3());
        addButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToButton.setForeground(colorConfig.getColor0());
        forwardButton.setForeground(colorConfig.getColor0());
        userNameLabel.setForeground(colorConfig.getColor0());
        addButton.setForeground(colorConfig.getColor0());



        logoutButton.setFocusable(false);
        backToButton.setFocusable(false);
        forwardButton.setFocusable(false);
        addButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();



        userNameLabel.setBounds(235,240,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());
        userNameTextField.setBounds(345,240,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        addButton.setBounds(150,300,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        forwardButton.setBounds(450,300,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        backToButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());



        logoutButton.addActionListener(this);
        backToButton.addActionListener(this);
        forwardButton.addActionListener(this);
        addButton.addActionListener(this);


    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    public void setUserNameTextField(String userNameTextField) {
        this.userNameTextField.setText(userNameTextField);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setForwardToMultiUsersListener(ForwardToMultiUsersListener forwardToMultiUsersListener) {
        this.forwardToMultiUsersListener = forwardToMultiUsersListener;
    }

    public int getMessageId() {
        return messageId;
    }

    public LinkedList<String> getUserNames() {
        return userNames;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (addButton == e.getSource()){
            if (checkValidation()){
                userNames.add(getUserNameTextField());
                setUserNameTextField("");
                this.revalidate();
                this.repaint();
            }
        }
        if (forwardButton == e.getSource()){
            ForwardToMultiUsersEvent forwardToMultiUsersEvent = new ForwardToMultiUsersEvent(this,getUserNames());
            forwardToMultiUsersListener.eventOccurred(forwardToMultiUsersEvent);

        }
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToButton == e.getSource()){
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }

    }
    public boolean checkValidation(){
        if (!Validation.isValidUsername(getUserNameTextField())){
            String message = Validation.getUsernameValidationHelp();
            JOptionPane.showMessageDialog(this, message,
                    "User Name Not Valid!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!checkList()){
            String message = "User Already Added!";
            JOptionPane.showMessageDialog(this, message,
                    "ERROR!",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    private boolean checkList(){
        for (String userName : userNames) {
            if (userName.equals(getUserNameTextField()))
                return false;
        }
        return true;
    }
}
