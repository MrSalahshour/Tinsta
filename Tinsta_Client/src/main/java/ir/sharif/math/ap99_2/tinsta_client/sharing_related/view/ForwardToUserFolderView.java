package ir.sharif.math.ap99_2.tinsta_client.sharing_related.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.ForwardToUserFolderListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.ForwardToUserFolderEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForwardToUserFolderView extends JPanel implements ActionListener {

    private final JPanel source;
    private final JTextField userFolderTextField = new JTextField();
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToButton =new JButton("Back");
    private final JButton forwardToUserFolderButton =new JButton("Forward");
    private final JLabel userFolderLabel = new JLabel("User Folder");
    private final ColorConfig colorConfig = new ColorConfig();
    private final int messageId;
    private BackToLoginListener backToLoginListener;
    private BackToListener backToListener;
    private ForwardToUserFolderListener forwardToUserFolderListener;


    public ForwardToUserFolderView(JPanel source, int messageId) {
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
        this.add(userFolderTextField);
        this.add(forwardToUserFolderButton);
        this.add(backToButton);
        this.add(logoutButton);
        this.add(userFolderLabel);

    }

    private void configElements(){
        forwardToUserFolderButton.setFont(forwardToUserFolderButton.getFont().deriveFont(15.0f));
        userFolderLabel.setFont(userFolderLabel.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToButton.setFont(backToButton.getFont().deriveFont(15.0f));
        userFolderTextField.setFont(backToButton.getFont().deriveFont(15.0f));

        logoutButton.setBackground(colorConfig.getColor3());
        backToButton.setBackground(colorConfig.getColor3());
        forwardToUserFolderButton.setBackground(colorConfig.getColor3());
        userFolderLabel.setBackground(colorConfig.getColor3());

        logoutButton.setForeground(colorConfig.getColor0());
        backToButton.setForeground(colorConfig.getColor0());
        forwardToUserFolderButton.setForeground(colorConfig.getColor0());
        userFolderLabel.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToButton.setFocusable(false);
        forwardToUserFolderButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();


        userFolderLabel.setBounds(235,240,labelConfig.getL100x20().getWidth()
                ,labelConfig.getL100x20().getHeight());

        userFolderTextField.setBounds(345,240,textFieldConfig.gettF200x20()
                .getWidth(),textFieldConfig.gettF200x20().getHeight());
        forwardToUserFolderButton.setBounds(300,300,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        backToButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());


        logoutButton.addActionListener(this);
        backToButton.addActionListener(this);
        forwardToUserFolderButton.addActionListener(this);


    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setForwardToUserFolderListener(ForwardToUserFolderListener forwardToUserFolderListener) {
        this.forwardToUserFolderListener = forwardToUserFolderListener;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getUserFolderTextField() {
        return userFolderTextField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToButton == e.getSource()){
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }
        if (forwardToUserFolderButton == e.getSource()){
            ForwardToUserFolderEvent forwardToUserFolderEvent = new ForwardToUserFolderEvent(this
                    ,getUserFolderTextField());
            try {
                forwardToUserFolderListener.eventOccurred(forwardToUserFolderEvent);
            } catch (UserFolderNotExist userFolderNotExist) {
                String message = userFolderNotExist.getMessage();
                JOptionPane.showMessageDialog(source, message,
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }

    }
}
