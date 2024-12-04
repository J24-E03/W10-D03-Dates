package org.dcistudent.library.management.models.books.states;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
public class Due {
    public static final Integer DUE_PRICE_PER_DAY = 1;
    public static final Integer DUE_DAYS = 14;

    private Boolean active = false;
    private LocalDate dueDate;

    public Due() {
        this.setDueDate(LocalDate.now());
    }

    public Boolean isDue(LocalDate handBackedDate) {
        return handBackedDate.isAfter(this.getDueDate());
    }

    public Integer calculateDuePrice(LocalDate handBackedDate) {
        return DUE_PRICE_PER_DAY * (int) (handBackedDate.toEpochDay() - this.getDueDate().toEpochDay() - DUE_DAYS);
    }
}
