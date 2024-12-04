package org.dcistudent.library.management.models.books.states;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
public class Borrow {
    private Boolean active = false;
    private LocalDate borrowedDate;
    private Due due;
    private HandBack handBack;

    public Borrow() {
        this.setBorrowedDate(LocalDate.now());
        this.setDue(new Due());
        this.setHandBack(new HandBack());
    }
}
