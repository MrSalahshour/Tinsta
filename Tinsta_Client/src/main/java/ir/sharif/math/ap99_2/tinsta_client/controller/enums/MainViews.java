package ir.sharif.math.ap99_2.tinsta_client.controller.enums;



public enum MainViews {
    CommunicationMenuView("CommunicationMenuView"),
    TimeLineView("TimeLineView"),
    ExplorerView("ExplorerView"),
    HomePageView("HomePageView"),
    ChatRoomView("ChatRoomView");

    public final String name;

    MainViews(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
