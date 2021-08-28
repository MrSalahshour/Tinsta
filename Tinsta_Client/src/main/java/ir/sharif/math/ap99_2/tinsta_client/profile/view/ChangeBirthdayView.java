package ir.sharif.math.ap99_2.tinsta_client.profile.view;



import ir.sharif.math.ap99_2.tinsta_client.config.*;
import ir.sharif.math.ap99_2.tinsta_client.home_page.listener.GoToChangeProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.profile.listener.ChangeBirthdayListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GoToChangeProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.profile.event.ChangeBirthdayEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBirthdayView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JLabel dayLabel = new JLabel("Day: ",SwingUtilities.CENTER);
    private final JTextField dayTextField = new JTextField(10);
    private final JLabel monthLabel = new JLabel("Month: ");
    private final JTextField monthTextField = new JTextField(10);
    private final JLabel yearLabel = new JLabel("Year: ",SwingUtilities.CENTER);
    private final JTextField yearTextField = new JTextField(10);
    private final JButton changeBirthdayButton =new JButton("Change Birthday");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToProfileButton =new JButton("Back To Profile Menu");
    private final ColorConfig colorConfig = new ColorConfig();
    private GoToChangeProfileListener goToChangeProfileListener;
    private BackToLoginListener backToLoginListener;
    private ChangeBirthdayListener changeBirthdayListener;


    public ChangeBirthdayView(JPanel source) {
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
        this.add(dayLabel);
        this.add(dayTextField);
        this.add(yearLabel);
        this.add(yearTextField);
        this.add(monthLabel);
        this.add(monthTextField);
        this.add(changeBirthdayButton);
    }

    private void configElements(){
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        changeBirthdayButton.setFont(changeBirthdayButton.getFont().deriveFont(15.0f));
        backToProfileButton.setFont(backToProfileButton.getFont().deriveFont(15.0f));
        dayLabel.setFont(dayLabel.getFont().deriveFont(15.0f));
        dayTextField.setFont(dayTextField.getFont().deriveFont(18.0f));
        monthLabel.setFont(dayLabel.getFont().deriveFont(15.0f));
        monthTextField.setFont(dayTextField.getFont().deriveFont(18.0f));
        yearLabel.setFont(dayLabel.getFont().deriveFont(15.0f));
        yearTextField.setFont(dayTextField.getFont().deriveFont(18.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        backToProfileButton.setBackground(colorConfig.getColor3());
        dayLabel.setBackground(colorConfig.getColor3());
        monthLabel.setBackground(colorConfig.getColor3());
        yearLabel.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        backToProfileButton.setForeground(colorConfig.getColor0());
        dayLabel.setForeground(colorConfig.getColor0());
        monthLabel.setForeground(colorConfig.getColor0());
        yearLabel.setForeground(colorConfig.getColor0());
        changeBirthdayButton.setForeground(colorConfig.getColor5());


        logoutButton.setFocusable(false);
        backToProfileButton.setFocusable(false);
        changeBirthdayButton.setFocusable(false);


        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToProfileButton.setBorder(BorderFactory.createEmptyBorder());
        dayLabel.setBorder(BorderFactory.createEmptyBorder());
        dayTextField.setBorder(BorderFactory.createEmptyBorder());
        monthLabel.setBorder(BorderFactory.createEmptyBorder());
        monthTextField.setBorder(BorderFactory.createEmptyBorder());
        yearLabel.setBorder(BorderFactory.createEmptyBorder());
        yearTextField.setBorder(BorderFactory.createEmptyBorder());
        changeBirthdayButton.setBorder(BorderFactory.createEmptyBorder());


        ButtonConfig buttonConfig = new ButtonConfig();
        TextFieldConfig textFieldConfig = new TextFieldConfig();
        LabelConfig labelConfig = new LabelConfig();


        dayLabel.setBounds(150,220,labelConfig.getL70x50().getWidth(),labelConfig.getL70x50().getHeight());
        dayTextField.setBounds(220,237,textFieldConfig.gettF50x20()
                .getWidth(),textFieldConfig.gettF50x20().getHeight());

        monthLabel.setBounds(320,220,labelConfig.getL70x50().getWidth(),labelConfig.getL70x50().getHeight());
        monthTextField.setBounds(390,237,textFieldConfig.gettF50x20()
                .getWidth(),textFieldConfig.gettF50x20().getHeight());

        yearLabel.setBounds(490,220,labelConfig.getL70x50().getWidth(),labelConfig.getL70x50().getHeight());
        yearTextField.setBounds(560,237,textFieldConfig.gettF50x20()
                .getWidth(),textFieldConfig.gettF50x20().getHeight());

        changeBirthdayButton.setBounds(270,320,buttonConfig.getB200x50().getWidth()
                ,buttonConfig.getB200x50().getHeight());

        logoutButton.setBounds(200,475,buttonConfig.getB400x50().getWidth()
                ,buttonConfig.getB400x50().getHeight());
        backToProfileButton.setBounds(200,10,buttonConfig.getB400x50().getWidth()
                ,buttonConfig.getB400x50().getHeight());

        logoutButton.addActionListener(this);
        backToProfileButton.addActionListener(this);
        changeBirthdayButton.addActionListener(this);
    }

    public void setGoToChangeProfileListener(GoToChangeProfileListener goToChangeProfileListener) {
        this.goToChangeProfileListener = goToChangeProfileListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setChangeBirthdayListener(ChangeBirthdayListener changeBirthdayListener) {
        this.changeBirthdayListener = changeBirthdayListener;
    }

    public String getDayTextField() {
        return dayTextField.getText();
    }

    public String getMonthTextField() {
        return monthTextField.getText();
    }

    public String getYearTextField() {
        return yearTextField.getText();
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
        if (changeBirthdayButton == e.getSource()){
            if (checkValidation()){
                ChangeBirthdayEvent changeBirthdayEvent = new ChangeBirthdayEvent
                        (source,getDayTextField(),getMonthTextField(),getYearTextField());
                changeBirthdayListener.eventOccurred(changeBirthdayEvent);


            }
        }

    }
    public boolean checkValidation(){
        if (!Validation.isValidAge(getYearTextField(),getMonthTextField(),getDayTextField())){
            String message = "Age is Not Valid";
            JOptionPane.showMessageDialog(source, message,
                    "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
