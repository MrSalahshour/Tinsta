package ir.sharif.math.ap99_2.tinsta_client.communication_related.view;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.listener.*;
import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.GoToSearchUserListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToHomePageListener;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.*;
import ir.sharif.math.ap99_2.tinsta_shared.explorer.event.GoToSearchUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.BackToLoginEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToHomePageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.validation.exception.UserFolderNotExist;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommunicationMenuView extends JPanel implements ActionListener {
    private final JPanel source;
    private final JButton showFollowingsButton=new JButton("Show Followings");
    private final JButton showFollowersButton=new JButton("Show Followers");
    private final JButton showMutedListButton=new JButton("Show MutedList");
    private final JButton showBlackListButton=new JButton("Show BlackList");
    private final JButton showFoldersListButton=new JButton("Show Folders List");
    private final JButton searchUserInYourListsButton=new JButton("Search User in Your Lists");
    private final JButton createNewFolderButton=new JButton("Create New Folder");
    private final JButton addUserToFolderButton=new JButton("Add User To Folder");
    private final JButton showAFolderContentButton=new JButton("Show A Folder Content");
    private final JButton removeUserFromFolderButton=new JButton("Remove User From Folder");
    private final JButton logoutButton=new JButton("Logout");
    private final JButton backToHomePageButton=new JButton("Back To HomePage");
    private final ColorConfig colorConfig = new ColorConfig();
    private ShowFollowingsListener showFollowingsListener;
    private ShowFollowersListener showFollowersListener;
    private ShowMutedListListener showMutedListListener;
    private ShowBlackListsListener showBlackListsListener;
    private ShowFoldersListListener showFoldersListListener;
    private GoToSearchUserListener goToSearchUserListener;
    private GoToCreateNewFolderListener goToCreateNewFolderListener;
    private GoToAddUserToFolderListener goToAddUserToFolderListener;
    private ShowAFolderContentListener showAFolderContentListener;
    private GoToRemoveUserFromFolderListener goToRemoveUserFromFolderListener;
    private BackToLoginListener backToLoginListener;
    private GoToHomePageListener goToHomePageListener;



    public CommunicationMenuView(JPanel source) {
        this.source = source;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        GridLayout gridLayout = new GridLayout(4,3);
        this.setBorder(border);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        this.setLayout(gridLayout);
        this.setBounds(panelConfig.getMainPanel().getX(),panelConfig.getMainPanel().getY()
                ,panelConfig.getMainPanel().getWidth(),panelConfig.getMainPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        configButtons();
        addButtons();
    }
    private void addButtons(){
        this.add(showFollowingsButton);
        this.add(showFollowersButton);
        this.add(showMutedListButton);
        this.add(showBlackListButton);
        this.add(showFoldersListButton);
        this.add(createNewFolderButton);
        this.add(showAFolderContentButton);
        this.add(addUserToFolderButton);
        this.add(removeUserFromFolderButton);
        this.add(searchUserInYourListsButton);
        this.add(logoutButton);
        this.add(backToHomePageButton);
    }

    private void configButtons(){
        showFollowingsButton.setFont(showFollowingsButton.getFont().deriveFont(15.0f));
        showFollowersButton.setFont(showFollowersButton.getFont().deriveFont(15.0f));
        showMutedListButton.setFont(showMutedListButton.getFont().deriveFont(15.0f));
        showBlackListButton.setFont(showBlackListButton.getFont().deriveFont(15.0f));
        showFoldersListButton.setFont(showFoldersListButton.getFont().deriveFont(15.0f));
        searchUserInYourListsButton.setFont(searchUserInYourListsButton.getFont().deriveFont(15.0f));
        createNewFolderButton.setFont(createNewFolderButton.getFont().deriveFont(15.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(15.0f));
        addUserToFolderButton.setFont(addUserToFolderButton.getFont().deriveFont(15.0f));
        backToHomePageButton.setFont(backToHomePageButton.getFont().deriveFont(15.0f));
        showAFolderContentButton.setFont(showAFolderContentButton.getFont().deriveFont(15.0f));
        removeUserFromFolderButton.setFont(removeUserFromFolderButton.getFont().deriveFont(15.0f));


        showFollowingsButton.setBackground(colorConfig.getColor3());
        showFollowersButton.setBackground(colorConfig.getColor3());
        showMutedListButton.setBackground(colorConfig.getColor3());
        showBlackListButton.setBackground(colorConfig.getColor3());
        showFoldersListButton.setBackground(colorConfig.getColor3());
        searchUserInYourListsButton.setBackground(colorConfig.getColor3());
        createNewFolderButton.setBackground(colorConfig.getColor3());
        addUserToFolderButton.setBackground(colorConfig.getColor3());
        showAFolderContentButton.setBackground(colorConfig.getColor3());
        removeUserFromFolderButton.setBackground(colorConfig.getColor3());
        logoutButton.setBackground(colorConfig.getColor3());
        backToHomePageButton.setBackground(colorConfig.getColor3());


        showFollowingsButton.setForeground(colorConfig.getColor0());
        showFollowersButton.setForeground(colorConfig.getColor0());
        showMutedListButton.setForeground(colorConfig.getColor0());
        showBlackListButton.setForeground(colorConfig.getColor0());
        showFoldersListButton.setForeground(colorConfig.getColor0());
        searchUserInYourListsButton.setForeground(colorConfig.getColor0());
        createNewFolderButton.setForeground(colorConfig.getColor0());
        addUserToFolderButton.setForeground(colorConfig.getColor0());
        showAFolderContentButton.setForeground(colorConfig.getColor0());
        removeUserFromFolderButton.setForeground(colorConfig.getColor0());
        logoutButton.setForeground(colorConfig.getColor0());
        backToHomePageButton.setForeground(colorConfig.getColor0());


        showFollowingsButton.setFocusable(false);
        showFollowersButton.setFocusable(false);
        showMutedListButton.setFocusable(false);
        showBlackListButton.setFocusable(false);
        showFoldersListButton.setFocusable(false);
        searchUserInYourListsButton.setFocusable(false);
        createNewFolderButton.setFocusable(false);
        addUserToFolderButton.setFocusable(false);
        showAFolderContentButton.setFocusable(false);
        removeUserFromFolderButton.setFocusable(false);
        logoutButton.setFocusable(false);
        backToHomePageButton.setFocusable(false);


        showFollowingsButton.setBorder(BorderFactory.createEmptyBorder());
        showFollowersButton.setBorder(BorderFactory.createEmptyBorder());
        showMutedListButton.setBorder(BorderFactory.createEmptyBorder());
        showBlackListButton.setBorder(BorderFactory.createEmptyBorder());
        showFoldersListButton.setBorder(BorderFactory.createEmptyBorder());
        searchUserInYourListsButton.setBorder(BorderFactory.createEmptyBorder());
        createNewFolderButton.setBorder(BorderFactory.createEmptyBorder());
        addUserToFolderButton.setBorder(BorderFactory.createEmptyBorder());
        showAFolderContentButton.setBorder(BorderFactory.createEmptyBorder());
        removeUserFromFolderButton.setBorder(BorderFactory.createEmptyBorder());
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        backToHomePageButton.setBorder(BorderFactory.createEmptyBorder());

        showFollowingsButton.addActionListener(this);
        showFollowersButton.addActionListener(this);
        showMutedListButton.addActionListener(this);
        showBlackListButton.addActionListener(this);
        showFoldersListButton.addActionListener(this);
        searchUserInYourListsButton.addActionListener(this);
        createNewFolderButton.addActionListener(this);
        addUserToFolderButton.addActionListener(this);
        showAFolderContentButton.addActionListener(this);
        removeUserFromFolderButton.addActionListener(this);
        logoutButton.addActionListener(this);
        backToHomePageButton.addActionListener(this);
    }

    public JPanel getSource() {
        return source;
    }

    public void setShowFollowingsListener(ShowFollowingsListener showFollowingsListener) {
        this.showFollowingsListener = showFollowingsListener;
    }

    public void setShowFollowersListener(ShowFollowersListener showFollowersListener) {
        this.showFollowersListener = showFollowersListener;
    }

    public void setShowMutedListListener(ShowMutedListListener showMutedListListener) {
        this.showMutedListListener = showMutedListListener;
    }

    public void setShowBlackListsListener(ShowBlackListsListener showBlackListsListener) {
        this.showBlackListsListener = showBlackListsListener;
    }

    public void setShowFoldersListListener(ShowFoldersListListener showFoldersListListener) {
        this.showFoldersListListener = showFoldersListListener;
    }

    public void setGoToSearchUserListener(GoToSearchUserListener goToSearchUserListener) {
        this.goToSearchUserListener = goToSearchUserListener;
    }

    public void setGoToCreateNewFolderListener(GoToCreateNewFolderListener goToCreateNewFolderListener) {
        this.goToCreateNewFolderListener = goToCreateNewFolderListener;
    }

    public void setGoToAddUserToFolderListener(GoToAddUserToFolderListener goToAddUserToFolderListener) {
        this.goToAddUserToFolderListener = goToAddUserToFolderListener;
    }

    public void setShowAFolderContentListener(ShowAFolderContentListener showAFolderContentListener) {
        this.showAFolderContentListener = showAFolderContentListener;
    }

    public void setGoToRemoveUserFromFolderListener(GoToRemoveUserFromFolderListener goToRemoveUserFromFolderListener) {
        this.goToRemoveUserFromFolderListener = goToRemoveUserFromFolderListener;
    }

    public void setBackToLoginListener(BackToLoginListener backToLoginListener) {
        this.backToLoginListener = backToLoginListener;
    }

    public void setGoToHomePageListener(GoToHomePageListener goToHomePageListener) {
        this.goToHomePageListener = goToHomePageListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (showFollowingsButton == e.getSource()){
            ShowFollowingsEvent showFollowingsEvent = new ShowFollowingsEvent(source);
            showFollowingsListener.eventOccurred(showFollowingsEvent);
        }
        if (showFollowersButton == e.getSource()){
            ShowFollowersEvent showFollowersEvent = new ShowFollowersEvent(source);
            showFollowersListener.eventOccurred(showFollowersEvent);
        }
        if (showMutedListButton == e.getSource()){
            ShowMutedListEvent showMutedListEvent = new ShowMutedListEvent(source);
            showMutedListListener.eventOccurred(showMutedListEvent);
        }
        if (showBlackListButton == e.getSource()){
            ShowBlackListsEvent showBlackListsEvent = new ShowBlackListsEvent(source);
            showBlackListsListener.eventOccurred(showBlackListsEvent);
        }
        if (showFoldersListButton == e.getSource()){
            ShowFoldersListEvent showFoldersListEvent = new ShowFoldersListEvent(source);
            showFoldersListListener.eventOccurred(showFoldersListEvent);
        }
        if (searchUserInYourListsButton == e.getSource()){
            GoToSearchUserEvent goToSearchUserEvent = new GoToSearchUserEvent(this);
            goToSearchUserListener.eventOccurred(goToSearchUserEvent);
        }
        if (createNewFolderButton == e.getSource()){
            GoToCreateNewFolderEvent goToCreateNewFolderEvent = new GoToCreateNewFolderEvent(source);
            goToCreateNewFolderListener.eventOccurred(goToCreateNewFolderEvent);

        }
        if (addUserToFolderButton == e.getSource()){
            GoToAddUserToFolderEvent goToAddUserToFolderEvent = new GoToAddUserToFolderEvent(source);
            goToAddUserToFolderListener.eventOccurred(goToAddUserToFolderEvent);
        }
        if (removeUserFromFolderButton == e.getSource()){
            GoToRemoveUserFromFolderEvent goToRemoveUserFromFolderEvent = new GoToRemoveUserFromFolderEvent(source);
            goToRemoveUserFromFolderListener.eventOccurred(goToRemoveUserFromFolderEvent);
        }
        if (showAFolderContentButton == e.getSource()){
            ShowAFolderContentEvent showAFolderContentEvent = new ShowAFolderContentEvent(source);
            try {
                showAFolderContentListener.eventOccurred(showAFolderContentEvent);
            } catch (UserFolderNotExist userFolderNotExist) {
                String message = userFolderNotExist.getMessage();
                JOptionPane.showMessageDialog(source, message,
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }
        if (logoutButton == e.getSource()){
            BackToLoginEvent backToLoginEvent = new BackToLoginEvent(source);
            backToLoginListener.eventOccurred(backToLoginEvent);
        }
        if (backToHomePageButton == e.getSource()){
            GoToHomePageEvent goToHomePageEvent = new GoToHomePageEvent(source);
            goToHomePageListener.eventOccurred(goToHomePageEvent);
        }
    }

    @Override
    public String toString() {
        return "CommunicationMenuView";
    }
}
