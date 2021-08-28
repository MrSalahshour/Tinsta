package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;


import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.TextFieldConfig;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.SearchUserListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.view.MessageView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.SearchUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.GroupNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotExist;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserNotInYourLists;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class ChatView extends JPanel implements ActionListener {
    private final JPanel source;
    private String groupName;
    private Integer userFromId;
    private final JTextField messageContentTextField = new JTextField();
    private final JButton logoutButton = new JButton("Logout");
    private final JButton backTo = new JButton("Back");
    private final JButton nextMessageButton = new JButton("Next Message");
    private final JButton previousMessageButton = new JButton("Previous Message");
    private final JButton sendMessageButton = new JButton("Send Message");
    private final JButton selectImageButton = new JButton("Select Image");
    private final JButton addToSavedMessageButton = new JButton("Add To Saved Msg");
    private final JButton editMessageButton = new JButton("Edit Message");
    private final JButton deleteMessageButton = new JButton("Delete Message");
    private final JButton timerMessageButton = new JButton("Timer Message");
    private final JButton onlineModeButton = new JButton("Online Mode");
    private final JButton offlineModeButton = new JButton("Offline Mode");
    private final ColorConfig colorConfig = new ColorConfig();
    private MessageView messageView;
    private String imagePath;
    private SelectImageForMessageListener selectImageForMessageListener;
    private BackToLoginListener backToLoginListener;
    private BackToListener backToListener;
    private NextMessageListener nextMessageListener;
    private PreviousMessageListener previousMessageListener;
    private SendMessageListener sendMessageListener;
    private AddToSavedMessageFromChatListener addToSavedMessageFromChatListener;
    private EditMessageListener editMessageListener;
    private DeleteMessageListener deleteMessageListener;
    private SendTimerMessageListener sendTimerMessageListener;
    private boolean online;
    private JHyperlink hyperlink;


    public ChatView(JPanel source) {
        this.source = source;
        this.online = true;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(), 10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements();

    }

    public void configElements() {
        LinkedList<JButton> jButtons = new LinkedList<>();
        jButtons.add(nextMessageButton);
        jButtons.add(previousMessageButton);
        jButtons.add(sendMessageButton);
        jButtons.add(selectImageButton);
        jButtons.add(logoutButton);
        jButtons.add(backTo);
        jButtons.add(addToSavedMessageButton);
        jButtons.add(editMessageButton);
        jButtons.add(deleteMessageButton);
        jButtons.add(timerMessageButton);
        jButtons.add(onlineModeButton);
        jButtons.add(offlineModeButton);
        for (JButton button : jButtons) {
            button.setFont(button.getFont().deriveFont(9.0f));
            button.setBackground(colorConfig.getColor3());
            button.setForeground(colorConfig.getColor0());
            button.setFocusable(false);
            button.addActionListener(this);
        }
        messageContentTextField.setFont(messageContentTextField.getFont().deriveFont(9.0f));
        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        messageContentTextField.setBounds(45, 489, textFieldConfig.gettF200x25()
                .getWidth(), textFieldConfig.gettF200x25().getHeight());
        sendMessageButton.setBounds(290, 489, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        selectImageButton.setBounds(460, 489, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        logoutButton.setBounds(655, 10, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        offlineModeButton.setBounds(655, 45, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        onlineModeButton.setBounds(655, 80, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        nextMessageButton.setBounds(60, 382, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        timerMessageButton.setBounds(60, 423, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        previousMessageButton.setBounds(245, 382, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        addToSavedMessageButton.setBounds(430, 382, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        backTo.setBounds(10, 10, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        editMessageButton.setBounds(630, 489, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        deleteMessageButton.setBounds(615, 382, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        for (JButton button : jButtons) {
            this.add(button);
        }
        this.add(messageContentTextField);

    }

    public void configMessageView() {
        PanelConfig panelConfig = new PanelConfig();
        messageView.setBounds(200, 50, panelConfig.getTweetPanel().getWidth()
                , panelConfig.getTweetPanel().getHeight());
        this.add(messageView);
    }

    public String getMessageContentTextField() {
        return messageContentTextField.getText();
    }

    public void setMessageView(MessageView messageView) {
        this.messageView = messageView;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public MessageView getMessageView() {
        return messageView;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserFromId() {
        return userFromId;
    }

    public void setUserFromId(Integer userFromId) {
        this.userFromId = userFromId;
    }

    public void setSelectImageForMessageListener(SelectImageForMessageListener selectImageForMessageListener) {
        this.selectImageForMessageListener = selectImageForMessageListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setNextMessageListener(NextMessageListener nextMessageListener) {
        this.nextMessageListener = nextMessageListener;
    }

    public void setPreviousMessageListener(PreviousMessageListener previousMessageListener) {
        this.previousMessageListener = previousMessageListener;
    }

    public void setSendMessageListener(SendMessageListener sendMessageListener) {
        this.sendMessageListener = sendMessageListener;
    }

    public void setAddToSavedMessageFromChatListener(AddToSavedMessageFromChatListener addToSavedMessageFromChatListener) {
        this.addToSavedMessageFromChatListener = addToSavedMessageFromChatListener;
    }

    public void setEditMessageListener(EditMessageListener editMessageListener) {
        this.editMessageListener = editMessageListener;
    }

    public void setDeleteMessageListener(DeleteMessageListener deleteMessageListener) {
        this.deleteMessageListener = deleteMessageListener;
    }

    public void setSendTimerMessageListener(SendTimerMessageListener sendTimerMessageListener) {
        this.sendTimerMessageListener = sendTimerMessageListener;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectImageButton == e.getSource()) {
            SelectImageForMessageEvent selectImageForMessageEvent = new SelectImageForMessageEvent(this);
            selectImageForMessageListener.eventOccurred(selectImageForMessageEvent);
        }
        if (logoutButton == e.getSource()) {
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backTo == e.getSource()) {
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }
        if (nextMessageButton == e.getSource()) {
            NextMessageEvent nextMessageEvent = new NextMessageEvent(this);
            nextMessageListener.eventOccurred(nextMessageEvent);
        }
        if (previousMessageButton == e.getSource()) {
            PreviousMessageEvent previousMessageEvent = new PreviousMessageEvent(this);
            previousMessageListener.eventOccurred(previousMessageEvent);
        }
        if (sendMessageButton == e.getSource()) {
            if (checkUserHyperLink())
                handleHyperLink();
            else if (checkGroupHyperLink()){
                handleHyperLink();
                hyperlink.setDrawUnderline(true);
            }
            else {
                if(hyperlink!=null){
                    this.remove(hyperlink);
                    this.revalidate();
                    this.repaint();
                }
                SendMessageEvent sendMessageEvent = new SendMessageEvent
                        (this, getImagePath(), getMessageContentTextField());
                sendMessageListener.eventOccurred(sendMessageEvent);
            }
        }
        if (addToSavedMessageButton == e.getSource()) {
            if (messageView == null) {
                String msg = "There is no message!";
                JOptionPane.showMessageDialog(this, msg,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            AddToSavedMessageFromChatEvent addToSavedMessageFromChatEvent = new
                    AddToSavedMessageFromChatEvent(this);
            addToSavedMessageFromChatListener.eventOccurred(addToSavedMessageFromChatEvent);
        }
        if (editMessageButton == e.getSource()) {
            if (messageView == null) {
                String msg = "There is no message!";
                JOptionPane.showMessageDialog(this, msg,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            EditMessageEvent editMessageEvent = new EditMessageEvent(this);
            editMessageListener.eventOccurred(editMessageEvent);

        }
        if (deleteMessageButton == e.getSource()) {
            if (messageView == null) {
                String msg = "There is no message!";
                JOptionPane.showMessageDialog(this, msg,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DeleteMessageEvent deleteMessageEvent = new DeleteMessageEvent(this);
            deleteMessageListener.eventOccurred(deleteMessageEvent);
        }
        if (timerMessageButton == e.getSource()) {
            if (groupName != null) {
                String result = JOptionPane.showInputDialog(this, "Hour Minute Second: ");
                if (result != null) {
                    handleTimeMessage(result);
                }
            }
        }
        if (onlineModeButton == e.getSource())
            setOnline(true);
        if (offlineModeButton == e.getSource())
            setOnline(false);
        if (hyperlink == e.getSource()) {
            String name = getMessageContentTextField().substring(1);
            if (hyperlink.getDrawUnderline()){
                try {
                    GoToGroupEvent goToGroupEvent = new GoToGroupEvent(source,name);
                    GoToGroupListener goToGroupListener = new GoToGroupListener();
                    goToGroupListener.eventOccurred(goToGroupEvent);
                } catch (GroupNotExist groupNotExist) {
                    String message = groupNotExist.getMessage();
                    JOptionPane.showMessageDialog(source, message,
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                if (checkValidation(name)) {
                    try {
                        SearchUserEvent searchUserEvent = new SearchUserEvent(source, name);
                        SearchUserListener searchUserListener = new SearchUserListener();
                        searchUserListener.eventOccurred(searchUserEvent);
                    } catch (UserNotExist | UserNotInYourLists exception) {
                        String message = exception.getMessage();
                        JOptionPane.showMessageDialog(source, message,
                                "ERROR", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        }

    }

    private boolean checkUserHyperLink() {
        String text = getMessageContentTextField();
        return text != null && text.charAt(0) == '@';
    }

    private boolean checkGroupHyperLink(){
        String text = getMessageContentTextField();
        return text != null && text.charAt(0) == '#';
    }

    private void handleTimeMessage(String result) {
        String[] time = result.split(" ");
        try {
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int second = Integer.parseInt(time[2]);
            int hourNow = LocalDateTime.now().getHour();
            int minuteNow = LocalDateTime.now().getMinute();
            int secondNow = LocalDateTime.now().getSecond();
            int firstInstance = hour * 3600 + minute * 60 + second;
            int secondInstance = hourNow * 3600 + minuteNow * 60 + secondNow;
            long milliBetween = (firstInstance - secondInstance) * 1000;
            if (milliBetween < 0)
                throw new NumberFormatException();
            SendMessageEvent sendMessageEvent = new SendMessageEvent
                    (this, getImagePath(), getMessageContentTextField(), milliBetween);
            sendTimerMessageListener.eventOccurred(sendMessageEvent);
        } catch (NumberFormatException numberFormatException) {
            String msg = "Invalid Time!";
            JOptionPane.showMessageDialog(this, msg,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleHyperLink() {
        hyperlink = new JHyperlink(getMessageContentTextField());
        hyperlink.setDrawUnderline(false);
        hyperlink.setForeground(colorConfig.getColor0());
        hyperlink.addActionListener(this);
        hyperlink.setFont(hyperlink.getFont().deriveFont(9.0f));
        hyperlink.setBounds(45, 524, 200, 25);
        this.add(hyperlink);
        this.revalidate();
        this.repaint();
    }


    public boolean checkValidation(String username) {
        if (!Validation.isValidUsername(username)) {
            String message = Validation.getUsernameValidationHelp();
            JOptionPane.showMessageDialog(source, message,
                    "User Name Not Valid!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
