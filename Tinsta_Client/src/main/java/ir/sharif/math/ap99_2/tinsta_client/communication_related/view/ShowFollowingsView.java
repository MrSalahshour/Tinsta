package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;

import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToCommunicationSecListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToCommunicationSecEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowFollowingsView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToCommunicationMenuButton =new JButton("Back To Communication Menu");
    private final JTextArea showFollowingsTextArea = new JTextArea();
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private GoToCommunicationSecListener goToCommunicationSecListener;


    public ShowFollowingsView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        BorderLayout borderLayout = new BorderLayout();
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        JScrollPane scrollPane =configElements(border);
        addElements(scrollPane);


    }
    private void addElements(JScrollPane scrollPane){
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(backToCommunicationMenuButton,BorderLayout.PAGE_START);
        this.add(logoutButton,BorderLayout.PAGE_END);
    }

    private JScrollPane configElements(Border border){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        backToCommunicationMenuButton.setFont(backToCommunicationMenuButton.getFont().deriveFont(15.0f));
        showFollowingsTextArea.setFont(showFollowingsTextArea.getFont().deriveFont(20.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToCommunicationMenuButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToCommunicationMenuButton.setForeground(colorConfig.getColor0());

        showFollowingsTextArea.setEditable(false);
        showFollowingsTextArea.setWrapStyleWord(true);
        showFollowingsTextArea.setOpaque(false);
        showFollowingsTextArea.setLineWrap(true);


        JScrollPane scrollPane = new JScrollPane(showFollowingsTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        logoutButton.setFocusable(false);
        backToCommunicationMenuButton.setFocusable(false);

        logoutButton.setBorder(border);
        backToCommunicationMenuButton.setBorder(border);

        logoutButton.addActionListener(this);
        backToCommunicationMenuButton.addActionListener(this);
        return scrollPane;

    }

    public void setShowFollowingsTextArea(String showFollowingsTextArea) {
        this.showFollowingsTextArea.setText(showFollowingsTextArea);
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToCommunicationSecListener(GoToCommunicationSecListener goToCommunicationSecListener) {
        this.goToCommunicationSecListener = goToCommunicationSecListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToCommunicationMenuButton == e.getSource()){
            GoToCommunicationSecEvent goToCommunicationSecEvent = new GoToCommunicationSecEvent(source);
            goToCommunicationSecListener.eventOccurred(goToCommunicationSecEvent);
        }
    }
}
