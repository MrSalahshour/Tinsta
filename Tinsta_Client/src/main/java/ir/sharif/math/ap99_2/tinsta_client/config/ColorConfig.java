package ir.sharif.math.ap99_2.tinsta_client.config;

import java.awt.*;


public class ColorConfig {
    private Color color0;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;
    private Color color5;
    private Color color6;
    private Color color7;
    private Color color8;
    private Color color9;
    private Color color10;
    private Color color11;
    private Color color12;

    public ColorConfig() {
        setProperties();
    }

    private void setProperties(){

        MainConfig colorsConfig = MainConfig.getConfig("colors");

        MainConfig color0Properties = colorsConfig.getProperty(MainConfig.class,"color0");
        this.color0 = new Color(color0Properties.getProperty(Integer.class,"red")
                ,color0Properties.getProperty(Integer.class,"green")
                ,color0Properties.getProperty(Integer.class,"blue"));

        MainConfig color1Properties = colorsConfig.getProperty(MainConfig.class,"color1");
        this.color1 = new Color(color1Properties.getProperty(Integer.class,"red")
                ,color1Properties.getProperty(Integer.class,"green")
                ,color1Properties.getProperty(Integer.class,"blue"));

        MainConfig color2Properties = colorsConfig.getProperty(MainConfig.class,"color2");
        this.color2 = new Color(color1Properties.getProperty(Integer.class,"red")
                ,color2Properties.getProperty(Integer.class,"green")
                ,color2Properties.getProperty(Integer.class,"blue"));

        MainConfig color3Properties = colorsConfig.getProperty(MainConfig.class,"color3");
        this.color3 = new Color(color1Properties.getProperty(Integer.class,"red")
                ,color3Properties.getProperty(Integer.class,"green")
                ,color3Properties.getProperty(Integer.class,"blue"));

        MainConfig color4Properties = colorsConfig.getProperty(MainConfig.class,"color4");
        this.color4 = new Color(color1Properties.getProperty(Integer.class,"red")
                ,color4Properties.getProperty(Integer.class,"green")
                ,color4Properties.getProperty(Integer.class,"blue"));

        MainConfig color5Properties = colorsConfig.getProperty(MainConfig.class,"color5");
        this.color5 = new Color(color5Properties.getProperty(Integer.class,"red")
                ,color5Properties.getProperty(Integer.class,"green")
                ,color5Properties.getProperty(Integer.class,"blue"));

        MainConfig color6Properties = colorsConfig.getProperty(MainConfig.class,"color6");
        this.color6 = new Color(color6Properties.getProperty(Integer.class,"red")
                ,color6Properties.getProperty(Integer.class,"green")
                ,color6Properties.getProperty(Integer.class,"blue"));

        MainConfig color7Properties = colorsConfig.getProperty(MainConfig.class,"color7");
        this.color7 = new Color(color7Properties.getProperty(Integer.class,"red")
                ,color7Properties.getProperty(Integer.class,"green")
                ,color7Properties.getProperty(Integer.class,"blue"));

        MainConfig color8Properties = colorsConfig.getProperty(MainConfig.class,"color8");
        this.color8 = new Color(color8Properties.getProperty(Integer.class,"red")
                ,color8Properties.getProperty(Integer.class,"green")
                ,color8Properties.getProperty(Integer.class,"blue"));

        MainConfig color9Properties = colorsConfig.getProperty(MainConfig.class,"color9");
        this.color9 = new Color(color9Properties.getProperty(Integer.class,"red")
                ,color9Properties.getProperty(Integer.class,"green")
                ,color9Properties.getProperty(Integer.class,"blue"));

        MainConfig color10Properties = colorsConfig.getProperty(MainConfig.class,"color10");
        this.color10 = new Color(color10Properties.getProperty(Integer.class,"red")
                ,color10Properties.getProperty(Integer.class,"green")
                ,color10Properties.getProperty(Integer.class,"blue"));

        MainConfig color11Properties = colorsConfig.getProperty(MainConfig.class,"color11");
        this.color11 = new Color(color11Properties.getProperty(Integer.class,"red")
                ,color11Properties.getProperty(Integer.class,"green")
                ,color11Properties.getProperty(Integer.class,"blue"));

        MainConfig color12Properties = colorsConfig.getProperty(MainConfig.class,"color12");
        this.color12 = new Color(color12Properties.getProperty(Integer.class,"red")
                ,color12Properties.getProperty(Integer.class,"green")
                ,color12Properties.getProperty(Integer.class,"blue"));

    }

    public Color getColor0() {
        return color0;
    }


    public Color getColor1() {
        return color1;
    }


    public Color getColor2() {
        return color2;
    }


    public Color getColor3() {
        return color3;
    }


    public Color getColor4() {
        return color4;
    }


    public Color getColor5() {
        return color5;
    }


    public Color getColor6() {
        return color6;
    }


    public Color getColor7() {
        return color7;
    }


    public Color getColor8() {
        return color8;
    }


    public Color getColor9() {
        return color9;
    }


    public Color getColor10() {
        return color10;
    }


    public Color getColor11() {
        return color11;
    }


    public Color getColor12() {
        return color12;
    }

}
