package org.dcistudent.library.management.services;

import org.dcistudent.library.management.entities.books.Book;
import org.dcistudent.library.management.exceptions.entities.books.BookNotFoundException;
import org.dcistudent.library.management.managers.BookManager;
import org.dcistudent.library.management.models.books.states.Borrow;
import org.dcistudent.library.management.models.books.states.Due;
import org.dcistudent.library.management.models.books.states.HandBack;

import java.time.LocalDate;
import java.util.*;

public class BookService {
    private Scanner scanner;
    private BookManager manager;

    public BookService(Scanner scanner) {
        this.scanner = scanner;
        this.manager = new BookManager();
    }

    public static void displayMenu() {
        System.out.println("Select an option:");
        System.out.println("1. View available books");
        System.out.println("2. Search books by author");
        System.out.println("3. View bestseller books");
        System.out.println("4. Borrow a book");
        System.out.println("5. Extend due date");
        System.out.println("6. Return a book");
        System.out.println("7. Exit");
    }

    public void getAvailableBooks() {
        List<Book> books;

        try {
            books = this.manager.findAvailableBooks();
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }

        // display available books
        System.out.println();
        System.out.println("Available books:");
        books.forEach(System.out::println);
        System.out.println();
    }

    public void getBooksByAuthor() {
        List<Book> books;

        System.out.println("Enter the author of the book you want to search:");
        String author = this.scanner.next();

        if (author.isBlank() == true) {
            System.out.println("Author cannot be empty. :(");
            this.getBooksByAuthor();
            return;
        }

        try {
            books = this.manager.findByAuthor(author);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }

        System.out.println();
        System.out.println("Books by " + author + ":");
        books.forEach(System.out::println);
        System.out.println();
    }

    public void getBooksByBestseller() {
        List<Book> books;

        try {
            books = this.manager.findBestsellerBooks();
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }

        System.out.println();
        System.out.println("Bestseller books:");
        books.forEach(System.out::println);
        System.out.println();
    }

    public void borrowBook() {
        Book book;
        Borrow borrow;
        Due due;
        HandBack handBack;

        System.out.println("Enter the title of the book you want to borrow:");
        String title = this.scanner.next();

        try {
            book = this.manager.findByTitle(title);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }

        if (book.getAvailable() == false) {
            System.out.println("Book not available. :(");
            System.out.println();
            return;
        }

        borrow = book.getBorrow();
        due = borrow.getDue();
        handBack = borrow.getHandBack();
        book.setAvailable(false);
        borrow.setActive(true);
        borrow.setBorrowedDate(LocalDate.now());
        due.setActive(true);
        due.setDueDate(LocalDate.now().plusDays(Due.DUE_DAYS));
        handBack.setActive(false);
        this.manager.persist(book);

        System.out.println("Book borrowed successfully. :)");
        System.out.println();
    }

    public void extendDueDate() {
        Book book;
        Borrow borrow;
        Due due;
        LocalDate newDueDate;
        String title;
        Integer days;

        System.out.println("Enter the title of the book you want to extend the due date:");
        title = this.scanner.next();

        try {
            book = this.manager.findByTitle(title);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }

        if (book.getAvailable() == true) {
            System.out.println("Book is still available for borrowing. :)");
            System.out.println();
            return;
        }

        borrow = book.getBorrow();
        due = borrow.getDue();
        System.out.println("Enter the days you want to extend the due date:");
        days = this.scanner.nextInt();

        newDueDate = due.getDueDate().plusDays(days);
        due.setDueDate(newDueDate);
        this.manager.persist(book);

        System.out.println("Due date extended successfully. :)");
        System.out.println();
    }

    public void handBackBook() {
        Book book;
        LocalDate today;
        Borrow borrow;
        Due due;
        HandBack handBack;

        System.out.println("Enter the title of the book you want to return:");
        String title = this.scanner.next();

        try {
            book = this.manager.findByTitle(title);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }

        if (book.getAvailable() == true) {
            System.out.println("Book is still available for borrowing. :)");
            System.out.println();
            return;
        }

        today = LocalDate.now().plusDays(30);
        borrow = book.getBorrow();
        due = borrow.getDue();
        handBack = borrow.getHandBack();
        handBack.setHandBackedDate(today);
        if (due.isDue(today)) {
            System.out.println(
                    "Book is overdue. :( " +
                    "Please pay the fine of " + due.calculateDuePrice(today) + " USD."
            );
        }
        book.setAvailable(true);
        borrow.setActive(false);
        due.setActive(false);
        handBack.setActive(true);
        this.manager.persist(book);

        System.out.println("Book returned successfully. :)");
        System.out.println();
    }
}
