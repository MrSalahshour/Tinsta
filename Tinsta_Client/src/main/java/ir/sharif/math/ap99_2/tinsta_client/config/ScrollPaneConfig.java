package ir.sharif.math.ap99_2.tinsta_client.config;


import ir.sharif.math.ap99_2.tinsta_client.config.config_model.ScrollPaneSize;

public class ScrollPaneConfig {
    private ScrollPaneSize sP250x250;
    private ScrollPaneSize sP170x100;

    public ScrollPaneConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig scrollPanesConfig = MainConfig.getConfig("scrollPanes");

        MainConfig sP250x250Properties = scrollPanesConfig.getProperty(MainConfig.class,"sP250x250");
        this.sP250x250 = new ScrollPaneSize(sP250x250Properties.getProperty(Integer.class,"width")
                ,sP250x250Properties.getProperty(Integer.class,"height"));

        MainConfig sP170x100Properties = scrollPanesConfig.getProperty(MainConfig.class,"sP170x100");
        this.sP170x100 = new ScrollPaneSize(sP170x100Properties.getProperty(Integer.class,"width")
                ,sP170x100Properties.getProperty(Integer.class,"height"));
    }

    public ScrollPaneSize getsP250x250() {
        return sP250x250;
    }

    public ScrollPaneSize getsP170x100() {
        return sP170x100;
    }
}
