package org.dcistudent.library.management.entities.books;

import lombok.*;
import lombok.Setter;
import org.dcistudent.library.management.models.books.states.Borrow;

@Getter @Setter
public class Book {
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private Integer pages;
    @NonNull
    private Boolean bestseller = false;
    @NonNull
    private Boolean available = true;
    @NonNull
    private Borrow borrow;

    public Book(String title, String author, Integer pages, Boolean isBestSeller, Boolean isAvailable) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setPages(pages);
        this.setBestseller(isBestSeller);
        this.setAvailable(isAvailable);
        this.setBorrow(new Borrow());
    }

    @Override
    public String toString() {
        return this.getTitle() + " (Author: " + this.getAuthor() + ", Pages: " + this.getPages() + ", Bestseller: " + this.getBestseller() + ")";
    }
}
