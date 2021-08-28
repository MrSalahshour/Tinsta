package ir.sharif.math.ap99_2.tinsta_client.profile.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToChangeProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeProfPrivacyView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton showBirthdayButton =new JButton("Show Birthday");
    private final JButton notShowBirthdayButton =new JButton("Don't Show Birthday");
    private final JButton showEmailButton =new JButton("Show Email");
    private final JButton notShowEmailButton =new JButton("Don't Show Email");
    private final JButton showPhoneNumberButton =new JButton("Show Phone Number");
    private final JButton notShowPhoneNumberButton =new JButton("Don't Show Phone Number");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToProfileButton =new JButton("Back To Profile Menu");
    private final ColorConfig colorConfig = new ColorConfig();
    private SetToShowBirthdayListener setToShowBirthdayListener;
    private SetToNotShowBirthdayListener setToNotShowBirthdayListener;
    private SetToShowEmailListener setToShowEmailListener;
    private SetToNotShowEmailListener setToNotShowEmailListener;
    private SetToShowPhoneNumberListener setToShowPhoneNumberListener;
    private SetToNotShowPhoneNumberListener setToNotShowPhoneNumberListener;
    private BackToLoginListener backToLoginListener;
    private GoToChangeProfileListener goToChangeProfileListener;


    public ChangeProfPrivacyView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        GridLayout gridLayout = new GridLayout(4,2);
        this.setBorder(border);
        this.setLayout(gridLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();
    }
    private void addElements(){
        this.add(showBirthdayButton);
        this.add(notShowBirthdayButton);
        this.add(showEmailButton);
        this.add(notShowEmailButton);
        this.add(showPhoneNumberButton);
        this.add(notShowPhoneNumberButton);
        this.add(backToProfileButton);
        this.add(logoutButton);

    }

    private void configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        notShowPhoneNumberButton.setFont(notShowPhoneNumberButton.getFont().deriveFont(15.0f));
        backToProfileButton.setFont(backToProfileButton.getFont().deriveFont(15.0f));
        showBirthdayButton.setFont(showBirthdayButton.getFont().deriveFont(15.0f));
        notShowBirthdayButton.setFont(notShowBirthdayButton.getFont().deriveFont(15.0f));
        showPhoneNumberButton.setFont(showPhoneNumberButton.getFont().deriveFont(15.0f));
        showEmailButton.setFont(showEmailButton.getFont().deriveFont(15.0f));
        notShowEmailButton.setFont(notShowEmailButton.getFont().deriveFont(15.0f));



        logoutButton.setBackground(colorConfig.getColor3());
        backToProfileButton.setBackground(colorConfig.getColor3());
        showEmailButton.setBackground(colorConfig.getColor3());
        showPhoneNumberButton.setBackground(colorConfig.getColor3());
        showBirthdayButton.setBackground(colorConfig.getColor3());
        notShowEmailButton.setBackground(colorConfig.getColor3());
        notShowBirthdayButton.setBackground(colorConfig.getColor3());
        notShowPhoneNumberButton.setBackground(colorConfig.getColor3());



        logoutButton.setForeground(colorConfig.getColor0());
        backToProfileButton.setForeground(colorConfig.getColor0());
        showBirthdayButton.setForeground(colorConfig.getColor0());
        showPhoneNumberButton.setForeground(colorConfig.getColor0());
        showEmailButton.setForeground(colorConfig.getColor0());
        notShowPhoneNumberButton.setForeground(colorConfig.getColor0());
        notShowBirthdayButton.setForeground(colorConfig.getColor0());
        notShowEmailButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToProfileButton.setFocusable(false);
        showBirthdayButton.setFocusable(false);
        showPhoneNumberButton.setFocusable(false);
        showEmailButton.setFocusable(false);
        notShowPhoneNumberButton.setFocusable(false);
        notShowBirthdayButton.setFocusable(false);
        notShowEmailButton.setFocusable(false);


        logoutButton.setBorder(border);
        backToProfileButton.setBorder(border);
        showBirthdayButton.setBorder(border);
        showPhoneNumberButton.setBorder(border);
        showEmailButton.setBorder(border);
        notShowPhoneNumberButton.setBorder(border);
        notShowBirthdayButton.setBorder(border);
        notShowEmailButton.setBorder(border);


        logoutButton.addActionListener(this);
        backToProfileButton.addActionListener(this);
        showBirthdayButton.addActionListener(this);
        showPhoneNumberButton.addActionListener(this);
        showEmailButton.addActionListener(this);
        notShowPhoneNumberButton.addActionListener(this);
        notShowBirthdayButton.addActionListener(this);
        notShowEmailButton.addActionListener(this);

    }

    public void setSetToShowBirthdayListener(SetToShowBirthdayListener setToShowBirthdayListener) {
        this.setToShowBirthdayListener = setToShowBirthdayListener;
    }

    public void setSetToNotShowBirthdayListener(SetToNotShowBirthdayListener setToNotShowBirthdayListener) {
        this.setToNotShowBirthdayListener = setToNotShowBirthdayListener;
    }

    public void setSetToShowEmailListener(SetToShowEmailListener setToShowEmailListener) {
        this.setToShowEmailListener = setToShowEmailListener;
    }

    public void setSetToNotShowEmailListener(SetToNotShowEmailListener setToNotShowEmailListener) {
        this.setToNotShowEmailListener = setToNotShowEmailListener;
    }

    public void setSetToShowPhoneNumberListener(SetToShowPhoneNumberListener setToShowPhoneNumberListener) {
        this.setToShowPhoneNumberListener = setToShowPhoneNumberListener;
    }

    public void setSetToNotShowPhoneNumberListener(SetToNotShowPhoneNumberListener setToNotShowPhoneNumberListener) {
        this.setToNotShowPhoneNumberListener = setToNotShowPhoneNumberListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToChangeProfileListener(GoToChangeProfileListener goToChangeProfileListener) {
        this.goToChangeProfileListener = goToChangeProfileListener;
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
        if (showBirthdayButton == e.getSource()){
            SetToShowBirthdayEvent setToShowBirthdayEvent = new SetToShowBirthdayEvent(source);
            setToShowBirthdayListener.eventOccurred(setToShowBirthdayEvent);

        }
        if (notShowBirthdayButton == e.getSource()){
            SetToNotShowBirthdayEvent setToNotShowBirthdayEvent = new SetToNotShowBirthdayEvent(source);
            setToNotShowBirthdayListener.eventOccurred(setToNotShowBirthdayEvent);

        }
        if (showEmailButton == e.getSource()){
            SetToShowEmailEvent setToShowEmailEvent = new SetToShowEmailEvent(source);
            setToShowEmailListener.eventOccurred(setToShowEmailEvent);

        }
        if (notShowEmailButton == e.getSource()){
            SetToNotShowEmailEvent setToNotShowBirthdayEvent = new SetToNotShowEmailEvent(source);
            setToNotShowEmailListener.eventOccurred(setToNotShowBirthdayEvent);


        }
        if (showPhoneNumberButton == e.getSource()){
            SetToShowPhoneNumberEvent setToShowPhoneNumberEvent = new SetToShowPhoneNumberEvent(source);
            setToShowPhoneNumberListener.eventOccurred(setToShowPhoneNumberEvent);

        }
        if (notShowPhoneNumberButton == e.getSource()){
            SetToNotShowPhoneNumberEvent setToNotShowPhoneNumberEvent = new SetToNotShowPhoneNumberEvent(source);
            setToNotShowPhoneNumberListener.eventOccurred(setToNotShowPhoneNumberEvent);

        }

    }

}
