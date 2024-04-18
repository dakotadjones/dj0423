package src.test.java;
import org.junit.jupiter.api.Test;
import src.main.java.tools.Tool;
import src.main.java.tools.ToolFactory;
import src.main.java.util.Checkout;
import src.main.java.util.HolidayList;
import src.main.java.util.RentalAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class RentalAgreementTest {

    @Test
    public void testRentalAgreement1() {
        // discount percentage greater than 100
        // handled in cli input before getting to rental agreement
        Tool tool = ToolFactory.createTool("JAKR");
        int daysToRent = 5;
        int discountPercentage = 101;
        LocalDate startDate = LocalDate.of(2015, 9, 3);

        Checkout checkout = new Checkout();
        HolidayList holidays = checkout.getHolidays(startDate);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RentalAgreement(tool, daysToRent, discountPercentage, startDate, holidays));
        assertEquals("Discount percentage cannot be greater than 100", exception.getMessage());


    }
    @Test
    public void testRentalAgreement2() {

        Tool tool = ToolFactory.createTool("LADW");
        int daysToRent = 3;
        int discountPercentage = 10;
        LocalDate startDate = LocalDate.of(2020, 7, 2);
        Checkout checkout = new Checkout();
        HolidayList holidays = checkout.getHolidays(startDate);
        RentalAgreement rentalAgreement = new RentalAgreement(tool, daysToRent, discountPercentage, startDate, holidays);

        assertEquals(rentalAgreement.getToolCode(), "LADW");
        assertEquals(rentalAgreement.getToolType(), "Ladder");
        assertEquals(rentalAgreement.getToolBrand(), "werner");
        assertEquals(rentalAgreement.getRentalDays(), 3);
        assertEquals(rentalAgreement.getCheckOutDate(), LocalDate.of(2020, 7, 2));
        assertEquals(rentalAgreement.getDueDate(), LocalDate.of(2020, 7, 4));
        assertEquals(rentalAgreement.getDailyCharge(), new BigDecimal("1.99"));
        assertEquals(rentalAgreement.getChargeDays(), 2);
        assertEquals(rentalAgreement.getPreDiscountCharge(), new BigDecimal("3.98"));
        assertEquals(rentalAgreement.getDiscountPercentage(), 10);
        assertEquals(rentalAgreement.getDiscountCharge(), new BigDecimal("0.40"));
        assertEquals(rentalAgreement.getFinalCharge(), new BigDecimal("3.58"));

    }
    @Test
    public void testRentalAgreement3() {
        Tool tool = ToolFactory.createTool("CHNS");
        int daysToRent = 5;
        int discountPercentage = 25;
        LocalDate startDate = LocalDate.of(2015, 7, 2);
        Checkout checkout = new Checkout();
        HolidayList holidays = checkout.getHolidays(startDate);
        RentalAgreement rentalAgreement = new RentalAgreement(tool, daysToRent, discountPercentage, startDate, holidays);

        assertEquals(rentalAgreement.getToolCode(), "CHNS");
        assertEquals(rentalAgreement.getToolType(), "Chainsaw");
        assertEquals(rentalAgreement.getToolBrand(), "stihl");
        assertEquals(rentalAgreement.getRentalDays(), 5);
        assertEquals(rentalAgreement.getCheckOutDate(), LocalDate.of(2015, 7, 2));
        assertEquals(rentalAgreement.getDueDate(), LocalDate.of(2015, 7, 6));
        assertEquals(rentalAgreement.getDailyCharge(), new BigDecimal("1.49"));
        assertEquals(rentalAgreement.getChargeDays(), 3);
        assertEquals(rentalAgreement.getPreDiscountCharge(), new BigDecimal("4.47"));
        assertEquals(rentalAgreement.getDiscountPercentage(), 25);
        assertEquals(rentalAgreement.getDiscountCharge(), new BigDecimal("1.12"));
        assertEquals(rentalAgreement.getFinalCharge(), new BigDecimal("3.35"));
    }
    @Test
    public void testRentalAgreement4() {
        Tool tool = ToolFactory.createTool("JAKD");
        int daysToRent = 6;
        int discountPercentage = 0;
        LocalDate startDate = LocalDate.of(2015, 9, 3);
        Checkout checkout = new Checkout();
        HolidayList holidays = checkout.getHolidays(startDate);
        RentalAgreement rentalAgreement = new RentalAgreement(tool, daysToRent, discountPercentage, startDate, holidays);

        assertEquals(rentalAgreement.getToolCode(), "JAKD");
        assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        assertEquals(rentalAgreement.getToolBrand(), "dewalt");
        assertEquals(rentalAgreement.getRentalDays(), 6);
        assertEquals(rentalAgreement.getCheckOutDate(), LocalDate.of(2015, 9, 3));
        assertEquals(rentalAgreement.getDueDate(), LocalDate.of(2015, 9, 8));
        assertEquals(rentalAgreement.getDailyCharge(), new BigDecimal("2.99"));
        assertEquals(rentalAgreement.getChargeDays(), 3);
        assertEquals(rentalAgreement.getPreDiscountCharge(), new BigDecimal("8.97"));
        assertEquals(rentalAgreement.getDiscountPercentage(), 0);
        assertEquals(rentalAgreement.getDiscountCharge(), new BigDecimal("0.00"));
        assertEquals(rentalAgreement.getFinalCharge(), new BigDecimal("8.97"));
    }
    @Test
    public void testRentalAgreement5() {
        Tool tool = ToolFactory.createTool("JAKD");
        int daysToRent = 9;
        int discountPercentage = 0;
        LocalDate startDate = LocalDate.of(2015, 7, 2);
        Checkout checkout = new Checkout();
        HolidayList holidays = checkout.getHolidays(startDate);
        RentalAgreement rentalAgreement = new RentalAgreement(tool, daysToRent, discountPercentage, startDate, holidays);

        assertEquals(rentalAgreement.getToolCode(), "JAKD");
        assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        assertEquals(rentalAgreement.getToolBrand(), "dewalt");
        assertEquals(rentalAgreement.getRentalDays(), 9);
        assertEquals(rentalAgreement.getCheckOutDate(), LocalDate.of(2015, 7, 2));
        assertEquals(rentalAgreement.getDueDate(), LocalDate.of(2015, 7, 10));
        assertEquals(rentalAgreement.getDailyCharge(), new BigDecimal("2.99"));
        assertEquals(rentalAgreement.getChargeDays(), 6);
        assertEquals(rentalAgreement.getPreDiscountCharge(), new BigDecimal("17.94"));
        assertEquals(rentalAgreement.getDiscountPercentage(), 0);
        assertEquals(rentalAgreement.getDiscountCharge(), new BigDecimal("0.00"));
        assertEquals(rentalAgreement.getFinalCharge(), new BigDecimal("17.94"));
    }
    @Test
    public void testRentalAgreement6() {
        Tool tool = ToolFactory.createTool("JAKR");
        int daysToRent = 4;
        int discountPercentage = 50;
        LocalDate startDate = LocalDate.of(2020, 7, 2);
        Checkout checkout = new Checkout();
        HolidayList holidays = checkout.getHolidays(startDate);
        RentalAgreement rentalAgreement = new RentalAgreement(tool, daysToRent, discountPercentage, startDate, holidays);

        assertEquals(rentalAgreement.getToolCode(), "JAKR");
        assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        assertEquals(rentalAgreement.getToolBrand(), "ridgid");
        assertEquals(rentalAgreement.getRentalDays(), 4);
        assertEquals(rentalAgreement.getCheckOutDate(), LocalDate.of(2020, 7, 2));
        assertEquals(rentalAgreement.getDueDate(), LocalDate.of(2020, 7, 5));
        assertEquals(rentalAgreement.getDailyCharge(), new BigDecimal("2.99"));
        assertEquals(rentalAgreement.getChargeDays(), 1);
        assertEquals(rentalAgreement.getPreDiscountCharge(), new BigDecimal("2.99"));
        assertEquals(rentalAgreement.getDiscountPercentage(), 50);
        assertEquals(rentalAgreement.getDiscountCharge(), new BigDecimal("1.50"));
        assertEquals(rentalAgreement.getFinalCharge(), new BigDecimal("1.49"));
    }
}
