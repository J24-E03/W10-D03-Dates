package org.dcistudent.hydrators.books;

import org.dcistudent.library.management.entities.books.Book;
import org.dcistudent.library.management.models.books.states.Borrow;
import org.dcistudent.library.management.models.books.states.Due;
import org.dcistudent.library.management.models.books.states.HandBack;

public class BookHydrator {
    public static Book hydrate(
            org.dcistudent.library.management.models.books.Book bookModel
    ) {
        Borrow borrow;
        Due due;
        HandBack handBack;

        Book bookEntity = new Book(
                bookModel.getTitle(),
                bookModel.getAuthor(),
                bookModel.getPages(),
                bookModel.getBestseller(),
                bookModel.getAvailable()
        );

        borrow = bookEntity.getBorrow();
        due = borrow.getDue();
        handBack = borrow.getHandBack();
        borrow.setActive(bookModel.getAvailable());
        borrow.setBorrowedDate(bookModel.getBorrow().getBorrowedDate());
        due.setActive(bookModel.getBorrow().getDue().getActive());
        due.setDueDate(bookModel.getBorrow().getDue().getDueDate());
        handBack.setActive(bookModel.getBorrow().getHandBack().getActive());
        handBack.setHandBackedDate(bookModel.getBorrow().getHandBack().getHandBackedDate());

        return bookEntity;
    }

    public static org.dcistudent.library.management.models.books.Book hydrate(
            Book book
    ) {
        Borrow borrow;
        Due due;
        HandBack handBack;

        org.dcistudent.library.management.models.books.Book bookModel = new org.dcistudent.library.management.models.books.Book(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getBestseller(),
                book.getAvailable()
        );

        borrow = bookModel.getBorrow();
        due = borrow.getDue();
        handBack = borrow.getHandBack();
        borrow.setActive(book.getAvailable());
        borrow.setBorrowedDate(book.getBorrow().getBorrowedDate());
        due.setActive(book.getBorrow().getDue().getActive());
        due.setDueDate(book.getBorrow().getDue().getDueDate());
        handBack.setActive(book.getBorrow().getHandBack().getActive());
        handBack.setHandBackedDate(book.getBorrow().getHandBack().getHandBackedDate());

        return bookModel;
    }
}
