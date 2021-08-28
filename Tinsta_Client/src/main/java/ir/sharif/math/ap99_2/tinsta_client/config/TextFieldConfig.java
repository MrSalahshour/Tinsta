package ir.sharif.math.ap99_2.tinsta_client.config;


import ir.sharif.math.ap99_2.tinsta_client.config.config_model.TextFieldSize;

public class TextFieldConfig {
    private TextFieldSize tF200x20;
    private TextFieldSize tF250x50;
    private TextFieldSize tF300x50;
    private TextFieldSize tF50x20;
    private TextFieldSize tF200x25;

    public TextFieldConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig textFieldsConfig = MainConfig.getConfig("textFields");

        MainConfig tF200x20Properties = textFieldsConfig.getProperty(MainConfig.class,"tF200x20");
        this.tF200x20 = new TextFieldSize(tF200x20Properties.getProperty(Integer.class,"width")
                ,tF200x20Properties.getProperty(Integer.class,"height"));

        MainConfig tF250x50Properties = textFieldsConfig.getProperty(MainConfig.class,"tF250x50");
        this.tF250x50 = new TextFieldSize(tF250x50Properties.getProperty(Integer.class,"width")
                ,tF250x50Properties.getProperty(Integer.class,"height"));

        MainConfig tF300x50Properties = textFieldsConfig.getProperty(MainConfig.class,"tF300x50");
        this.tF300x50 = new TextFieldSize(tF300x50Properties.getProperty(Integer.class,"width")
                ,tF300x50Properties.getProperty(Integer.class,"height"));

        MainConfig tF50x20Properties = textFieldsConfig.getProperty(MainConfig.class,"tF50x20");
        this.tF50x20 = new TextFieldSize(tF50x20Properties.getProperty(Integer.class,"width")
                ,tF50x20Properties.getProperty(Integer.class,"height"));

        MainConfig tF200x25Properties = textFieldsConfig.getProperty(MainConfig.class,"tF200x25");
        this.tF200x25 = new TextFieldSize(tF200x25Properties.getProperty(Integer.class,"width")
                ,tF200x25Properties.getProperty(Integer.class,"height"));
    }

    public TextFieldSize gettF200x20() {
        return tF200x20;
    }

    public TextFieldSize gettF250x50() {
        return tF250x50;
    }

    public TextFieldSize gettF300x50() {
        return tF300x50;
    }

    public TextFieldSize gettF50x20() {
        return tF50x20;
    }

    public TextFieldSize gettF200x25() {
        return tF200x25;
    }
}
