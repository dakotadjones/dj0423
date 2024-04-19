package src.main.java.util;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class HolidayList {
    private List<LocalDate> holidays;

    public HolidayList(){
        this.holidays = new ArrayList<>();
    }

    public void addHoliday(LocalDate holiday){
        this.holidays.add(holiday);
    }

    public boolean isHoliday(LocalDate date){
        return this.holidays.contains(date);
    }

    public void removeHoliday(LocalDate holiday) { this.holidays.remove(holiday);}
}
