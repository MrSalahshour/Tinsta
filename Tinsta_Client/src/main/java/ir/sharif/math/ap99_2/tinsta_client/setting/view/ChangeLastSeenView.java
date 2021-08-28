package ir.sharif.math.ap99_2.tinsta_client.setting.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.ShowLastSeenToAllListener;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.ShowLastSeenToFollowersListener;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.ShowLastSeenToNoOneListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ShowLastSeenToAllEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ShowLastSeenToFollowersEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ShowLastSeenToNoOneEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToSettingEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeLastSeenView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton showLastSeenToAllButton =new JButton("Show Last Seen To All");
    private final JButton showLastSeenToNoOneButton =new JButton("Show Last Seen To No one");
    private final JButton showLastSeenToFollowersButton =new JButton("Show Last Seen To Followers");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToSettingButton =new JButton("Back To Setting");
    private final ColorConfig colorConfig = new ColorConfig();
    private ShowLastSeenToAllListener showLastSeenToAllListener;
    private ShowLastSeenToNoOneListener showLastSeenToNoOneListener;
    private ShowLastSeenToFollowersListener showLastSeenToFollowersListener;
    private GoToSettingListener goToSettingListener;
    private BackToLoginListener backToLoginListener;


    public ChangeLastSeenView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        BorderLayout borderLayout = new BorderLayout();
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configButtons(border);
        addButtons();

    }

    public void setShowLastSeenToAllListener(ShowLastSeenToAllListener showLastSeenToAllListener) {
        this.showLastSeenToAllListener = showLastSeenToAllListener;
    }

    public void setShowLastSeenToNoOneListener(ShowLastSeenToNoOneListener showLastSeenToNoOneListener) {
        this.showLastSeenToNoOneListener = showLastSeenToNoOneListener;
    }

    public void setShowLastSeenToFollowersListener(ShowLastSeenToFollowersListener showLastSeenToFollowersListener) {
        this.showLastSeenToFollowersListener = showLastSeenToFollowersListener;
    }

    public void setGoToSettingListener(GoToSettingListener goToSettingListener) {
        this.goToSettingListener = goToSettingListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    private void addButtons(){
        this.add(logoutButton,BorderLayout.PAGE_END);
        this.add(backToSettingButton,BorderLayout.PAGE_START);
        this.add(showLastSeenToFollowersButton,BorderLayout.LINE_START);
        this.add(showLastSeenToNoOneButton,BorderLayout.LINE_END);
        this.add(showLastSeenToAllButton,BorderLayout.CENTER);
    }

    private void configButtons(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(13.0f));
        showLastSeenToAllButton.setFont(showLastSeenToAllButton.getFont().deriveFont(15.0f));
        backToSettingButton.setFont(backToSettingButton.getFont().deriveFont(13.0f));
        showLastSeenToFollowersButton.setFont(showLastSeenToFollowersButton.getFont().deriveFont(15.0f));
        showLastSeenToNoOneButton.setFont(showLastSeenToNoOneButton.getFont().deriveFont(15.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToSettingButton.setBackground(colorConfig.getColor3());
        showLastSeenToAllButton.setBackground(colorConfig.getColor3());
        showLastSeenToFollowersButton.setBackground(colorConfig.getColor3());
        showLastSeenToNoOneButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToSettingButton.setForeground(colorConfig.getColor0());
        showLastSeenToAllButton.setForeground(colorConfig.getColor0());
        showLastSeenToFollowersButton.setForeground(colorConfig.getColor0());
        showLastSeenToNoOneButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backToSettingButton.setFocusable(false);
        showLastSeenToAllButton.setFocusable(false);
        showLastSeenToFollowersButton.setFocusable(false);
        showLastSeenToNoOneButton.setFocusable(false);


        logoutButton.setBorder(border);
        backToSettingButton.setBorder(border);
        showLastSeenToAllButton.setBorder(border);
        showLastSeenToFollowersButton.setBorder(border);
        showLastSeenToNoOneButton.setBorder(border);


        ButtonConfig buttonConfig = new ButtonConfig();
        showLastSeenToNoOneButton.setPreferredSize(new Dimension(buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight()));
        showLastSeenToFollowersButton.setPreferredSize(new Dimension(buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight()));
        showLastSeenToAllButton.setPreferredSize(new Dimension(buttonConfig.getB250x125()
                .getWidth(),buttonConfig.getB250x125().getHeight()));

        logoutButton.addActionListener(this);
        backToSettingButton.addActionListener(this);
        showLastSeenToAllButton.addActionListener(this);
        showLastSeenToFollowersButton.addActionListener(this);
        showLastSeenToNoOneButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (showLastSeenToAllButton == e.getSource()){
            ShowLastSeenToAllEvent showLastSeenToAllEvent = new ShowLastSeenToAllEvent(source);
            showLastSeenToAllListener.eventOccurred(showLastSeenToAllEvent);

        }
        if (showLastSeenToFollowersButton == e.getSource()){
            ShowLastSeenToFollowersEvent showLastSeenToFollowersEvent = new ShowLastSeenToFollowersEvent(source);
            showLastSeenToFollowersListener.eventOccurred(showLastSeenToFollowersEvent);
        }
        if (showLastSeenToNoOneButton == e.getSource()){
            ShowLastSeenToNoOneEvent showLastSeenToNoOneEvent = new ShowLastSeenToNoOneEvent(source);
            showLastSeenToNoOneListener.eventOccurred(showLastSeenToNoOneEvent);

        }
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
