package ir.sharif.math.ap99_2.tinsta_shared.setting.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;



public class ChangePasswordEvent extends SettingEvent {
    private String currentPassword;
    private String newPassword;

    public ChangePasswordEvent(Object source, String currentPassword, String newPassword) {
        super(source);
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public ChangePasswordEvent() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    @Override
    public Response visit(EventVisitor eventVisitor) {
        SettingEventVisitor settingEventVisitor = (SettingEventVisitor) eventVisitor;
        return settingEventVisitor.changePassword(this);
    }
}
