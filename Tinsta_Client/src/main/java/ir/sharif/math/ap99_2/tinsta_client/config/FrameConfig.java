package ir.sharif.math.ap99_2.tinsta_client.config;


public class FrameConfig {
    private int width;
    private int height;
    private boolean resizable;
    private boolean undecorated;
    private String title;

    public FrameConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig frameConfig = MainConfig.getConfig("frame");
        this.width = frameConfig.getProperty(Integer.class, "width");
        this.height = frameConfig.getProperty(Integer.class, "height");
        this.resizable = frameConfig.getProperty(Boolean.class, "resizable");
        this.undecorated = frameConfig.getProperty(Boolean.class, "undecorated");
        this.title = frameConfig.getProperty(String.class, "title");
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isUndecorated() {
        return undecorated;
    }

    public void setUndecorated(boolean undecorated) {
        this.undecorated = undecorated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
