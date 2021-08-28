package ir.sharif.math.ap99_2.tinsta_client.config;



import java.io.File;

public class DirectoryConfig {
    private File messagesDirectory;
    private File tweetsDirectory;
    private File commentsDirectory;
    private File userNamesDirectory;
    private File chatroomDirectory;
    private File offlineUserDirectory;
    private File eventDirectory;

    public DirectoryConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig directoriesConfig = MainConfig.getConfig("directories");
        this.messagesDirectory = directoriesConfig.getProperty(File.class,"messagesDirectory");
        this.tweetsDirectory = directoriesConfig.getProperty(File.class,"tweetsDirectory");
        this.commentsDirectory = directoriesConfig.getProperty(File.class,"commentsDirectory");
        this.userNamesDirectory = directoriesConfig.getProperty(File.class,"userNamesDirectory");
        this.chatroomDirectory = directoriesConfig.getProperty(File.class,"chatroomDirectory");
        this.offlineUserDirectory = directoriesConfig.getProperty(File.class,"offlineUserDirectory");
        this.eventDirectory = directoriesConfig.getProperty(File.class,"eventDirectory");


    }

    public File getMessagesDirectory() {
        return messagesDirectory;
    }

    public File getTweetsDirectory() {
        return tweetsDirectory;
    }

    public File getCommentsDirectory() {
        return commentsDirectory;
    }

    public File getUserNamesDirectory() {
        return userNamesDirectory;
    }

    public File getChatroomDirectory() {
        return chatroomDirectory;
    }

    public File getOfflineUserDirectory() {
        return offlineUserDirectory;
    }

    public File getEventDirectory() {
        return eventDirectory;
    }
}
