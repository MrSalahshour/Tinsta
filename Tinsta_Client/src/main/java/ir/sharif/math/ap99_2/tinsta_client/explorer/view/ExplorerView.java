package ir.sharif.math.ap99_2.tinsta_client.explorer.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.GoToSearchUserListener;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.ShowExplorerTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GoToSearchUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.ShowExplorerTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.BackToMenuEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExplorerView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton showExplorerTweetsButton = new JButton("Show Explorer Tweets");
    private final JButton searchUserButton = new JButton("Search User");
    private final JButton backToMenuButton = new JButton("Back To Menu");
    private final JButton logoutButton = new JButton("Logout");
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToMenuListener backToMenuListener;
    private BackToLoginListener backToLoginListener;
    private ShowExplorerTweetListener showExplorerTweetListener;
    private GoToSearchUserListener goToSearchUserListener;


    public ExplorerView(JPanel source) {
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
    private void addElements(){
        backToMenuButton.addActionListener(this);
        showExplorerTweetsButton.addActionListener(this);
        logoutButton.addActionListener(this);
        searchUserButton.addActionListener(this);
    }

    private void configElements(Border border){
        backToMenuButton.setFont(backToMenuButton.getFont().deriveFont(15.0f));
        showExplorerTweetsButton.setFont(showExplorerTweetsButton.getFont().deriveFont(20.0f));
        searchUserButton.setFont(searchUserButton.getFont().deriveFont(20.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));


        showExplorerTweetsButton.setBackground(colorConfig.getColor3());
        searchUserButton.setBackground(colorConfig.getColor3());
        backToMenuButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());

        showExplorerTweetsButton.setForeground(colorConfig.getColor0());
        searchUserButton.setForeground(colorConfig.getColor0());
        backToMenuButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());



        backToMenuButton.setBorder(border);
        showExplorerTweetsButton.setBorder(border);
        logoutButton.setBorder(border);
        searchUserButton.setBorder(border);


        backToMenuButton.setFocusable(false);
        showExplorerTweetsButton.setFocusable(false);
        logoutButton.setFocusable(false);
        searchUserButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();

        backToMenuButton.setPreferredSize(new Dimension(buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight()));
        showExplorerTweetsButton.setPreferredSize(new Dimension(buttonConfig.getB350x200()
                .getWidth(),buttonConfig.getB350x200().getHeight()));
        searchUserButton.setPreferredSize(new Dimension(buttonConfig.getB350x200()
                .getWidth(),buttonConfig.getB350x200().getHeight()));
        logoutButton.setPreferredSize(new Dimension(buttonConfig.getB400x50()
                .getWidth(),buttonConfig.getB400x50().getHeight()));


        this.add(backToMenuButton,BorderLayout.PAGE_START);
        this.add(showExplorerTweetsButton,BorderLayout.EAST);
        this.add(searchUserButton,BorderLayout.WEST);
        this.add(logoutButton,BorderLayout.PAGE_END);
    }

    public JPanel getSource() {
        return source;
    }

    public void setBackToMenuListener(BackToMenuListener backToMenuListener) {
        this.backToMenuListener = backToMenuListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setShowExplorerTweetListener(ShowExplorerTweetListener showExplorerTweetListener) {
        this.showExplorerTweetListener = showExplorerTweetListener;
    }

    public void setGoToSearchUserListener(GoToSearchUserListener goToSearchUserListener) {
        this.goToSearchUserListener = goToSearchUserListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (backToMenuButton == e.getSource()){
            BackToMenuEvent backToMenuEvent = new BackToMenuEvent(source);
            backToMenuListener.eventOccurred(backToMenuEvent);
        }
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (showExplorerTweetsButton == e.getSource()){
            ShowExplorerTweetEvent showExplorerTweetEvent = new ShowExplorerTweetEvent(this);
            showExplorerTweetListener.eventOccurred(showExplorerTweetEvent);
        }
        if (searchUserButton == e.getSource()){
            GoToSearchUserEvent goToSearchUserEvent = new GoToSearchUserEvent(this);
            goToSearchUserListener.eventOccurred(goToSearchUserEvent);
        }


    }

    @Override
    public String toString() {
        return "ExplorerView";
    }
}
