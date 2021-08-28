package ir.sharif.math.ap99_2.tinsta_client.explorer.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.SearchUserListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.SearchUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotInYourLists;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchUserView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JLabel userNameLabel = new JLabel("User Name: ",SwingConstants.RIGHT);
    private final JTextField userNameTextField = new JTextField(15);
    private final JButton searchButton=new JButton("Search");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backTo =new JButton("Back");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private BackToListener backToListener;
    private SearchUserListener searchUserListener;

    public SearchUserView(JPanel source) {
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
        this.add(backTo);
        this.add(userNameLabel);
        this.add(userNameTextField);
        this.add(searchButton);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        searchButton.setFont(searchButton.getFont().deriveFont(15.0f));
        backTo.setFont(backTo.getFont().deriveFont(15.0f));
        userNameLabel.setFont(userNameLabel.getFont().deriveFont(20.0f));
        userNameTextField.setFont(userNameTextField.getFont().deriveFont(25.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backTo.setBackground(colorConfig.getColor3());
        userNameLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backTo.setForeground(colorConfig.getColor0());
        userNameLabel.setForeground(colorConfig.getColor0());
        searchButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backTo.setFocusable(false);
        searchButton.setFocusable(false);


        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backTo.setBorder(BorderFactory.createEmptyBorder());
        userNameLabel.setBorder(BorderFactory.createEmptyBorder());
        userNameTextField.setBorder(BorderFactory.createEmptyBorder());
        searchButton.setBorder(BorderFactory.createEmptyBorder());

        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();

        userNameLabel.setBounds(150,220,labelConfig.getL150x50().getWidth(),labelConfig.getL150x50().getHeight());
        userNameTextField.setBounds(350,220,textFieldConfig.gettF250x50()
                .getWidth(),textFieldConfig.gettF250x50().getHeight());
        searchButton.setBounds(300,320,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        backTo.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());

        logoutButton.addActionListener(this);
        backTo.addActionListener(this);
        searchButton.addActionListener(this);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setSearchUserListener(SearchUserListener searchUserListener) {
        this.searchUserListener = searchUserListener;
    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backTo == e.getSource()){
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }
        if (searchButton == e.getSource()){
            if (checkValidation()){
                SearchUserEvent searchUserEvent = new SearchUserEvent(source,getUserNameTextField());
                try {
                    searchUserListener.eventOccurred(searchUserEvent);
                } catch (UserNotExist | UserNotInYourLists exception) {
                    String message = exception.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);

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
