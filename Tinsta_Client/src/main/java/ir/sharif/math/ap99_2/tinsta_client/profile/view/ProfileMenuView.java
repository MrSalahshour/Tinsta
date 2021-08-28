package ir.sharif.math.ap99_2.tinsta_client.profile.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.profile.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToHomePageEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileMenuView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton changeUserNameButton=new JButton("Change UserName");
    private final JButton changeNameButton=new JButton("Change Name");
    private final JButton changeLastNameButton=new JButton("Change LastName");
    private final JButton changePhoneNumberButton=new JButton("Change Phone Number");
    private final JButton changeEmailButton=new JButton("Change Email");
    private final JButton changeBiographyButton=new JButton("Change Biography");
    private final JButton changeProfPrivacyButton=new JButton("Change Profile Privacy");
    private final JButton changeBirthdayButton=new JButton("Change Birthday");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToHomePageButton=new JButton("Back To HomePage");
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToChangeUserNameListener goToChangeUserNameListener;
    private GoToChangeNameListener goToChangeNameListener;
    private GoToChangeLastNameListener goToChangeLastNameListener;
    private GoToChangePhoneNumberListener goToChangePhoneNumberListener;
    private GoToChangeEmailListener goToChangeEmailListener;
    private GoToChangeBioListener goToChangeBioListener;
    private GoToChangeProfilePrivacyListener goToChangeProfilePrivacyListener;
    private GoToChangeBirthdayListener goToChangeBirthdayListener;
    private BackToLoginListener backToLoginListener;
    private GoToHomePageListener goToHomePageListener;



    public ProfileMenuView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        GridLayout gridLayout = new GridLayout(5,2);
        this.setBorder(border);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        this.setLayout(gridLayout);
        this.setBounds(panelConfig.getMainPanel().getX(),panelConfig.getMainPanel().getY()
                ,panelConfig.getMainPanel().getWidth(),panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configButtons();
        addButtons();

    }

    private void addButtons(){
        this.add(changeUserNameButton);
        this.add(changeNameButton);
        this.add(changeLastNameButton);
        this.add(changeBiographyButton);
        this.add(changePhoneNumberButton);
        this.add(changeEmailButton);
        this.add(changeBirthdayButton);
        this.add(changeProfPrivacyButton);
        this.add(logoutButton);
        this.add(backToHomePageButton);
    }

    private void configButtons(){
        changeUserNameButton.setFont(changeUserNameButton.getFont().deriveFont(15.0f));
        changeNameButton.setFont(changeNameButton.getFont().deriveFont(15.0f));
        changeLastNameButton.setFont(changeLastNameButton.getFont().deriveFont(15.0f));
        changeBiographyButton.setFont(changeBiographyButton.getFont().deriveFont(15.0f));
        changePhoneNumberButton.setFont(changePhoneNumberButton.getFont().deriveFont(15.0f));
        changeEmailButton.setFont(changeEmailButton.getFont().deriveFont(15.0f));
        changeProfPrivacyButton.setFont(changeProfPrivacyButton.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        changeBirthdayButton.setFont(changeBirthdayButton.getFont().deriveFont(15.0f));
        backToHomePageButton.setFont(backToHomePageButton.getFont().deriveFont(15.0f));


        changeUserNameButton.setBackground(colorConfig.getColor3());
        changeNameButton.setBackground(colorConfig.getColor3());
        changeLastNameButton.setBackground(colorConfig.getColor3());
        changeBiographyButton.setBackground(colorConfig.getColor3());
        changePhoneNumberButton.setBackground(colorConfig.getColor3());
        changeEmailButton.setBackground(colorConfig.getColor3());
        changeProfPrivacyButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());
        changeBirthdayButton.setBackground(colorConfig.getColor3());
        backToHomePageButton.setBackground(colorConfig.getColor3());


        changeUserNameButton.setForeground(colorConfig.getColor0());
        changeNameButton.setForeground(colorConfig.getColor0());
        changeLastNameButton.setForeground(colorConfig.getColor0());
        changeBiographyButton.setForeground(colorConfig.getColor0());
        changePhoneNumberButton.setForeground(colorConfig.getColor0());
        changeEmailButton.setForeground(colorConfig.getColor0());
        changeProfPrivacyButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());
        changeBirthdayButton.setForeground(colorConfig.getColor0());
        backToHomePageButton.setForeground(colorConfig.getColor0());


        changeUserNameButton.setFocusable(false);
        changeNameButton.setFocusable(false);
        changeLastNameButton.setFocusable(false);
        changeBiographyButton.setFocusable(false);
        changePhoneNumberButton.setFocusable(false);
        changeEmailButton.setFocusable(false);
        changeProfPrivacyButton.setFocusable(false);
        logoutButton.setFocusable(false);
        changeBirthdayButton.setFocusable(false);
        backToHomePageButton.setFocusable(false);


        changeUserNameButton.setBorder(BorderFactory.createEmptyBorder());
        changeNameButton.setBorder(BorderFactory.createEmptyBorder());
        changeLastNameButton.setBorder(BorderFactory.createEmptyBorder());
        changeBiographyButton.setBorder(BorderFactory.createEmptyBorder());
        changePhoneNumberButton.setBorder(BorderFactory.createEmptyBorder());
        changeEmailButton.setBorder(BorderFactory.createEmptyBorder());
        changeProfPrivacyButton.setBorder(BorderFactory.createEmptyBorder());
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        changeBirthdayButton.setBorder(BorderFactory.createEmptyBorder());
        backToHomePageButton.setBorder(BorderFactory.createEmptyBorder());

        changeUserNameButton.addActionListener(this);
        changeNameButton.addActionListener(this);
        changeLastNameButton.addActionListener(this);
        changeBiographyButton.addActionListener(this);
        changePhoneNumberButton.addActionListener(this);
        changeEmailButton.addActionListener(this);
        changeProfPrivacyButton.addActionListener(this);
        logoutButton.addActionListener(this);
        changeBirthdayButton.addActionListener(this);
        backToHomePageButton.addActionListener(this);
    }

    public void setGoToChangeUserNameListener(GoToChangeUserNameListener goToChangeUserNameListener) {
        this.goToChangeUserNameListener = goToChangeUserNameListener;
    }

    public void setGoToChangeNameListener(GoToChangeNameListener goToChangeNameListener) {
        this.goToChangeNameListener = goToChangeNameListener;
    }

    public void setGoToChangeLastNameListener(GoToChangeLastNameListener goToChangeLastNameListener) {
        this.goToChangeLastNameListener = goToChangeLastNameListener;
    }

    public void setGoToChangePhoneNumberListener(GoToChangePhoneNumberListener goToChangePhoneNumberListener) {
        this.goToChangePhoneNumberListener = goToChangePhoneNumberListener;
    }

    public void setGoToChangeEmailListener(GoToChangeEmailListener goToChangeEmailListener) {
        this.goToChangeEmailListener = goToChangeEmailListener;
    }

    public void setGoToChangeBioListener(GoToChangeBioListener goToChangeBioListener) {
        this.goToChangeBioListener = goToChangeBioListener;
    }

    public void setGoToChangeProfilePrivacyListener(GoToChangeProfilePrivacyListener goToChangeProfilePrivacyListener) {
        this.goToChangeProfilePrivacyListener = goToChangeProfilePrivacyListener;
    }

    public void setGoToChangeBirthdayListener(GoToChangeBirthdayListener goToChangeBirthdayListener) {
        this.goToChangeBirthdayListener = goToChangeBirthdayListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToHomePageListener(GoToHomePageListener goToHomePageListener) {
        this.goToHomePageListener = goToHomePageListener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (changeUserNameButton == e.getSource()){
            GoToChangeUserNameEvent goToChangeUserNameEvent = new GoToChangeUserNameEvent(source);
            goToChangeUserNameListener.eventOccurred(goToChangeUserNameEvent);

        }
        if (changeNameButton == e.getSource()){
            GoToChangeNameEvent goToChangeNameEvent = new GoToChangeNameEvent(source);
            goToChangeNameListener.eventOccurred(goToChangeNameEvent);

        }
        if (changeLastNameButton == e.getSource()){
            GoToChangeLastNameEvent goToChangeLastNameEvent = new GoToChangeLastNameEvent(source);
            goToChangeLastNameListener.eventOccurred(goToChangeLastNameEvent);

        }
        if (changePhoneNumberButton == e.getSource()){
            GoToChangePhoneNumberEvent goToChangePhoneNumberEvent = new GoToChangePhoneNumberEvent(source);
            goToChangePhoneNumberListener.eventOccurred(goToChangePhoneNumberEvent);

        }
        if (changeEmailButton == e.getSource()){
            GoToChangeEmailEvent goToChangeEmailEvent = new GoToChangeEmailEvent(source);
            goToChangeEmailListener.eventOccurred(goToChangeEmailEvent);

        }
        if (changeBiographyButton == e.getSource()){
            GoToChangeBioEvent goToChangeBioEvent = new GoToChangeBioEvent(source);
            goToChangeBioListener.eventOccurred(goToChangeBioEvent);

        }
        if (changeProfPrivacyButton == e.getSource()){
            GoToChangeProfilePrivacyEvent goToChangeProfilePrivacyEvent = new GoToChangeProfilePrivacyEvent(source);
            goToChangeProfilePrivacyListener.eventOccurred(goToChangeProfilePrivacyEvent);

        }
        if (changeBirthdayButton == e.getSource()){
            GoToChangeBirthdayEvent goToChangeBirthdayEvent = new GoToChangeBirthdayEvent(source);
            goToChangeBirthdayListener.eventOccurred(goToChangeBirthdayEvent);

        }
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToHomePageButton == e.getSource()){
            GoToHomePageEvent goToHomePageEvent = new GoToHomePageEvent(source);
            goToHomePageListener.eventOccurred(goToHomePageEvent);

        }

    }
}
