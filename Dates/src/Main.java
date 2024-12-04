import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
//        localDate();
//        localTime();
//        localDateTime();
        zonedDateTime();

    }

//    localDate: only contains the information for the date: dd-MM-yyyy
    public static void localDate(){

//        creating a new date with of() factory method
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        System.out.println(today);
        System.out.println(tomorrow);

//        Adding or subtracting time from a date
        LocalDate nextWeek = today.plusWeeks(1);

        System.out.println(nextWeek);

        System.out.println(today.minusMonths(1));

        LocalDate newReturnDate = extendDueDate(2,today);

        System.out.println(newReturnDate);


//        COMPARASION: comparing two dates
//        isAfter: returns a boolean if the date is before or after the argument date
        System.out.println(tomorrow.isAfter(today));
        System.out.println(tomorrow.isBefore(today));
        System.out.println(LocalDate.now().isEqual(today));

//        Parsing: turning a string into a date
        String stringDate = "2024-10-29";

        LocalDate parsedDate = LocalDate.parse(stringDate);

        System.out.println(parsedDate.getClass());

        System.out.println(parsedDate.isBefore(today));


        /*
            Exercise 1:
            1. given the following string: "2011-11-20"
            2. turn this string into a LocalDate
            3. Compare this date with today. If today is after the date than sout "today is after chosen date"
            4. otherwise sout "chosen date is after today" or "both dates are the same"

        */


        String givenDate = "2024-12-04";

        LocalDate convertedDate = LocalDate.parse(givenDate);

        if(convertedDate.isAfter(today)){
            System.out.println("chosen date is after today");
        }
        else if(convertedDate.isBefore(today)){
            System.out.println("Today is after chosen date");
        }
        else{
            System.out.println("Today is the same as chosen date");
        }


        System.out.println("Year: " + today.getYear());
        System.out.println("Month: " + today.getMonth());
        System.out.println("Day of Month: " + today.getDayOfMonth());
        System.out.println("Day of Week: " + today.getDayOfWeek());
        System.out.println("Day of Year: " + today.getDayOfYear());


        System.out.println("Length of Month: " + today.minusMonths(1).lengthOfMonth());
        System.out.println("Is Leap Year: " + today.isLeapYear());


//        Changing dates

        LocalDate changedDate = today.withDayOfMonth(1).withYear(1999);

        System.out.println(changedDate);


//        Formatter
//        when formatting dates we need a DateTimeFormatter

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String formattedDate = today.format(formatter);

        System.out.println(formattedDate);
    }


    public static LocalDate extendDueDate(int weeks, LocalDate returnDate){
        return returnDate.plusWeeks(weeks);
    }



    public static void localTime(){

//        Creating a new time
        LocalTime currentTime = LocalTime.now();

        System.out.println(currentTime);

        LocalTime specificTime = LocalTime.of(10,40);
        System.out.println(specificTime);


//        manipulating time
        System.out.println(currentTime.plusHours(2).minusMinutes(2));


//        comparasion
        System.out.println(currentTime.isBefore(specificTime));



//        parse: String to LocalTime

        LocalTime parsedTime = LocalTime.parse("20:00");
        System.out.println(parsedTime);


        System.out.println(parsedTime.getSecond());
        System.out.println(parsedTime.getMinute());
        System.out.println(parsedTime.getHour());


        System.out.println(parsedTime.compareTo(currentTime));


        System.out.println(currentTime.withHour(23).withSecond(50));


//        formatter

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss::mm::HH");

//        converting localDate to string
        String formattedTime = currentTime.format(formatter);

        System.out.println(formattedTime);

//        converting string to localdate
        LocalTime convertedBackTime = LocalTime.parse(formattedTime, formatter);
        System.out.println(convertedBackTime);


        String properTime = "20:10:40";

        System.out.println(LocalTime.parse(properTime));

     /*
            Exercise 2:
            1. take the following string "20:10:11"
            2. and format it so the output looks like this "20//10//11"
        */

        String givenString = "20:10:11";

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH//mm//ss");

        System.out.println(LocalTime.parse(givenString).format(formatter1));



    }
    public static void localDateTime(){

        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        LocalDateTime todayTime = LocalDateTime.of(today,currentTime);

        System.out.println(todayTime);

        LocalDateTime specificTime = LocalDateTime.of(2022,11,11,11,30);

        System.out.println(specificTime);

        LocalDateTime parsedDateTime = LocalDateTime.parse("1995-12-04T02:26:07");

        System.out.println(parsedDateTime.getClass());

        System.out.println(today.isAfter(specificTime.toLocalDate()));

        System.out.println(currentTime.isAfter(specificTime.toLocalTime()));

        System.out.println(currentTime.getHour());

        System.out.println(specificTime.getDayOfMonth());

        System.out.println(specificTime.plusYears(200));



    }
    public static void zonedDateTime(){

        ZonedDateTime zonedNow = ZonedDateTime.now();
        System.out.println(zonedNow);

        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();


        ZonedDateTime omarZone = ZonedDateTime.of(today, currentTime, ZoneId.of("Asia/Riyadh"));

//        ZonedDateTime classZone = ZonedDateTime.of(today,currentTime.minusHours(2),ZoneId.of("Europe/Paris"));

        System.out.println(omarZone);
//        System.out.println(classZone);


        ZonedDateTime classZone = omarZone.withZoneSameInstant(ZoneId.of("Europe/Paris"));

        ZonedDateTime newYorkTime = omarZone.withZoneSameInstant(ZoneId.of("America/New_York"));

        ZonedDateTime portOfSpain = omarZone.withZoneSameInstant(ZoneId.of("America/Port_of_Spain"));


        System.out.println("Omar Zone "+omarZone);
        System.out.println("Class time "+classZone);
        System.out.println("New york time " + newYorkTime);
        System.out.println(portOfSpain);



    }

}