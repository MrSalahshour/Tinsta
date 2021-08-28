package ir.sharif.math.ap99_2.tinsta_server.config;



import java.io.File;

public class DirectoryConfig {
    private File usersDirectory;
    private File logDirectory;
    private File messagesDirectory;
    private File tweetsDirectory;
    private File commentsDirectory;
    private File likesDirectory;
    private File emailsDirectory;
    private File phoneNumbersDirectory;
    private File userNamesDirectory;

    public DirectoryConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig directoriesConfig = MainConfig.getConfig("directories");
        this.usersDirectory = directoriesConfig.getProperty(File.class,"usersDirectory");
        this.logDirectory = directoriesConfig.getProperty(File.class,"logDirectory");
        this.messagesDirectory = directoriesConfig.getProperty(File.class,"messagesDirectory");
        this.tweetsDirectory = directoriesConfig.getProperty(File.class,"tweetsDirectory");
        this.commentsDirectory = directoriesConfig.getProperty(File.class,"commentsDirectory");
        this.likesDirectory = directoriesConfig.getProperty(File.class,"likesDirectory");
        this.emailsDirectory = directoriesConfig.getProperty(File.class,"emailsDirectory");
        this.phoneNumbersDirectory = directoriesConfig.getProperty(File.class,"phoneNumbersDirectory");
        this.userNamesDirectory = directoriesConfig.getProperty(File.class,"userNamesDirectory");

    }

    public File getUsersDirectory() {
        return usersDirectory;
    }

    public File getLogDirectory() {
        return logDirectory;
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

    public File getLikesDirectory() {
        return likesDirectory;
    }

    public File getEmailsDirectory() {
        return emailsDirectory;
    }

    public File getPhoneNumbersDirectory() {
        return phoneNumbersDirectory;
    }

    public File getUserNamesDirectory() {
        return userNamesDirectory;
    }

}
