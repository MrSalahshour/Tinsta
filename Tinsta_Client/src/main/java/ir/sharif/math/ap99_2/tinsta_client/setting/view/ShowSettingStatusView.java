package ir.sharif.math.ap99_2.tinsta_client.setting.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.LabelConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToSettingEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowSettingStatusView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToSettingButton =new JButton("Back To Setting");
    private final JLabel privacyStatusLabel = new JLabel();
    private final JLabel lastSeenStatusLabel = new JLabel();
    private final JLabel activationStatusLabel = new JLabel("Activation Status: Active",SwingUtilities.CENTER);
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToSettingListener goToSettingListener;
    private BackToLoginListener backToLoginListener;


    public ShowSettingStatusView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();
    }

    public void setLastSeenStatusLabelText(String text){
        lastSeenStatusLabel.setText(text);
    }
    public void setPrivacyStatusLabelText(String text){
        privacyStatusLabel.setText(text);
    }

    public void setGoToSettingListener(GoToSettingListener goToSettingListener) {
        this.goToSettingListener = goToSettingListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    private void addElements(){
        this.add(logoutButton);
        this.add(backToSettingButton);
        this.add(activationStatusLabel);
        this.add(lastSeenStatusLabel);
        this.add(privacyStatusLabel);
    }

    private void configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        activationStatusLabel.setFont(activationStatusLabel.getFont().deriveFont(15.0f));
        backToSettingButton.setFont(backToSettingButton.getFont().deriveFont(15.0f));
        privacyStatusLabel.setFont(backToSettingButton.getFont().deriveFont(15.0f));
        lastSeenStatusLabel.setFont(backToSettingButton.getFont().deriveFont(15.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToSettingButton.setBackground(colorConfig.getColor3());
        privacyStatusLabel.setBackground(colorConfig.getColor3());
        activationStatusLabel.setBackground(colorConfig.getColor3());
        lastSeenStatusLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToSettingButton.setForeground(colorConfig.getColor0());
        privacyStatusLabel.setForeground(colorConfig.getColor0());
        lastSeenStatusLabel.setForeground(colorConfig.getColor0());
        activationStatusLabel.setForeground(colorConfig.getColor0());

        privacyStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        privacyStatusLabel.setVerticalAlignment(SwingConstants.CENTER);
        lastSeenStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lastSeenStatusLabel.setVerticalAlignment(SwingConstants.CENTER);


        logoutButton.setFocusable(false);
        backToSettingButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        LabelConfig labelConfig = new LabelConfig();

        logoutButton.setBounds(200,510,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        backToSettingButton.setBounds(200,0,buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight());
        activationStatusLabel.setBounds(250,200,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        lastSeenStatusLabel.setBounds(250,250,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());
        privacyStatusLabel.setBounds(250,300,labelConfig.getL300x20()
                .getWidth(),labelConfig.getL300x20().getHeight());


        logoutButton.setBorder(border);
        backToSettingButton.setBorder(border);

        logoutButton.addActionListener(this);
        backToSettingButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToSettingButton == e.getSource()){
            GoToSettingEvent goToSettingEvent = new GoToSettingEvent(source);
            goToSettingListener.eventOccurred(goToSettingEvent);
        }
    }
}
