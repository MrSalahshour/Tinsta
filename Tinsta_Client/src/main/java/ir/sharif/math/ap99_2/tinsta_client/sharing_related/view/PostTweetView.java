package ir.sharif.math.ap99_2.tinsta_client.sharing_related.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ScrollPaneConfig;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.BackToListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.PostTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.SelectImageForTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.BackToEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.PostTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.SelectImageForTweetEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostTweetView extends JPanel implements ActionListener {
    private final JPanel source;
    private final ShowTweetsView showTweetsViewSource;
    private final JButton backToButton = new JButton("Back");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton selectImageButton = new JButton("Select Image");
    private final JButton tweetButton = new JButton("Tweet");
    private final JTextArea tweetTextArea = new JTextArea();
    private final ColorConfig colorConfig = new ColorConfig();
    private BackToLoginListener backToLoginListener;
    private PostTweetListener postTweetListener;
    private SelectImageForTweetListener selectImageForTweetListener;
    private BackToListener backToListener;
    private String imagePath;


    public PostTweetView(JPanel source, ShowTweetsView showTweetsViewSource) {
        this.source = source;
        this.showTweetsViewSource = showTweetsViewSource;
        PanelConfig panelConfig = new PanelConfig();

        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        BorderLayout borderLayout = new BorderLayout();
        this.setBorder(border);
        this.setLayout(borderLayout);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        JScrollPane areaScrollPane = new JScrollPane(tweetTextArea);

        ScrollPaneConfig scrollPaneConfig = new ScrollPaneConfig();

        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(scrollPaneConfig.getsP250x250().getWidth()
                , scrollPaneConfig.getsP250x250().getHeight()));
        configElements(border);
        addElements(areaScrollPane);

    }

    private void addElements(JScrollPane areaScrollPane){
        this.add(backToButton,BorderLayout.PAGE_START);
        this.add(tweetButton,BorderLayout.LINE_START);
        this.add(selectImageButton,BorderLayout.LINE_END);
        this.add(logoutButton,BorderLayout.PAGE_END);
        this.add(areaScrollPane,BorderLayout.CENTER);

    }

    private void configElements(Border border){
        backToButton.setFont(backToButton.getFont().deriveFont(15.0f));
        tweetTextArea.setFont(tweetTextArea.getFont().deriveFont(15.0f));
        selectImageButton.setFont(selectImageButton.getFont().deriveFont(20.0f));
        tweetButton.setFont(tweetButton.getFont().deriveFont(20.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));

        tweetButton.setBackground(colorConfig.getColor3());
        selectImageButton.setBackground(colorConfig.getColor3());
        backToButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());
        tweetTextArea.setBackground(colorConfig.getColor6());

        tweetButton.setForeground(colorConfig.getColor0());
        selectImageButton.setForeground(colorConfig.getColor0());
        backToButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());

        tweetTextArea.setWrapStyleWord(true);
        tweetTextArea.setLineWrap(true);

        backToButton.setBorder(border);
        tweetTextArea.setBorder(border);
        logoutButton.setBorder(border);
        selectImageButton.setBorder(border);
        tweetButton.setBorder(border);

        backToButton.setFocusable(false);
        tweetButton.setFocusable(false);
        logoutButton.setFocusable(false);
        selectImageButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();

        backToButton.setPreferredSize(new Dimension(buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight()));
        logoutButton.setPreferredSize(new Dimension(buttonConfig.getB400x75()
                .getWidth(),buttonConfig.getB400x75().getHeight()));
        tweetButton.setPreferredSize(new Dimension(buttonConfig.getB200x200()
                .getWidth(),buttonConfig.getB200x200().getHeight()));
        selectImageButton.setPreferredSize(new Dimension(buttonConfig.getB200x200()
                .getWidth(),buttonConfig.getB200x200().getHeight()));

        backToButton.addActionListener(this);
        tweetButton.addActionListener(this);
        logoutButton.addActionListener(this);
        selectImageButton.addActionListener(this);

    }

    public String getTweetTextArea() {
        return tweetTextArea.getText();
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public void setPostTweetListener(PostTweetListener postTweetListener) {
        this.postTweetListener = postTweetListener;
    }

    public void setSelectImageForTweetListener(SelectImageForTweetListener selectImageForTweetListener) {
        this.selectImageForTweetListener = selectImageForTweetListener;
    }

    public ShowTweetsView getShowTweetsViewSource() {
        return showTweetsViewSource;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToButton == e.getSource()){
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }
        if (selectImageButton == e.getSource()){
            SelectImageForTweetEvent selectImageForTweetEvent = new SelectImageForTweetEvent(this);
            selectImageForTweetListener.eventOccurred(selectImageForTweetEvent);
        }
        if (tweetButton == e.getSource()){
            PostTweetEvent postTweetEvent = new PostTweetEvent(this,getImagePath(),getTweetTextArea());
            postTweetListener.eventOccurred(postTweetEvent);
        }

    }
}
