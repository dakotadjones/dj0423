package src.main.java.util;

import src.main.java.tools.Tool;
import src.main.java.tools.ToolFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class Checkout {
    private Scanner scanner;
    private HolidayList holidays;

    private final Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");


    public Checkout() {
        this.scanner = new Scanner(System.in);
        this.holidays = new HolidayList();

    }

    public void processCheckout() {
        System.out.println("Welcome to the Tool Shop!");

        while (true) {
            // gather user input for tool code
            System.out.println("Enter a tool code to purchase (or 'q' to quit):");
            String code = scanner.nextLine();
            if (code.equalsIgnoreCase("q")) {
                break;
            }
            Tool tool = ToolFactory.createTool(code);

            // gather user input for rental days
            int daysToRent = 0;
            while (daysToRent <= 0) {
                System.out.println("How many days would you like to rent the tool for?");
                daysToRent = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (daysToRent <= 0) {
                    System.out.println("Invalid number of days. Please enter a positive integer.");
                }
            }
            // gather user input for rental days
            int discountPercentage = -1;
            while (discountPercentage < 0 || discountPercentage > 100) {
                System.out.println("Enter a discount percentage (or 0 for no discount):");
                discountPercentage = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (discountPercentage < 0 || discountPercentage > 100) {
                    System.out.println("Invalid discount percentage. Please enter a number between 0 and 100.");
                }
            }
            // get checkout date
            LocalDate checkoutDate;
            while (true) {
                System.out.println("Enter the checkout date (in the format yyyy-MM-dd):");
                String checkoutDateString = scanner.nextLine();
                checkoutDate = LocalDate.parse(checkoutDateString);
                Matcher matcher = datePattern.matcher(checkoutDate.toString());
                if (!matcher.matches()) {
                    System.out.println("Invalid date format.");
                } else {
                    break;
                }
            }
            // set holidays for the checkout date including independence day and labor day
            HolidayList holidays = this.getHolidays(checkoutDate);

            // generate a rental agreement
            RentalAgreement rentalAgreement = new RentalAgreement(tool, daysToRent, discountPercentage, checkoutDate, holidays);
            System.out.println("Your rental agreement has been complete:");
            System.out.println(rentalAgreement);
        }
    }
    public HolidayList getHolidays(LocalDate checkoutDate) {
        int year = checkoutDate.getYear();
        LocalDate laborDay = LocalDate.of(year,9,1).with(firstInMonth(DayOfWeek.MONDAY)); // first monday of sept of that year
        holidays.addHoliday(laborDay);
        LocalDate independenceDay = LocalDate.of(year,7,4);
        if (independenceDay.getDayOfWeek().toString().equals("SATURDAY")){
            independenceDay = independenceDay.minusDays(1); // go to friday before
        }
        else if(independenceDay.getDayOfWeek().toString().equals("SUNDAY")){
            independenceDay = independenceDay.plusDays(1); // go to monday after
        }
        holidays.addHoliday(independenceDay);
        return holidays;
    }
}
