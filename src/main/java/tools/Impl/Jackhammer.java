package src.main.java.tools.Impl;

import src.main.java.tools.Tool;

import java.math.BigDecimal;

public class Jackhammer extends Tool {

    public Jackhammer(String code, String brand) {
        this.setCode(code);
        this.setType(this.getClass().getSimpleName());
        this.setBrand(brand);
        BigDecimal dailyCharge = new BigDecimal("2.99");
        this.setDailyCharge(dailyCharge);
        this.setWeekdayCharge(true);
        this.setWeekendCharge(false);
        this.setHolidayCharge(false);
    }
}
