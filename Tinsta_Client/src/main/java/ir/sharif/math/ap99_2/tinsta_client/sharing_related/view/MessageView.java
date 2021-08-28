package ir.sharif.math.ap99_2.tinsta_client.sharing_related.view;



import ir.sharif.math.ap99_2.tinsta_client.config.ColorConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.LabelConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.PanelConfig;
import ir.sharif.math.ap99_2.tinsta_client.config.ScrollPaneConfig;
import ir.sharif.math.ap99_2.tinsta_client.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MessageState;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetFileEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetFileResponse;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class MessageView extends JPanel {
    private String ownerImagePath;
    private final JLabel ownerImageLabel = new JLabel();
    private String messageImagePath;
    private final JLabel messageImageLabel = new JLabel();
    private final JTextArea messageContentTextArea =new JTextArea();
    private final JLabel publishedTimeLabel=new JLabel();
    private final JLabel ownerUserNameLabel=new JLabel();
    private final ColorConfig colorConfig = new ColorConfig();
    private final int messageId;
    private MessageState messageState;


    public MessageView(int messageId) {
        this.messageId = messageId;
        PanelConfig panelConfig = new PanelConfig();
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(panelConfig.getTweetPanel().getX(),panelConfig.getTweetPanel().getY()
                ,panelConfig.getTweetPanel().getWidth(),panelConfig.getTweetPanel().getHeight());
        this.setBackground(colorConfig.getColor5());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


    }

    private void addElements(JScrollPane scrollArea){
        this.add(ownerImageLabel);
        this.add(publishedTimeLabel);
        this.add(ownerUserNameLabel);
        this.add(scrollArea);

    }

    private void handleTweetImage(Image tweetImage){
        if (tweetImage!=null){
            tweetImage = tweetImage.getScaledInstance(150,150,Image.SCALE_DEFAULT);
            ImageIcon tweetImageIcon = new ImageIcon(tweetImage);
            messageImageLabel.setIcon(tweetImageIcon);
            LabelConfig labelConfig = new LabelConfig();
            messageImageLabel.setBounds(105,110,labelConfig.getL150x150()
                    .getWidth(),labelConfig.getL150x150().getHeight());
            this.add(messageImageLabel);
        }
    }

    public void configElements(){
        Border border = BorderFactory.createLineBorder(colorConfig.getColor5(),10);
        LabelConfig labelConfig = new LabelConfig();
        publishedTimeLabel.setFont(publishedTimeLabel.getFont().deriveFont(9.0f));
        ownerUserNameLabel.setFont(ownerUserNameLabel.getFont().deriveFont(15.0f));
        messageContentTextArea.setFont(messageContentTextArea.getFont().deriveFont(11.0f));

        messageContentTextArea.setWrapStyleWord(true);
        messageContentTextArea.setLineWrap(true);
        messageContentTextArea.setOpaque(false);
        messageContentTextArea.setEditable(false);
        messageContentTextArea.setAlignmentX(CENTER_ALIGNMENT);
        Image ownerImage = getImage(getImageFile(getOwnerImagePath()));

        publishedTimeLabel.setForeground(colorConfig.getColor0());
        ownerUserNameLabel.setForeground(colorConfig.getColor0());

        if (messageState == MessageState.ONLINE){
            messageContentTextArea.setForeground(colorConfig.getColor8());
        }
        else if (messageState == MessageState.SEND){
            messageContentTextArea.setForeground(colorConfig.getColor9());
        }
        else if (messageState == MessageState.OFFLINE){
            messageContentTextArea.setForeground(Color.red);
        }

        publishedTimeLabel.setBorder(border);
        ownerUserNameLabel.setBorder(border);
        ownerImage = ownerImage.getScaledInstance(labelConfig.getL100x100()
                .getWidth(),labelConfig.getL100x100().getHeight(),Image.SCALE_DEFAULT);


        ImageIcon ownerImageIcon = new ImageIcon(ownerImage);
        ownerImageLabel.setIcon(ownerImageIcon);

        publishedTimeLabel.setFocusable(false);
        ownerUserNameLabel.setFocusable(false);
        messageContentTextArea.setFocusable(false);

        ownerImageLabel.setBounds(0,0,labelConfig.getL100x100().getWidth(),labelConfig.getL100x100().getHeight());
        ownerUserNameLabel.setBounds(100,0,labelConfig.getL150x50()
                .getWidth(),labelConfig.getL150x50().getHeight());
        publishedTimeLabel.setBounds(100,50,labelConfig.getL150x50()
                .getWidth(),labelConfig.getL150x50().getHeight());

        ownerUserNameLabel.setVerticalAlignment(SwingConstants.TOP);
        ownerUserNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        publishedTimeLabel.setVerticalAlignment(SwingConstants.TOP);
        publishedTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Image tweetImage;

        if (messageImagePath ==null){
            tweetImage = null;
        }
        else {
           tweetImage = getImage(getImageFile(getMessageImagePath()));
        }
        handleTweetImage(tweetImage);
        JScrollPane scrollArea = new JScrollPane(messageContentTextArea);
        scrollArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollPaneConfig scrollPaneConfig = new ScrollPaneConfig();
        scrollArea.setBounds(250,0,scrollPaneConfig.getsP170x100()
                .getWidth(),scrollPaneConfig.getsP170x100().getHeight());
        addElements(scrollArea);

    }

    public String getOwnerImagePath() {
        return ownerImagePath;
    }

    public void setOwnerImagePath(String ownerImagePath) {
        this.ownerImagePath = ownerImagePath;
    }

    public String getMessageImagePath() {
        return messageImagePath;
    }

    public void setMessageImagePath(String messageImagePath) {
        this.messageImagePath = messageImagePath;
    }

    public void setMessageContentTextArea(String messageContentTextArea) {
        this.messageContentTextArea.setText(messageContentTextArea);
    }
    public void setPublishedTimeText(String publishedTimeText){
        publishedTimeLabel.setText(publishedTimeText);
    }

    public void setTweetOwnerUserNameText (String tweetOwnerUserNameText){
        ownerUserNameLabel.setText(tweetOwnerUserNameText);
    }

    public MessageState getMessageState() {
        return messageState;
    }

    public void setMessageState(MessageState messageState) {
        this.messageState = messageState;
    }

    public Image getImage(File file){
        if (file==null)
            return null;
        else {
            return ImageLoader.getImageByPath(file);
        }
    }
    public File getImageFile(String path){
        File file = new File(path);
        if (file.exists())
            return file;
        GetFileResponse getFileResponse = (GetFileResponse)
                MainController.sendEvents(new GetFileEvent(path));
        return ImageLoader.contentToFile(getFileResponse);
    }
    public int getMessageId() {
        return messageId;
    }
}
