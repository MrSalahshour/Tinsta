package ir.sharif.math.ap99_2.tinsta_shared.user_info.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.validation.model.Validation;

import java.io.Serializable;
import java.time.DateTimeException;

@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Birthday.class)
public class Birthday implements Serializable {
    private boolean privacy;
    private String Year;
    private String Month;
    private String Day;



    public Birthday(String year, String month, String day) throws DateTimeException {
        if (Validation.isValidAge(year, month, day)) {
            Year = year;
            Month = month;
            Day = day;
            this.privacy = true;

        }
    }

    public Birthday() {
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public String toString() {
        return Year + "-" + Month + "-" + Day;
    }

    public void showBirthday() {
        if (!privacy)
            System.out.println(this.toString());
    }
}
