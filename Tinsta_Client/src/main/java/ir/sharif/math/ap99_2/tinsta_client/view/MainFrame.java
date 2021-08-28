package ir.sharif.math.ap99_2.tinsta_client.view;


import ir.sharif.math.ap99_2.tinsta_client.config.FrameConfig;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private MainPanel mainPanel;

    public static MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }

    public MainFrame() {
        this.mainPanel = new MainPanel();
        this.config();
        setContentPane(mainPanel);
    }

    private void config() {
        FrameConfig frameConfig = new FrameConfig();
        setSize(new Dimension(frameConfig.getWidth(), frameConfig.getHeight()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(frameConfig.isResizable());
        setUndecorated(frameConfig.isUndecorated());
        setTitle(frameConfig.getTitle());
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void setContentPane(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        super.setContentPane(mainPanel);
        super.revalidate();
        super.repaint(1L);
        super.pack();
    }

    private void update() {
        super.revalidate();
        super.repaint();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
