package ir.sharif.math.ap99_2.tinsta_client.setting.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.DeActiveAccountListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.DeActiveAccountEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToSettingEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeActiveAccountView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton deActiveButton = new JButton("DeActive Your Account");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToSettingButton =new JButton("Back To Setting");
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToSettingListener goToSettingListener;
    private BackToLoginListener backToLoginListener;
    private DeActiveAccountListener deActiveAccountListener;


    public DeActiveAccountView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();

        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        BorderLayout borderLayout = new BorderLayout();
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addButtons();

    }

    public void setGoToSettingListener(GoToSettingListener goToSettingListener) {
        this.goToSettingListener = goToSettingListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setDeActiveAccountListener(DeActiveAccountListener deActiveAccountListener) {
        this.deActiveAccountListener = deActiveAccountListener;
    }

    private void addButtons(){
        this.add(logoutButton,BorderLayout.PAGE_END);
        this.add(backToSettingButton,BorderLayout.PAGE_START);
        this.add(deActiveButton,BorderLayout.CENTER);
    }

    private void configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        deActiveButton.setFont(deActiveButton.getFont().deriveFont(20.0f));
        backToSettingButton.setFont(backToSettingButton.getFont().deriveFont(15.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToSettingButton.setBackground(colorConfig.getColor3());
        deActiveButton.setBackground(colorConfig.getColor3());



        logoutButton.setForeground(colorConfig.getColor0());
        backToSettingButton.setForeground(colorConfig.getColor0());
        deActiveButton.setForeground(colorConfig.getColor0());



        logoutButton.setFocusable(false);
        backToSettingButton.setFocusable(false);
        deActiveButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        logoutButton.setPreferredSize(new Dimension(buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight()));
        backToSettingButton.setPreferredSize(new Dimension(buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight()));


        logoutButton.setBorder(border);
        backToSettingButton.setBorder(border);
        deActiveButton.setBorder(border);

        logoutButton.addActionListener(this);
        backToSettingButton.addActionListener(this);
        deActiveButton.addActionListener(this);


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
        if (deActiveButton == e.getSource()){
            DeActiveAccountEvent deActiveAccountEvent = new DeActiveAccountEvent(source);
            deActiveAccountListener.eventOccurred(deActiveAccountEvent);
        }

    }
}
