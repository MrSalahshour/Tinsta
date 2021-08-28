package ir.sharif.math.ap99_2.tinsta_client.view;



import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToRegisterListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.LoginFormListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.view.LoginView;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {
    private final LoginView loginView;


    public MainPanel() {
        loginView = new LoginView(this);
        initialize();

    }
    private void initialize() {
        config();
        loginView.setLoginFormListener(new LoginFormListener());
        loginView.setBackToRegisterListener(new BackToRegisterListener());
        this.add(loginView);
    }

    private void config() {
        PanelConfig panelConfig = new PanelConfig();
        setLayout(null);
        setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        setPreferredSize(new Dimension(panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight()));

    }


}
