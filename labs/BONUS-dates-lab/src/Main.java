import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        basicDateHandling();
        dateCalculations();
        zoneIdConverter();
    }

    private static void basicDateHandling() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime: " + now.format(dateTimeFormatter));


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate.format(dateFormatter));

        LocalDate parsedDate = parseDate();
        System.out.println("Parsed LocalDate: " + parsedDate);
    }

    private static LocalDate parseDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a date (dd-MM-yyyy): ");
        String input = scanner.nextLine();

        try {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
            return parseDate();
        }
    }

    private static void dateCalculations() {
        Random random = new Random();
        LocalDate givenDate = LocalDate.now().plusDays(random.nextInt(200) - 100); // ±100 days
        System.out.println("Given Date: " + givenDate);
        System.out.println("30 days later: " + givenDate.plusDays(30));

        calculateAge();
        calculateDifferenceBetweenDates();
    }

    private static void calculateAge() {
        LocalDate birthDate = LocalDate.of(1995, 8, 15);
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        System.out.println("Age of person born on 1995-08-15: " + age + " years.");
    }

    private static void calculateDifferenceBetweenDates() {
        LocalDate date1 = LocalDate.of(2023, 5, 10);
        LocalDate date2 = LocalDate.of(2024, 12, 4);
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("Days between 2023-05-10 and 2024-12-04: " + daysBetween);
    }

    private static void zoneIdConverter() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId sourceZoneId = ZoneId.of("Asia/Tehran");
        ZoneId targetZoneId = ZoneId.of("Europe/Berlin");

        ZonedDateTime convertedDateTime = convertBetweenZoneIds(localDateTime, sourceZoneId, targetZoneId);

        System.out.println("Original LocalDateTime: " + localDateTime);
        System.out.println("Source ZoneId: " + sourceZoneId);
        System.out.println("Target ZoneId: " + targetZoneId);
        System.out.println("Converted ZonedDateTime: " + convertedDateTime);
    }

    private static ZonedDateTime convertBetweenZoneIds(LocalDateTime localDateTime, ZoneId sourceZoneId, ZoneId targetZoneId) {
        ZonedDateTime sourceZonedDateTime = localDateTime.atZone(sourceZoneId);
        return sourceZonedDateTime.withZoneSameInstant(targetZoneId);
    }
}
