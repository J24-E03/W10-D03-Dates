package org.dcistudent.controllers;

import org.dcistudent.library.management.services.BookService;

import java.util.Scanner;

public class BookController {
    private final Scanner scanner;
    private final BookService service;

    public static void main(String[] args) {
        new BookController();
    }

    public BookController() {
        this.scanner = new Scanner(System.in).useDelimiter("\n");
        this.service = new BookService(scanner);

        BookService.displayMenu();
        this.processMenu();
    }

    public void processMenu() {
        Integer option = this.scanner.nextInt();

        switch (option) {
            case 1:
                this.service.getAvailableBooks();
                break;
            case 2:
                this.service.getBooksByAuthor();
                break;
            case 3:
                this.service.getBooksByBestseller();
                break;
            case 4:
                this.service.borrowBook();
                break;
            case 5:
                this.service.extendDueDate();
                break;
            case 6:
                this.service.handBackBook();
                break;
            case 7:
                System.out.println("Bye, bye. :)");
                return;
            default:
                System.out.println("Invalid option. :( Try again.");
        }

        BookService.displayMenu();
        this.processMenu();
    }
}