package ir.sharif.math.ap99_2.tinsta_shared.setting.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public class GetPrivacyStatusEvent extends SettingEvent {
    public GetPrivacyStatusEvent(Object source) {
        super(source);
    }

    public GetPrivacyStatusEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        SettingEventVisitor settingEventVisitor = (SettingEventVisitor) eventVisitor;
        return settingEventVisitor.getPrivacyStatus();
    }
}
