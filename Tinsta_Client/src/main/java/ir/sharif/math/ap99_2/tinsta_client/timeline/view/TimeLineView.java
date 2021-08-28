package ir.sharif.math.ap99_2.tinsta_client.timeline.view;


import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_client.timeline.listener.ShowTimeLineTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.BackToMenuEvent;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.event.ShowTimeLineTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeLineView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton showTimeLineTweetsButton = new JButton("Show TimeLine Tweets");
    private final JButton backToMenuButton = new JButton("Back To Menu");
    private final JButton logoutButton = new JButton("Logout");
    private final ColorConfig colorConfig = new ColorConfig();
    private ShowTimeLineTweetListener showTimeLineTweetListener;
    private BackToMenuListener backToMenuListener;
    private BackToLoginListener backToLoginListener;


    public TimeLineView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        BorderLayout borderLayout = new BorderLayout(10,10);
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements(border);
        addElements();

    }

    public JPanel getSource() {
        return source;
    }

    public void setShowTimeLineTweetListener(ShowTimeLineTweetListener showTimeLineTweetListener) {
        this.showTimeLineTweetListener = showTimeLineTweetListener;
    }

    public void setBackToMenuListener(BackToMenuListener backToMenuListener) {
        this.backToMenuListener = backToMenuListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    private void addElements(){
        this.add(backToMenuButton,BorderLayout.PAGE_START);
        this.add(showTimeLineTweetsButton,BorderLayout.CENTER);
        this.add(logoutButton,BorderLayout.PAGE_END);
    }

    private void configElements(Border border){
        backToMenuButton.setFont(backToMenuButton.getFont().deriveFont(15.0f));
        showTimeLineTweetsButton.setFont(showTimeLineTweetsButton.getFont().deriveFont(25.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));

        showTimeLineTweetsButton.setBackground(colorConfig.getColor3());
        backToMenuButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());

        showTimeLineTweetsButton.setForeground(colorConfig.getColor0());
        backToMenuButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());

        backToMenuButton.setBorder(border);
        showTimeLineTweetsButton.setBorder(border);
        logoutButton.setBorder(border);

        backToMenuButton.setFocusable(false);
        showTimeLineTweetsButton.setFocusable(false);
        logoutButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();

        backToMenuButton.setPreferredSize(new Dimension(buttonConfig.getB200x100()
                .getWidth(),buttonConfig.getB200x100().getHeight()));
        showTimeLineTweetsButton.setPreferredSize(new Dimension(buttonConfig.getB400x300()
                .getWidth(),buttonConfig.getB400x300().getHeight()));
        logoutButton.setPreferredSize(new Dimension(buttonConfig.getB200x100()
                .getWidth(),buttonConfig.getB200x100().getHeight()));

        backToMenuButton.addActionListener(this);
        showTimeLineTweetsButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (showTimeLineTweetsButton == e.getSource()){
            ShowTimeLineTweetEvent showTimeLineTweetEvent = new ShowTimeLineTweetEvent(this);
            showTimeLineTweetListener.eventOccurred(showTimeLineTweetEvent);
        }
        if (backToMenuButton == e.getSource()){
            BackToMenuEvent backToMenuEvent = new BackToMenuEvent(source);
            backToMenuListener.eventOccurred(backToMenuEvent);
        }
    }

    @Override
    public String toString() {
        return "TimeLineView";
    }
}
