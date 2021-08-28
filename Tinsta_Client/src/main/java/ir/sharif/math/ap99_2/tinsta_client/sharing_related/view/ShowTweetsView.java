package ir.sharif.math.ap99_2.tinsta_client.sharing_related.view;


import ir.sharif.math.ap99_2.tinsta_client.config.ButtonConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.GoToUserProfileListener;
import ir.sharif.math.ap99_2.tinsta_client.sharing_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GoToUserProfileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowTweetsView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton nextButton = new JButton("Next");
    private final JButton previousButton = new JButton("Previous");
    private final JButton likeButton = new JButton("Like");
    private final JButton addCommentButton = new JButton("Add Comment");
    private final JButton showCommentsButton = new JButton("Show Comments");
    private final JButton forwardButton = new JButton("Forward");
    private final JButton addToSavedMessagesButton = new JButton("Add To Saved Msg");
    private final JButton goToUserProfButton = new JButton("Go To User Profile");
    private final JButton reportButton = new JButton("Report");
    private final JButton reTweetButton = new JButton("ReTweet");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton backButton = new JButton("Back");
    private final JButton onlineModeButton = new JButton("Online Mode");
    private final JButton offlineModeButton = new JButton("Offline Mode");
    private final ColorConfig colorConfig = new ColorConfig();
    private MessageView messageView;
    private NextTweetListener nextTweetListener;
    private PreviousTweetListener previousTweetListener;
    private LikeTweetListener likeTweetListener;
    private AddCommentListener addCommentListener;
    private ShowCommentsListener showCommentsListener;
    private GoToForwardTweetListener goToForwardTweetListener;
    private AddToSavedMessagesListener addToSavedMessagesListener;
    private GoToUserProfileListener goToUserProfileListener;
    private ReportListener reportListener;
    private ReTweetListener reTweetListener;
    private BackToLoginListener backToLoginListener;
    private BackToListener backToListener;
    private boolean online;
    private boolean comment;


    public ShowTweetsView(JPanel source) {
        this.source = source;
        this.online = true;
        this.comment = false;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(), 10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getMainPanel().getX(), panelConfig.getMainPanel().getY()
                , panelConfig.getMainPanel().getWidth(), panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());


    }

    private void addElements() {
        this.add(nextButton);
        this.add(previousButton);
        this.add(addCommentButton);
        this.add(showCommentsButton);
        this.add(forwardButton);
        this.add(addToSavedMessagesButton);
        this.add(backButton);
        this.add(logoutButton);
        this.add(likeButton);
        this.add(goToUserProfButton);
        this.add(reportButton);
        this.add(reTweetButton);
        this.add(messageView);
        this.add(onlineModeButton);
        this.add(offlineModeButton);

    }

    public void configElements() {
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        onlineModeButton.setFont(onlineModeButton.getFont().deriveFont(10.0f));
        offlineModeButton.setFont(offlineModeButton.getFont().deriveFont(10.0f));
        backButton.setFont(backButton.getFont().deriveFont(15.0f));
        addToSavedMessagesButton.setFont(addToSavedMessagesButton.getFont().deriveFont(9.0f));
        nextButton.setFont(nextButton.getFont().deriveFont(10.0f));
        previousButton.setFont(previousButton.getFont().deriveFont(10.0f));
        forwardButton.setFont(forwardButton.getFont().deriveFont(10.0f));
        addCommentButton.setFont(addCommentButton.getFont().deriveFont(10.0f));
        showCommentsButton.setFont(showCommentsButton.getFont().deriveFont(10.0f));
        likeButton.setFont(likeButton.getFont().deriveFont(10.0f));
        goToUserProfButton.setFont(goToUserProfButton.getFont().deriveFont(9.0f));
        reportButton.setFont(reportButton.getFont().deriveFont(9.0f));
        reTweetButton.setFont(reTweetButton.getFont().deriveFont(10.0f));


        logoutButton.setBackground(colorConfig.getColor3());
        onlineModeButton.setBackground(colorConfig.getColor3());
        offlineModeButton.setBackground(colorConfig.getColor3());
        backButton.setBackground(colorConfig.getColor3());
        addCommentButton.setBackground(colorConfig.getColor3());
        forwardButton.setBackground(colorConfig.getColor3());
        nextButton.setBackground(colorConfig.getColor3());
        showCommentsButton.setBackground(colorConfig.getColor3());
        previousButton.setBackground(colorConfig.getColor3());
        addToSavedMessagesButton.setBackground(colorConfig.getColor3());
        likeButton.setBackground(colorConfig.getColor3());
        goToUserProfButton.setBackground(colorConfig.getColor3());
        reportButton.setBackground(colorConfig.getColor3());
        reTweetButton.setBackground(colorConfig.getColor3());


        logoutButton.setForeground(colorConfig.getColor0());
        onlineModeButton.setForeground(colorConfig.getColor0());
        offlineModeButton.setForeground(colorConfig.getColor0());
        backButton.setForeground(colorConfig.getColor0());
        nextButton.setForeground(colorConfig.getColor0());
        forwardButton.setForeground(colorConfig.getColor0());
        addCommentButton.setForeground(colorConfig.getColor0());
        addToSavedMessagesButton.setForeground(colorConfig.getColor0());
        previousButton.setForeground(colorConfig.getColor0());
        showCommentsButton.setForeground(colorConfig.getColor0());
        likeButton.setForeground(colorConfig.getColor0());
        goToUserProfButton.setForeground(colorConfig.getColor0());
        reportButton.setForeground(colorConfig.getColor0());
        reTweetButton.setForeground(colorConfig.getColor0());


        logoutButton.setFocusable(false);
        backButton.setFocusable(false);
        nextButton.setFocusable(false);
        forwardButton.setFocusable(false);
        addCommentButton.setFocusable(false);
        addToSavedMessagesButton.setFocusable(false);
        previousButton.setFocusable(false);
        showCommentsButton.setFocusable(false);
        likeButton.setFocusable(false);
        goToUserProfButton.setFocusable(false);
        reportButton.setFocusable(false);
        reTweetButton.setFocusable(false);
        onlineModeButton.setFocusable(false);
        offlineModeButton.setFocusable(false);

        ButtonConfig buttonConfig = new ButtonConfig();
        PanelConfig panelConfig = new PanelConfig();

        logoutButton.setBounds(450, 10, buttonConfig.getB200x50()
                .getWidth(), buttonConfig.getB200x50().getHeight());
        offlineModeButton.setBounds(675, 5, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        onlineModeButton.setBounds(675, 35, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        backButton.setBounds(150, 10, buttonConfig.getB200x50()
                .getWidth(), buttonConfig.getB200x50().getHeight());
        messageView.setBounds(200, 100, panelConfig.getTweetPanel()
                .getWidth(), panelConfig.getTweetPanel().getHeight());
        nextButton.setBounds(60, 400, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        previousButton.setBounds(200, 400, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        likeButton.setBounds(340, 400, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        addCommentButton.setBounds(480, 400, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        showCommentsButton.setBounds(620, 400, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        addToSavedMessagesButton.setBounds(60, 450, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        forwardButton.setBounds(200, 450, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        reportButton.setBounds(340, 450, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        reTweetButton.setBounds(480, 450, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());
        goToUserProfButton.setBounds(620, 450, buttonConfig.getB120x25()
                .getWidth(), buttonConfig.getB120x25().getHeight());


        logoutButton.addActionListener(this);
        backButton.addActionListener(this);
        nextButton.addActionListener(this);
        forwardButton.addActionListener(this);
        addCommentButton.addActionListener(this);
        addToSavedMessagesButton.addActionListener(this);
        previousButton.addActionListener(this);
        showCommentsButton.addActionListener(this);
        likeButton.addActionListener(this);
        goToUserProfButton.addActionListener(this);
        reportButton.addActionListener(this);
        reTweetButton.addActionListener(this);
        offlineModeButton.addActionListener(this);
        onlineModeButton.addActionListener(this);
        addElements();

    }

    public void configMessageView() {
        PanelConfig panelConfig = new PanelConfig();
        messageView.setBounds(200, 100, panelConfig.getTweetPanel()
                .getWidth(), panelConfig.getTweetPanel().getHeight());
        this.add(messageView);
    }

    public JPanel getSource() {
        return source;
    }

    public synchronized void setTweetView(MessageView messageView) {
        this.messageView = messageView;
    }

    public void setNextTweetListener(NextTweetListener nextTweetListener) {
        this.nextTweetListener = nextTweetListener;
    }

    public void setPreviousTweetListener(PreviousTweetListener previousTweetListener) {
        this.previousTweetListener = previousTweetListener;
    }

    public void setLikeTweetListener(LikeTweetListener likeTweetListener) {
        this.likeTweetListener = likeTweetListener;
    }

    public void setAddCommentListener(AddCommentListener addCommentListener) {
        this.addCommentListener = addCommentListener;
    }

    public void setShowCommentsListener(ShowCommentsListener showCommentsListener) {
        this.showCommentsListener = showCommentsListener;
    }

    public void setForwardTweetListener(GoToForwardTweetListener goToForwardTweetListener) {
        this.goToForwardTweetListener = goToForwardTweetListener;
    }

    public void setAddToSavedMessagesListener(AddToSavedMessagesListener addToSavedMessagesListener) {
        this.addToSavedMessagesListener = addToSavedMessagesListener;
    }

    public void setGoToUserProfileListener(GoToUserProfileListener goToUserProfileListener) {
        this.goToUserProfileListener = goToUserProfileListener;
    }

    public void setReportListener(ReportListener reportListener) {
        this.reportListener = reportListener;
    }

    public void setReTweetListener(ReTweetListener reTweetListener) {
        this.reTweetListener = reTweetListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setBackToListener(BackToListener backToListener) {
        this.backToListener = backToListener;
    }

    public synchronized MessageView getMessageView() {
        return messageView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (nextButton == e.getSource()) {
            NextTweetEvent nextTweetEvent = new NextTweetEvent(this);
            nextTweetListener.eventOccurred(nextTweetEvent);
        }
        if (previousButton == e.getSource()) {
            PreviousTweetEvent previousTweetEvent = new PreviousTweetEvent(this);
            previousTweetListener.eventOccurred(previousTweetEvent);
        }
        if (likeButton == e.getSource()) {
            LikeTweetEvent likeTweetEvent = new LikeTweetEvent(this);
            likeTweetListener.eventOccurred(likeTweetEvent);
        }
        if (addCommentButton == e.getSource()) {
            AddCommentEvent addCommentEvent = new AddCommentEvent(this);
            addCommentListener.eventOccurred(addCommentEvent);
        }
        if (showCommentsButton == e.getSource()) {
            ShowCommentsEvent showCommentsEvent = new ShowCommentsEvent(this);
            showCommentsListener.eventOccurred(showCommentsEvent);
        }
        if (forwardButton == e.getSource()) {
            GoToForwardTweetEvent goToForwardTweetEvent = new GoToForwardTweetEvent(this);
            goToForwardTweetListener.eventOccurred(goToForwardTweetEvent);
        }
        if (goToUserProfButton == e.getSource()) {
            GoToUserProfileEvent goToUserProfileEvent = new GoToUserProfileEvent(this);
            goToUserProfileListener.eventOccurred(goToUserProfileEvent);
        }
        if (backButton == e.getSource()) {
            BackToEvent backToEvent = new BackToEvent(source);
            backToListener.eventOccurred(backToEvent);
        }

        if (addToSavedMessagesButton == e.getSource()) {
            AddToSavedMessageEvent addToSavedMessageEvent = new AddToSavedMessageEvent(this);
            addToSavedMessagesListener.eventOccurred(addToSavedMessageEvent);
        }

        if (logoutButton == e.getSource()) {
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (reportButton == e.getSource()) {
            ReportEvent reportEvent = new ReportEvent(this);
            reportListener.eventOccurred(reportEvent);
        }
        if (reTweetButton == e.getSource()) {
            ReTweetEvent reTweetEvent = new ReTweetEvent(this);
            reTweetListener.eventOccurred(reTweetEvent);
        }
        if (onlineModeButton == e.getSource())
            setOnline(true);
        if (offlineModeButton == e.getSource())
            setOnline(false);

    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "ShowTweetsView";
    }
}
