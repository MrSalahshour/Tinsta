package ir.sharif.math.ap99_2.tinsta_shared.profile.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.util.EventObject;

public class ChangeBirthdayEvent extends ProfileEvent {
    private String day;
    private String month;
    private String year;
    public ChangeBirthdayEvent(Object source, String day, String month, String year) {
        super(source);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public ChangeBirthdayEvent() {
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }


    public String getYear() {
        return year;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        ProfileEventVisitor profileEventVisitor = (ProfileEventVisitor) eventVisitor;
        return profileEventVisitor.changeBirthday(this);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
