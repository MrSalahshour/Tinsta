package ir.sharif.math.ap99_2.tinsta_shared.setting.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;


public class DeleteAccountEvent extends SettingEvent {
    private String password;
    public DeleteAccountEvent(Object source) {
        super(source);
    }

    public DeleteAccountEvent() {
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        SettingEventVisitor settingEventVisitor = (SettingEventVisitor) eventVisitor;
        return settingEventVisitor.deleteAccount(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
