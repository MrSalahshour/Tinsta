package ir.sharif.math.ap99_2.tinsta_shared.enums;

public enum MainPages {
    HomePage("HomePage"),
    TimeLine("TimeLine"),
    Community("Community"),
    Explorer("Explorer");

    private final String name;

    MainPages(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
