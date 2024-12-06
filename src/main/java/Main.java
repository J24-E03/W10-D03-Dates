import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {



    }
    public static void initializeBooks () {
        Book book1 = new Book("Animal Farm", 200, "George Orwell", true, true);
        Book book2 = new Book("The Kite Runner", 300, "Khaled Hosseini", true, true);
        Book book3 = new Book("The White Tiger", 300, "Arundhati Roy", false, false);
        Book book4 = new Book("2 States", 500, "Chetan Bhagat", true, true);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
    }

    public static String borrowBookByTitle (String title){
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    book.setBorrowDate(LocalDate.now()); // set the borrowDate to the current date.
                    book.setDueDate(LocalDate.now().plusDays(14)); //the dueDate to be 14 days from the borrow date.
                    return "You have checked out " + title + ".";
                } else {
                    return "Sorry, the book " + title + " is currently unavailable.";
                }
            }
        }
        return "Sorry, the book " + title + " was not found in our records.";
    }
    public static String returnBook (String title){
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailable(true);
                book.setReturnDate(LocalDate.now());
                long overdueDays = book.getReturnDate().toEpochDay() - book.getDueDate().toEpochDay();
                double fine = overdueDays * 1.0;
                book.setAvailable(true);
                return "You have successfully returned the book. Thank you. and over due day fine is : " + fine; //Display the return status and fine, if applicable
            } else {
                return "The book " + title + "was not found in our records.";
            }
        }
        return "The book" + title + " was not found in our records.";
    }

    public static void checkOverdueBooks () {
        for (Book book : books) {
            if (book.getDueDate().isBefore(LocalDate.now()) && book.getReturnDate() == null) ;
            long overdueDays = book.getReturnDate().toEpochDay() - book.getDueDate().toEpochDay();
            System.out.println("Over due books:" + book.getTitle() + "Over due days:" + overdueDays);//Display all overdue books along with how many days they are overdue.

        }
    }
    public static void extendDueDate (String title,int additionalDays){
        for(Book book : books){
            if (book.getTitle().equals(title) && book.getReturnDate() == null) {
                LocalDate newDueDate = book.getDueDate().plusDays(additionalDays);
                book.setDueDate(newDueDate);
                System.out.println("Due date for '" + title + "' extended by " + additionalDays + " days. New Due Date: " + newDueDate);
                return;
            }
        }
        System.out.println("Book '" + title + "' is not borrowed.");
    }

    // Filter Books by Borrow Date Range Method
    public void filterBooksByBorrowDate(LocalDate startDate, LocalDate endDate) {
        for (Book book : books) {
            if (book.getBorrowDate() != null && (book.getBorrowDate().isEqual(startDate) || book.getBorrowDate().isAfter(startDate)) &&
                    (book.getBorrowDate().isEqual(endDate) || book.getBorrowDate().isBefore(endDate))) {
                System.out.println("Book borrowed within range: " + book.getTitle() + " | Borrow Date: " + book.getBorrowDate());
            }
        }
    }

    // Notify About Due Books Method
    public void notifyDueBooks() {
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);
        for (Book book : books) {
            if (book.getDueDate() != null && (book.getReturnDate() && (book.getDueDate().isBefore(threeDaysLater) || book.getDueDate().isEqual(threeDaysLater))) {
                System.out.println("Book '" + book.getTitle() + "' is due within the next 3 days.");
            }
        }
    }
        }


