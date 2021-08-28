package ir.sharif.math.ap99_2.tinsta_shared.home_page.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.util.EventObject;

public class SelectImageForProfEvent extends HomePageEvent {
    private String imagePath;

    public SelectImageForProfEvent(Object source) {
        super(source);
    }

    public SelectImageForProfEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        HomePageEventVisitor homePageEventVisitor = (HomePageEventVisitor) eventVisitor;
        return homePageEventVisitor.selectImageProfile(this);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
