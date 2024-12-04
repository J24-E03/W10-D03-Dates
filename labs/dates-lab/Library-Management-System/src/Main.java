import javax.swing.text.html.HTMLDocument;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final int DUE_DATE = 14;
    private static final int NOTIFY_PERIOD = 3;
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        initializeBooks();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = false;
        do {
            System.out.println("Welcome to our Library Management System!");
            System.out.println("If you and working with system as a user enter 1, if an admin enter 2: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        displayMenuToUser(scanner);
                        isRunning = false;
                        break;
                    case 2:
                        displayMenuToAdmin(scanner);
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose 1 or 2!");
                        isRunning = true;
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid choice! Please choose 1 or 2!");
                isRunning = true;
            }
        } while (isRunning);

        scanner.close();

    }

    private static void initializeBooks() {
        LocalDate currentDate = LocalDate.now();
        books.addAll(Arrays.asList(
                new Book("The Great Gatsby", 180, "F. Scott Fitzgerald", true, true),
                new Book("To Kill a Mockingbird", 281, "Harper Lee", true, false, currentDate.minusDays(20), currentDate.minusDays(6), null),
                new Book("1984", 328, "George Orwell", true, true),
                new Book("Pride and Prejudice", 432, "Jane Austen", false, true),
                new Book("Moby Dick", 585, "Herman Melville", false, true),
                new Book("The Catcher in the Rye", 277, "J.D. Salinger", true, false, currentDate.minusDays(10), currentDate.plusDays(4), null),
                new Book("Brave New World", 311, "Aldous Huxley", true, true),
                new Book("War and Peace", 1225, "Leo Tolstoy", false, false, currentDate.minusDays(30), currentDate.minusDays(16), null),
                new Book("The Odyssey", 541, "Homer", false, true),
                new Book("The Alchemist", 208, "Paulo Coelho", true, true),
                new Book("The Book Thief", 552, "Markus Zusak", true, false, currentDate.minusDays(11), currentDate.plusDays(3), null),
                new Book("The Hobbit", 310, "J.R.R. Tolkien", true, true),
                new Book("Crime and Punishment", 671, "Fyodor Dostoevsky", false, true),
                new Book("The Road", 287, "Cormac McCarthy", false, false, currentDate.minusDays(5), currentDate.plusDays(9), null),
                new Book("Frankenstein", 280, "Mary Shelley", false, true)
        ));
    }

    private static void displayMenuToAdmin(Scanner scanner) {
        boolean isRunning = true;
        System.out.println("\nWelcome Admin:");
        while (isRunning) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Check for Overdue Books");
            System.out.println("2. Filter Books by Borrow Date Range");
            System.out.println("3. Notify About Due Books");
            System.out.println("4. Extend Due Date of a Book");
            System.out.println("5. Exit");
            System.out.print("Please Enter Your Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        checkOverdueBooks();
                        break;
                    case 2:
                        System.out.println("Please Enter a Start Date in this format: (yyyy-MM-dd): ");
                        LocalDate startDate, endDate;
                        String startDateString = scanner.nextLine();
                        try {
                            startDate = LocalDate.parse(startDateString);
                        } catch (Exception ex) {
                            System.out.println("Wrong date format! Please try again later!");
                            break;
                        }
                        System.out.println("Please Enter an End Date in this format: (yyyy-MM-dd): ");
                        String endDateString = scanner.nextLine();
                        try {
                            endDate = LocalDate.parse(endDateString);
                        } catch (Exception ex) {
                            System.out.println("Wrong date format! Please try again later!");
                            break;
                        }
                        filterBooksByBorrowDate(startDate, endDate);
                        break;
                    case 3:
                        notifyDueBooks();
                        break;

                    case 4:
                        System.out.println("Which book do you want to extend the due date? ");
                        String title = scanner.nextLine();
                        int additionalDays;
                        System.out.println("How many days do you want it to be extended? ");
                        try{
                            additionalDays = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (Exception ex){
                            System.out.println("Invalid input for days. Please try again later!");
                            scanner.nextLine();
                            break;
                        }
                        extendDueDate(title, additionalDays);
                        break;

                    case 5:
                        isRunning = false;
                        System.out.println("Thank you and Goodbye!");
                        break;
                    default:
                        System.out.println("Please enter a valid choice (1–5).");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                scanner.nextLine();
            }

        }
        scanner.close();
    }

    private static void displayMenuToUser(Scanner scanner) {
        boolean isRunning = true;
        System.out.println("\nWelcome User:");
        while (isRunning) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. View All Books");
            System.out.println("2. View Available Books");
            System.out.println("3. View Bestseller Books");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Search Books by Author");
            System.out.println("7. Exit");
            System.out.print("Please Enter Your Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        viewAllBooks();
                        break;
                    case 2:
                        viewAvailableBooks();
                        break;
                    case 3:
                        viewBestSellerBooks();
                        break;

                    case 4:
                        System.out.print("Enter the title of the book you want to borrow: ");
                        borrowBookByTitle(scanner.nextLine().trim());
                        break;

                    case 5:
                        System.out.print("Enter the title of the book you want to return: ");
                        returnBook(scanner.nextLine().trim());
                        break;
                    case 6:
                        System.out.print("Enter the author's name: ");
                        searchBookByAuthor(scanner.nextLine().trim());
                        break;
                    case 7:
                        isRunning = false;
                        System.out.println("Thank you and Goodbye!");
                        break;
                    default:
                        System.out.println("Please enter a valid choice (1–7).");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                scanner.nextLine();
            }

        }
    }

    private static void viewAvailableBooks() {
        System.out.println("\nAvailable Books:");
        books.stream()
                .filter(Book::isAvailable)
                .forEach(System.out::println);
    }

    private static void viewAllBooks() {
        System.out.println("\nAll Books in the Library:");
        books.forEach(System.out::println);
    }

    private static void viewBestSellerBooks() {
        System.out.println("\nBestseller Books:");
        books.stream()
                .filter(Book::isBestSeller)
                .forEach(System.out::println);
    }


    private static void searchBookByAuthor(String author) {
        System.out.println("\nBooks by " + author + ":");
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                found = true;
                System.out.println(book);
            }
        }
        if (! found) {
            System.out.println("No books found by this author.");
        }
    }

    private static void borrowBookByTitle(String title) {
        if (title.isBlank()) {
            System.out.println("Invalid input. Book title cannot be empty.");
            return;
        }

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    LocalDate borrowDate = LocalDate.now();
                    LocalDate dueDate = borrowDate.plusDays(DUE_DATE);
                    book.setBorrowDate(borrowDate);
                    book.setDueDate(dueDate);
                    System.out.println("You have Checked out " + book.getTitle() + " on " + borrowDate);
                    System.out.println("The Due Date is: " + dueDate);
                    return;
                }
                System.out.println("Sorry, the book " + book.getTitle() + " is currently unavailable");
                return;
            }
        }
        System.out.println("The book " + title + " was not found in our records.");
    }

    private static void returnBook(String title) {
        if (title.isBlank()) {
            System.out.println("Invalid input. Book title cannot be empty.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isAvailable()) {
                    LocalDate currentDate = LocalDate.now();
                    book.setAvailable(true);
                    book.setReturnDate(currentDate);
                    if (book.getDueDate().isBefore(currentDate)) {
                        System.out.println("The book is overdue! your fine will be " + book.getDueDate().until(currentDate).getDays() + "$.");
                    }
                    System.out.println("You have successfully returned " + book.getTitle() + ". Thank you!");
                    return;
                }
                System.out.println("The book " + book.getTitle() + " is already available in the library.");
                return;
            }
        }
        System.out.println("The book " + title + " was not found in our records.");
    }

    private static void checkOverdueBooks() {
        System.out.println("\nList of all overdue Books:");
        LocalDate currentDate = LocalDate.now();
        for (Book book : books) {
            if (book.getReturnDate() == null && book.getDueDate() != null && book.getDueDate().isBefore(currentDate)) {
                System.out.println(book + " overdue for " + book.getDueDate().until(currentDate).getDays() + " days.");
            }
        }
    }

    private static void extendDueDate(String title, int additionalDays) {
        if (title.isBlank()) {
            System.out.println("Invalid input. Book title cannot be empty.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isAvailable() && book.getDueDate() != null) {
                    System.out.println("Due Date of this book is: " + book.getDueDate());
                    book.setDueDate(book.getDueDate().plusDays(additionalDays));
                    System.out.println("New Due Date will be: " + book.getDueDate());
                    return;
                }
                System.out.println("The book " + book.getTitle() + "  isn't borrowed! You cannot change any Due date for this book.");
                return;
            }
        }
        System.out.println("The book " + title + " was not found in our records.");
    }

    private static void filterBooksByBorrowDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            System.out.println("Dates cannot be null.");
            return;
        }

        if (startDate.isAfter(endDate)) {
            System.out.println("Invalid range.");
            return;
        }

        System.out.println("All books borrowed between " + startDate + " and " + endDate + ":");
        for (Book book : books) {
            if (book.getBorrowDate() != null && book.getBorrowDate().isAfter(startDate) && book.getBorrowDate().isBefore(endDate)) {
                System.out.println(book);
            }
        }
    }

    private static void notifyDueBooks() {
        System.out.println("These books should be returned within the next three days:");
        boolean found = false;
        for (Book book : books) {
            if (!book.isAvailable() && book.getDueDate() != null && book.getDueDate().isAfter(LocalDate.now())) {
                if (LocalDate.now().until(book.getDueDate()).getDays() <= NOTIFY_PERIOD) {
                    found = true;
                    System.out.println(book + " hast DueDate on: " + book.getDueDate());
                }
            }
        }
        if (!found) {
            System.out.println("No Book was found!");
        }
    }
}