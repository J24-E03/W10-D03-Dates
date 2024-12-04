import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static ArrayList<Book> books = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBooks();

        while(true){
            displayMenu();

            int choice = scanner.nextInt();

            if(choice == 4){
                break;
            }
            else if(choice == 1){
                viewAvailableBooks();
            }
            else if(choice ==2){
                scanner.nextLine();
                String chosenBookTitle = scanner.nextLine();
                borrowBookByTitle(chosenBookTitle);
            }
            else if(choice == 3){
                scanner.nextLine();
                String chosenBookTitle = scanner.nextLine();
                returnBook(chosenBookTitle);
            }
        }





    }

    public static void displayMenu(){
        System.out.println("Library Management System Menu: \n 1. View Available Books \n 2. Borrow a Book \n 3. Return a Book \n 4. Exit");

    }

    public static void initializeBooks(){
        Book book1 = new Book("Harry Potter", 150, "JK Rowling",true,true);
        Book book2 = new Book("Harry Potter 2", 150, "JK Rowling",true,true);
        Book book3 = new Book("Harry Potter 3", 150, "JK Rowling",true,false);
        Book book4 = new Book("Harry Potter 4", 150, "JK Rowling",true,true);

        books.addAll(Arrays.asList(book1,book2,book3,book4));
    }

    public static void viewAvailableBooks(){

        System.out.println("Available Books: ");
        for(Book book: books){
            if(book.isAvaialble()){
                System.out.println(book);
            }
        }
    }

    public static void borrowBookByTitle(String title){
        Book foundBook = null;

        for(Book book : books){
            if(book.getTitle().equals(title)){
                foundBook = book;
                book.setAvaialble(false);
            }
        }
        System.out.println(foundBook != null ? "You have checked out " + foundBook.getTitle() : "Sorry the book " + title + "is not available");
    }

    public static void returnBook(String title){
        Book foundBook = null;
        for(Book book : books){
            if(book.getTitle().equals(title)){
                foundBook = book;
                book.setAvaialble(true);
            }
        }
        System.out.println(foundBook != null ? "You have successfully returned a book " : "The book " + title + "was not found");


    }
}