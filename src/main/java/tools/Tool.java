package src.main.java.tools;

import src.main.java.util.HolidayList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public abstract class Tool {

    private String type;
    private String brand;
    private String code;
    private BigDecimal dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    public long caculateChargeDays(LocalDate startDate, LocalDate endDate, HolidayList holidayList) {
        long chargeDays = ChronoUnit.DAYS.between(startDate, endDate) + 1; // +1 because we want the last day of the rental
        if (!this.weekendCharge){
            // get rid of weekend changes between our two dates
            for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
                if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")) {
                    chargeDays--;
                }
            }
        }
        if (!this.holidayCharge) {
            // get rid of holiday days between our two dates not including the weekends we just removed
            for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
                if (holidayList.isHoliday(date) &&  !date.getDayOfWeek().toString().equals("SATURDAY") && !date.getDayOfWeek().toString().equals("SUNDAY")) {
                    chargeDays--;
                    holidayList.removeHoliday(date);
                }
                // account for 4th of july on sat or sun
                if (holidayList.isHoliday(date) && date.getMonth().equals(Month.JULY)){
                    chargeDays --;
                    holidayList.removeHoliday(date);
                }
            }
        }
        return chargeDays;
    }
}
