# Java Date Practice Questions

---

## **Basic Date Handling**
1. **Getting the Current Date and Time:**
   - Write a program to print the current date and time in the format `YYYY-MM-DD HH:mm:ss`.

2. **Formatting Dates:**
   - Convert the current date into the format `dd/MM/yyyy` and display it.

3. **Parsing Dates:**
   - Write a program that takes a string in the format `dd-MM-yyyy` and converts it into a `LocalDate` object.

---

## **Date Calculations**
4. **Date Arithmetic:**
   - Given a `LocalDate`, calculate and display the date 30 days from that date.

5. **Age Calculation:**
   - Write a program to calculate the age of a person born on `1995-08-15` using `LocalDate`.

6. **Difference Between Two Dates:**
   - Calculate the number of days between two dates: `2023-05-10` and `2024-12-04`.

---

## **Advanced Date Handling**
7. **Convert Between ZoneIds:**
    - Write a method that takes in a chosen `ZoneId`, a target `ZoneId`, and a `LocalDateTime`. This method should convert the `LocalDateTime` from the first `ZoneId` to a `ZonedDateTime` in the second `ZoneId`, maintaining the same instance in time.
