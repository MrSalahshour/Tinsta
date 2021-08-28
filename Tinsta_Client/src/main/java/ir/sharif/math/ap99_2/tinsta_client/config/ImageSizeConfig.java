package ir.sharif.math.ap99_2.tinsta_client.config;


import ir.sharif.math.ap99_2.tinsta_client.config.config_model.ImageSize;

public class ImageSizeConfig {
    private ImageSize iS100x100;
    private ImageSize iS150x150;

    public ImageSizeConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig ImageSizeConfig = MainConfig.getConfig("imagesSize");

        MainConfig iS100x100Properties = ImageSizeConfig.getProperty(MainConfig.class,"iS100x100");
        this.iS100x100 = new ImageSize(iS100x100Properties.getProperty(Integer.class,"width")
                ,iS100x100Properties.getProperty(Integer.class,"height"));

        MainConfig iS150x150Properties = ImageSizeConfig.getProperty(MainConfig.class,"iS150x150");
        this.iS150x150 = new ImageSize(iS150x150Properties.getProperty(Integer.class,"width")
                ,iS150x150Properties.getProperty(Integer.class,"height"));
    }

    public ImageSize getiS100x100() {
        return iS100x100;
    }

    public ImageSize getiS150x150() {
        return iS150x150;
    }
}
