package src.main.java.util;

import src.main.java.tools.Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private BigDecimal dailyCharge;
    private long chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercentage;
    private BigDecimal discountCharge;
    private BigDecimal finalCharge;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public long getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(long chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountCharge() {
        return discountCharge;
    }

    public void setDiscountCharge(BigDecimal discountCharge) {
        this.discountCharge = discountCharge;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }



    public RentalAgreement(Tool tool, int daysToRent, int discountPercentage, LocalDate startDate, HolidayList holidayList) {
        this.toolCode = tool.getCode();
        this.toolType = tool.getType();
        this.toolBrand = tool.getBrand();
        this.rentalDays = daysToRent;
        this.checkOutDate = startDate;
        this.dueDate = calculateRentalEndDate(startDate, daysToRent);
        this.dailyCharge = tool.getDailyCharge();
        this.chargeDays = tool.caculateChargeDays(startDate, dueDate, holidayList);
        this.preDiscountCharge = calculatePreDiscountCharge(tool.getDailyCharge(), chargeDays);
        this.discountPercentage = discountPercentage;
        this.discountCharge = calculateDiscountAmount(preDiscountCharge, discountPercentage);
        this.finalCharge = calculatePostDiscountCharge(preDiscountCharge, discountCharge);
    }
    private LocalDate calculateRentalEndDate(LocalDate startDate, int daysToRent) {
        return startDate.plusDays(daysToRent-1); // -1 because we want the last day of the rental
    }

    private BigDecimal calculatePreDiscountCharge(BigDecimal dailyCharge, long daysToRent) {
        return dailyCharge.multiply(new BigDecimal(daysToRent)).setScale(2, RoundingMode.HALF_UP);
    }
    private BigDecimal calculateDiscountAmount(BigDecimal preDiscountCharge, int discountPercentage) {
        if (discountPercentage > 0 && discountPercentage < 100) {
            discountCharge = preDiscountCharge.multiply(new BigDecimal(discountPercentage / 100.0)).setScale(2, RoundingMode.HALF_UP);
            return discountCharge;
        }
        // check for test case can't be greater than 100 with cli input
        else if (discountPercentage > 100){
            throw new IllegalArgumentException("Discount percentage cannot be greater than 100");
        }
        else{
            return new BigDecimal("0.00");
        }
    }

    private BigDecimal calculatePostDiscountCharge(BigDecimal preDiscountCharge, BigDecimal discountCharge) {
        BigDecimal finalCharge;
        finalCharge = preDiscountCharge.subtract(discountCharge);
        return finalCharge;
    }

    public static String currencyFormat(BigDecimal amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(amount);
    }
    @Override
    public String toString() {
        DateTimeFormatter outputDate = DateTimeFormatter.ofPattern("MM/dd/yy");
        return "Rental Agreement -------------------\n" +
                "src.main.java.tools.Tool Code: " + toolCode + "\n" +
                "src.main.java.tools.Tool Type: " + toolType + "\n" +
                "src.main.java.tools.Tool Brand: " + toolBrand  + "\n" +
                "Rental Days: " + rentalDays + "\n" +
                "src.main.java.util.Checkout Date: " + outputDate.format(checkOutDate)+ "\n" +
                "Due Date: " + outputDate.format(dueDate) + "\n" +
                "Daily Charge: " + currencyFormat(dailyCharge) + "\n" +
                "Charge Days: " + chargeDays + "\n" +
                "Pre-Discount Charge: " + currencyFormat(preDiscountCharge) + "\n" +
                "Discount Percentage: " + discountPercentage + "%" + "\n" +
                "Discount Charge: " + currencyFormat(discountCharge) + "\n" +
                "Final Charge: " + currencyFormat(finalCharge)
                ;
    }
}