package ir.sharif.math.ap99_2.tinsta_client.config;


import ir.sharif.math.ap99_2.tinsta_client.config.config_model.ButtonSize;

public class ButtonConfig {
    private ButtonSize b120x25;
    private ButtonSize b200x50;
    private ButtonSize b200x100;
    private ButtonSize b200x200;
    private ButtonSize b250x125;
    private ButtonSize b300x200;
    private ButtonSize b350x200;
    private ButtonSize b400x50;
    private ButtonSize b400x75;
    private ButtonSize b400x300;

    public ButtonConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig buttonsConfig = MainConfig.getConfig("buttons");

        MainConfig b120x25Properties = buttonsConfig.getProperty(MainConfig.class,"b120x25");
        this.b120x25 = new ButtonSize(b120x25Properties.getProperty(Integer.class,"width")
                ,b120x25Properties.getProperty(Integer.class,"height"));

        MainConfig b200x50Properties = buttonsConfig.getProperty(MainConfig.class,"b200x50");
        this.b200x50 = new ButtonSize(b200x50Properties.getProperty(Integer.class,"width")
                ,b200x50Properties.getProperty(Integer.class,"height"));

        MainConfig b200x200Properties = buttonsConfig.getProperty(MainConfig.class,"b200x200");
        this.b200x200 = new ButtonSize(b200x200Properties.getProperty(Integer.class,"width")
                ,b200x200Properties.getProperty(Integer.class,"height"));

        MainConfig b250x125Properties = buttonsConfig.getProperty(MainConfig.class,"b250x125");
        this.b250x125 = new ButtonSize(b250x125Properties.getProperty(Integer.class,"width")
                ,b250x125Properties.getProperty(Integer.class,"height"));

        MainConfig b300x200Properties = buttonsConfig.getProperty(MainConfig.class,"b300x200");
        this.b300x200 = new ButtonSize(b300x200Properties.getProperty(Integer.class,"width")
                ,b300x200Properties.getProperty(Integer.class,"height"));

        MainConfig b350x200Properties = buttonsConfig.getProperty(MainConfig.class,"b350x200");
        this.b350x200 = new ButtonSize(b350x200Properties.getProperty(Integer.class,"width")
                ,b350x200Properties.getProperty(Integer.class,"height"));

        MainConfig b400x50Properties = buttonsConfig.getProperty(MainConfig.class,"b400x50");
        this.b400x50 = new ButtonSize(b400x50Properties.getProperty(Integer.class,"width")
                ,b400x50Properties.getProperty(Integer.class,"height"));

        MainConfig b400x75Properties = buttonsConfig.getProperty(MainConfig.class,"b400x75");
        this.b400x75 = new ButtonSize(b400x75Properties.getProperty(Integer.class,"width")
                ,b400x75Properties.getProperty(Integer.class,"height"));

        MainConfig b200x100Properties = buttonsConfig.getProperty(MainConfig.class,"b200x100");
        this.b200x100 = new ButtonSize(b200x100Properties.getProperty(Integer.class,"width")
                ,b200x100Properties.getProperty(Integer.class,"height"));

        MainConfig b400x300Properties = buttonsConfig.getProperty(MainConfig.class,"b400x300");
        this.b400x300 = new ButtonSize(b400x300Properties.getProperty(Integer.class,"width")
                ,b400x300Properties.getProperty(Integer.class,"height"));


    }

    public ButtonSize getB120x25() {
        return b120x25;
    }

    public ButtonSize getB200x50() {
        return b200x50;
    }

    public ButtonSize getB200x200() {
        return b200x200;
    }

    public ButtonSize getB250x125() {
        return b250x125;
    }

    public ButtonSize getB300x200() {
        return b300x200;
    }

    public ButtonSize getB350x200() {
        return b350x200;
    }

    public ButtonSize getB400x50() {
        return b400x50;
    }

    public ButtonSize getB400x75() {
        return b400x75;
    }

    public ButtonSize getB200x100() {
        return b200x100;
    }

    public ButtonSize getB400x300() {
        return b400x300;
    }
}
