package ir.sharif.math.ap99_2.tinsta_client.profile.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.listener.ChangeBioListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToChangeProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeBioEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBioView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JLabel bioLabel = new JLabel("Bio: ",SwingUtilities.CENTER);
    private final JTextField bioTextField = new JTextField(15);
    private final JButton changeBioButton =new JButton("Change Bio");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToProfileButton =new JButton("Back To Profile Menu");
    private final ColorConfig colorConfig = new ColorConfig();
    private ChangeBioListener changeBioListener;
    private BackToLoginListener backToLoginListener;
    private GoToChangeProfileListener goToChangeProfileListener;


    public ChangeBioView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configElements();
        addElements();
    }
    private void addElements(){
        this.add(logoutButton);
        this.add(backToProfileButton);
        this.add(bioLabel);
        this.add(bioTextField);
        this.add(changeBioButton);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        changeBioButton.setFont(changeBioButton.getFont().deriveFont(15.0f));
        backToProfileButton.setFont(backToProfileButton.getFont().deriveFont(15.0f));
        bioLabel.setFont(bioLabel.getFont().deriveFont(18.0f));
        bioTextField.setFont(bioTextField.getFont().deriveFont(18.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToProfileButton.setBackground(colorConfig.getColor3());
        bioLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToProfileButton.setForeground(colorConfig.getColor0());
        bioLabel.setForeground(colorConfig.getColor0());
        changeBioButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backToProfileButton.setFocusable(false);
        changeBioButton.setFocusable(false);


        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToProfileButton.setBorder(BorderFactory.createEmptyBorder());
        bioLabel.setBorder(BorderFactory.createEmptyBorder());
        bioTextField.setBorder(BorderFactory.createEmptyBorder());
        changeBioButton.setBorder(BorderFactory.createEmptyBorder());

        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();

        bioLabel.setBounds(155,220,labelConfig.getL150x50().getWidth(),labelConfig.getL150x50().getHeight());
        bioTextField.setBounds(300,220,textFieldConfig.gettF300x50()
                .getWidth(),textFieldConfig.gettF300x50().getHeight());
        changeBioButton.setBounds(280,320,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());
        logoutButton.setBounds(200,475,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());
        backToProfileButton.setBounds(200,10,buttonConfig.getB400x75().getWidth()
                ,buttonConfig.getB400x75().getHeight());


        logoutButton.addActionListener(this);
        backToProfileButton.addActionListener(this);
        changeBioButton.addActionListener(this);
    }

    public String getBioTextField() {
        return bioTextField.getText();
    }

    public void setBioTextField(String bioTextField) {
        this.bioTextField.setText(bioTextField);
    }

    public void setChangeBioListener(ChangeBioListener changeBioListener) {
        this.changeBioListener = changeBioListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToChangeProfileListener(GoToChangeProfileListener goToChangeProfileListener) {
        this.goToChangeProfileListener = goToChangeProfileListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToProfileButton == e.getSource()){
            GoToChangeProfileEvent goToChangeProfileEvent = new GoToChangeProfileEvent(source);
            goToChangeProfileListener.eventOccurred(goToChangeProfileEvent);
        }
        if (changeBioButton == e.getSource()){
            ChangeBioEvent changeBioEvent = new ChangeBioEvent(source,getBioTextField());
            changeBioListener.eventOccurred(changeBioEvent);
        }


    }
}
