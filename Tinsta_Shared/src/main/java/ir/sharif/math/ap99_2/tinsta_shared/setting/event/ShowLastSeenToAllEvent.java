package ir.sharif.math.ap99_2.tinsta_shared.setting.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class ShowLastSeenToAllEvent extends SettingEvent {

    public ShowLastSeenToAllEvent(Object source) {
        super(source);
    }

    public ShowLastSeenToAllEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        SettingEventVisitor settingEventVisitor = (SettingEventVisitor) eventVisitor;
        return settingEventVisitor.showLastSeenToAll();
    }
}
