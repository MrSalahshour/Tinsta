package ir.sharif.math.ap99_2.tinsta_client.config;


import ir.sharif.math.ap99_2.tinsta_client.config.config_model.TextAreaSize;

public class TextAreaConfig {
    private TextAreaSize tA250x250;

    public TextAreaConfig() {
        setProperties();
    }

    private void setProperties(){
        MainConfig textAreasConfig = MainConfig.getConfig("textAreas");

        MainConfig tA250x250Properties = textAreasConfig.getProperty(MainConfig.class,"tA250x250");
        this.tA250x250 = new TextAreaSize(tA250x250Properties.getProperty(Integer.class,"width")
                ,tA250x250Properties.getProperty(Integer.class,"height"));
    }

    public TextAreaSize gettA250x250() {
        return tA250x250;
    }

}
