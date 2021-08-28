package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.BackToChatRoomListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.GoToGroupListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.LeftTheGroupListener;
import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.StartGroupListener;
import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.BackToChatRoomEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.GoToGroupEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.LeftTheGroupEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.StartGroupEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupAlreadyExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupNotExist;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupChatsMenuView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton goToExistingGroupButton = new JButton("Go To Existing Group");
    private final JButton startNewGroupButton = new JButton("Start New Group");
    private final JButton leftTheGroupButton = new JButton("Left The Group");
    private final JLabel groupNameLabel = new JLabel("Group Name: ",SwingConstants.RIGHT);
    private final JTextField groupNameTextField = new JTextField(15);
    private final JButton backToChatRoomButton = new JButton("Back To ChatRoom");
    private final JButton logoutButton = new JButton("Logout");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private BackToChatRoomListener backToChatRoomListener;
    private StartGroupListener startGroupListener;
    private GoToGroupListener goToGroupListener;
    private LeftTheGroupListener leftTheGroupListener;


    public GroupChatsMenuView(JPanel source) {
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
        this.add(startNewGroupButton);
        this.add(groupNameLabel);
        this.add(groupNameTextField);
        this.add(goToExistingGroupButton);
        this.add(backToChatRoomButton);
        this.add(logoutButton);
        this.add(leftTheGroupButton);
    }

    private void configElements(){
        backToChatRoomButton.setFont(backToChatRoomButton.getFont().deriveFont(15.0f));
        goToExistingGroupButton.setFont(goToExistingGroupButton.getFont().deriveFont(15.0f));
        leftTheGroupButton.setFont(leftTheGroupButton.getFont().deriveFont(15.0f));
        startNewGroupButton.setFont(startNewGroupButton.getFont().deriveFont(15.0f));
        groupNameLabel.setFont(groupNameLabel.getFont().deriveFont(20.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        groupNameTextField.setFont(groupNameTextField.getFont().deriveFont(25.0f));



        goToExistingGroupButton.setBackground(colorConfig.getColor3());
        startNewGroupButton.setBackground(colorConfig.getColor3());
        leftTheGroupButton.setBackground(colorConfig.getColor3());
        groupNameLabel.setBackground(colorConfig.getColor3());
        backToChatRoomButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());

        goToExistingGroupButton.setForeground(colorConfig.getColor0());
        leftTheGroupButton.setForeground(colorConfig.getColor0());
        startNewGroupButton.setForeground(colorConfig.getColor0());
        groupNameLabel.setForeground(colorConfig.getColor0());
        backToChatRoomButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());

        backToChatRoomButton.setFocusable(false);
        goToExistingGroupButton.setFocusable(false);
        logoutButton.setFocusable(false);
        startNewGroupButton.setFocusable(false);
        leftTheGroupButton.setFocusable(false);

        backToChatRoomButton.addActionListener(this);
        goToExistingGroupButton.addActionListener(this);
        logoutButton.addActionListener(this);
        startNewGroupButton.addActionListener(this);
        leftTheGroupButton.addActionListener(this);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();

        backToChatRoomButton.setBounds(200,10,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight());
        groupNameLabel.setBounds(150,220,labelConfig.getL150x50().getWidth(),labelConfig.getL150x50().getHeight());
        groupNameTextField.setBounds(360,220,textFieldConfig.gettF250x50()
                .getWidth(),textFieldConfig.gettF250x50().getHeight());
        goToExistingGroupButton.setBounds(50,320,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        startNewGroupButton.setBounds(300,320,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
        leftTheGroupButton.setBounds(550,320,buttonConfig.getB200x50()
                .getWidth(),buttonConfig.getB200x50().getHeight());
    }

    public String getGroupNameTextField() {
        return groupNameTextField.getText();
    }

    public JPanel getSource() {
        return source;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToChatRoomListener(BackToChatRoomListener backToChatRoomListener) {
        this.backToChatRoomListener = backToChatRoomListener;
    }

    public void setStartGroupListener(StartGroupListener startGroupListener) {
        this.startGroupListener = startGroupListener;
    }

    public void setGoToGroupListener(GoToGroupListener goToGroupListener) {
        this.goToGroupListener = goToGroupListener;
    }

    public void setLeftTheGroupListener(LeftTheGroupListener leftTheGroupListener) {
        this.leftTheGroupListener = leftTheGroupListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToChatRoomButton == e.getSource()){
            BackToChatRoomEvent backToChatRoomEvent = new BackToChatRoomEvent(source);
            backToChatRoomListener.eventOccurred(backToChatRoomEvent);
        }
        if (startNewGroupButton == e.getSource()){
            StartGroupEvent startGroupEvent = new StartGroupEvent(this,getGroupNameTextField());
            try {
                startGroupListener.eventOccurred(startGroupEvent);
            } catch (GroupAlreadyExist groupAlreadyExist) {
                String message = groupAlreadyExist.getMessage();
                JOptionPane.showMessageDialog(source, message,
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (goToExistingGroupButton == e.getSource()){
            GoToGroupEvent goToGroupEvent = new GoToGroupEvent(this,getGroupNameTextField());
            try {
                goToGroupListener.eventOccurred(goToGroupEvent);
            } catch (GroupNotExist groupNotExist) {
                String message = groupNotExist.getMessage();
                JOptionPane.showMessageDialog(source, message,
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (leftTheGroupButton == e.getSource()){
            LeftTheGroupEvent leftTheGroupEvent = new LeftTheGroupEvent(this,getGroupNameTextField());
            try {
                leftTheGroupListener.eventOccurred(leftTheGroupEvent);
            }
            catch (GroupNotExist groupNotExist) {
                String message = groupNotExist.getMessage();
                JOptionPane.showMessageDialog(source, message,
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
