package ir.sharif.math.ap99_2.tinsta_client.config;


import ir.sharif.math.ap99_2.tinsta_client.config.config_model.PanelSize;

public class PanelConfig {
    private PanelSize mainPanel;
    private PanelSize tweetPanel;
    private PanelSize userProfilePanel;


    public PanelConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig panelsConfig = MainConfig.getConfig("panels");

        MainConfig mainPanelProperties = panelsConfig.getProperty(MainConfig.class,"mainPanel");
        this.mainPanel = new PanelSize(mainPanelProperties.getProperty(Integer.class, "x")
                ,mainPanelProperties.getProperty(Integer.class, "y")
                ,mainPanelProperties.getProperty(Integer.class, "width")
                ,mainPanelProperties.getProperty(Integer.class, "height"));


        MainConfig tweetPanelProperties = panelsConfig.getProperty(MainConfig.class,"tweetPanel");
        this.tweetPanel = new PanelSize(tweetPanelProperties.getProperty(Integer.class, "x")
                ,tweetPanelProperties.getProperty(Integer.class, "y")
                ,tweetPanelProperties.getProperty(Integer.class, "width")
                ,tweetPanelProperties.getProperty(Integer.class, "height"));

        MainConfig userProfilePanelProperties = panelsConfig.getProperty(MainConfig.class,"userProfilePanel");
        this.userProfilePanel = new PanelSize(userProfilePanelProperties.getProperty(Integer.class, "x")
                ,userProfilePanelProperties.getProperty(Integer.class, "y")
                ,userProfilePanelProperties.getProperty(Integer.class, "width")
                ,userProfilePanelProperties.getProperty(Integer.class, "height"));


    }

    public PanelSize getMainPanel() {
        return mainPanel;
    }


    public PanelSize getTweetPanel() {
        return tweetPanel;
    }

    public PanelSize getUserProfilePanel() {
        return userProfilePanel;
    }
}
