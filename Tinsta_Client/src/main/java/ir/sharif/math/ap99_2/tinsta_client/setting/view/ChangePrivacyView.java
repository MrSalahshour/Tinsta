package ir.sharif.math.ap99_2.tinsta_client.setting.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.MakePagePrivateListener;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.MakePagePublicListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.MakePagePrivateEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.MakePagePublicEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToSettingEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePrivacyView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton makeYourPagePublicButton =new JButton("Make Your Page Public");
    private final JButton makeYourPagePrivateButton =new JButton("Make Your Page Private");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToSettingButton =new JButton("Back To Setting");
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToSettingListener goToSettingListener;
    private BackToLoginListener backToLoginListener;
    private MakePagePublicListener makePagePublicListener;
    private MakePagePrivateListener makePagePrivateListener;


    public ChangePrivacyView(JPanel source) {
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

    public void setMakePagePublicListener(MakePagePublicListener makePagePublicListener) {
        this.makePagePublicListener = makePagePublicListener;
    }

    public void setMakePagePrivateListener(MakePagePrivateListener makePagePrivateListener) {
        this.makePagePrivateListener = makePagePrivateListener;
    }

    private void addButtons(){
        this.add(logoutButton,BorderLayout.PAGE_END);
        this.add(backToSettingButton,BorderLayout.PAGE_START);
        this.add(makeYourPagePublicButton,BorderLayout.LINE_START);
        this.add(makeYourPagePrivateButton,BorderLayout.LINE_END);
    }

    private void configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(17.0f));
        makeYourPagePublicButton.setFont(makeYourPagePublicButton.getFont().deriveFont(25.0f));
        backToSettingButton.setFont(backToSettingButton.getFont().deriveFont(17.0f));
        makeYourPagePrivateButton.setFont(makeYourPagePrivateButton.getFont().deriveFont(25.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToSettingButton.setBackground(colorConfig.getColor3());
        makeYourPagePublicButton.setBackground(colorConfig.getColor3());
        makeYourPagePrivateButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToSettingButton.setForeground(colorConfig.getColor0());
        makeYourPagePublicButton.setForeground(colorConfig.getColor0());
        makeYourPagePrivateButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToSettingButton.setFocusable(false);
        makeYourPagePublicButton.setFocusable(false);
        makeYourPagePrivateButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        makeYourPagePublicButton.setPreferredSize(new Dimension(buttonConfig.getB350x200()
                .getWidth(),buttonConfig.getB350x200().getHeight()));
        makeYourPagePrivateButton.setPreferredSize(new Dimension(buttonConfig.getB350x200()
                .getWidth(),buttonConfig.getB350x200().getHeight()));


        logoutButton.setBorder(border);
        backToSettingButton.setBorder(border);
        makeYourPagePublicButton.setBorder(border);
        makeYourPagePrivateButton.setBorder(border);

        logoutButton.addActionListener(this);
        backToSettingButton.addActionListener(this);
        makeYourPagePublicButton.addActionListener(this);
        makeYourPagePrivateButton.addActionListener(this);

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
        if (makeYourPagePrivateButton == e.getSource()){
            MakePagePrivateEvent makePagePrivateEvent = new MakePagePrivateEvent(source);
            makePagePrivateListener.eventOccurred(makePagePrivateEvent);

        }
        if (makeYourPagePublicButton == e.getSource()){
            MakePagePublicEvent makePagePublicEvent = new MakePagePublicEvent(source);
            makePagePublicListener.eventOccurred(makePagePublicEvent);

        }

    }
}
