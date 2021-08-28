package ir.sharif.math.ap99_2.tinsta_client.config;


import ir.sharif.math.ap99_2.tinsta_client.config.config_model.LabelSize;

public class LabelConfig {
    private LabelSize l100x20;
    private LabelSize l100x100;
    private LabelSize l120x20;
    private LabelSize l150x50;
    private LabelSize l150x150;
    private LabelSize l200x100;
    private LabelSize l200x20;
    private LabelSize l300x20;
    private LabelSize l70x50;
    private LabelSize l200x200;

    public LabelConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig labelsConfig = MainConfig.getConfig("labels");

        MainConfig l100x20Properties = labelsConfig.getProperty(MainConfig.class,"l100x20");
        this.l100x20 = new LabelSize(l100x20Properties.getProperty(Integer.class,"width")
                ,l100x20Properties.getProperty(Integer.class,"height"));

        MainConfig l100x100Properties = labelsConfig.getProperty(MainConfig.class,"l100x100");
        this.l100x100 = new LabelSize(l100x100Properties.getProperty(Integer.class,"width")
                ,l100x100Properties.getProperty(Integer.class,"height"));

        MainConfig l120x20Properties = labelsConfig.getProperty(MainConfig.class,"l120x20");
        this.l120x20 = new LabelSize(l120x20Properties.getProperty(Integer.class,"width")
                ,l120x20Properties.getProperty(Integer.class,"height"));

        MainConfig l150x50Properties = labelsConfig.getProperty(MainConfig.class,"l150x50");
        this.l150x50 = new LabelSize(l150x50Properties.getProperty(Integer.class,"width")
                ,l150x50Properties.getProperty(Integer.class,"height"));

        MainConfig l200x100Properties = labelsConfig.getProperty(MainConfig.class,"l200x100");
        this.l200x100 = new LabelSize(l200x100Properties.getProperty(Integer.class,"width")
                ,l200x100Properties.getProperty(Integer.class,"height"));

        MainConfig l200x20Properties = labelsConfig.getProperty(MainConfig.class,"l200x20");
        this.l200x20 = new LabelSize(l200x20Properties.getProperty(Integer.class,"width")
                ,l200x20Properties.getProperty(Integer.class,"height"));

        MainConfig l300x20Properties = labelsConfig.getProperty(MainConfig.class,"l300x20");
        this.l300x20 = new LabelSize(l300x20Properties.getProperty(Integer.class,"width")
                ,l300x20Properties.getProperty(Integer.class,"height"));

        MainConfig l70x50Properties = labelsConfig.getProperty(MainConfig.class,"l70x50");
        this.l70x50 = new LabelSize(l70x50Properties.getProperty(Integer.class,"width")
                ,l70x50Properties.getProperty(Integer.class,"height"));

        MainConfig l150x150Properties = labelsConfig.getProperty(MainConfig.class,"l150x150");
        this.l150x150 = new LabelSize(l150x150Properties.getProperty(Integer.class,"width")
                ,l150x150Properties.getProperty(Integer.class,"height"));

        MainConfig l200x200Properties = labelsConfig.getProperty(MainConfig.class,"l200x200");
        this.l200x200 = new LabelSize(l200x200Properties.getProperty(Integer.class,"width")
                ,l200x200Properties.getProperty(Integer.class,"height"));
    }

    public LabelSize getL100x20() {
        return l100x20;
    }

    public LabelSize getL100x100() {
        return l100x100;
    }

    public LabelSize getL120x20() {
        return l120x20;
    }

    public LabelSize getL150x50() {
        return l150x50;
    }

    public LabelSize getL200x100() {
        return l200x100;
    }

    public LabelSize getL200x20() {
        return l200x20;
    }

    public LabelSize getL300x20() {
        return l300x20;
    }

    public LabelSize getL70x50() {
        return l70x50;
    }

    public LabelSize getL150x150() {
        return l150x150;
    }

    public LabelSize getL200x200() {
        return l200x200;
    }
}
