package src.main.java.tools.Impl;

import src.main.java.tools.Tool;

import java.math.BigDecimal;

public class Ladder extends Tool {

    public Ladder(String code, String brand) {
        this.setCode(code);
        this.setType(this.getClass().getSimpleName());
        this.setBrand(brand);
        BigDecimal dailyCharge = new BigDecimal("1.99");
        this.setDailyCharge(dailyCharge);
        this.setWeekdayCharge(true);
        this.setWeekendCharge(true);
        this.setHolidayCharge(false);
    }
}
