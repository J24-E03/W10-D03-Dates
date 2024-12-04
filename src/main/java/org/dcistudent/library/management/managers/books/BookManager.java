package org.dcistudent.library.management.managers.books;

import org.dcistudent.library.management.entities.books.Book;
import org.dcistudent.library.management.exceptions.entities.books.BookNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class BookManager {
    private final List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>(Arrays.asList(
                new Book("The Alchemist", "Paulo Coelho", 208, true, false),
                new Book("The Da Vinci Code", "Dan Brown", 454, true, true),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 180, false, true),
                new Book("The Catcher in the Rye", "J.D. Salinger", 214, false, true),
                new Book("The Hobbit", "J.R.R. Tolkien", 310, true, true)
        ));
    }

    public List<Book> findAll() {
        return this.books;
    }

    public List<Book> findAvailableBooks() throws BookNotFoundException {
        List<Book> result = this.books
                .stream()
                .filter(Book::getAvailable)
                .toList()
        ;

        if (result.isEmpty()) {
            throw new BookNotFoundException("No available books found. :(");
        }

        return result;
    }

    public List<Book> findBestsellerBooks() throws BookNotFoundException {
        List<Book> result = this.books
                .stream()
                .filter(Book::getBestseller)
                .toList()
        ;

        if (result.isEmpty()) {
            throw new BookNotFoundException("No bestseller books found. :(");
        }

        return result;
    }

    public Book findByTitle(String title) throws BookNotFoundException {
        try {
            return this.books
                    .stream()
                    .filter(b -> b.getTitle().equals(title)).findFirst()
                    .orElseThrow()
            ;
        } catch (NoSuchElementException e) {
            throw new BookNotFoundException("Book with title {" + title + "} not found. :(");
        }
    }

    public List<Book> findByAuthor(String author) throws BookNotFoundException {
        List<Book> result = this.books
                .stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList()
        ;

        if (result.isEmpty()) {
            throw new BookNotFoundException("Books with author {" + author + "} not found. :(");
        }

        return result;
    }

    public void persist(Book book) {
        for (Book b : this.books) {
            if (b.getTitle().equals(book.getTitle())) {
                this.books.set(this.books.indexOf(b), book);
            }
        }
    }
}
