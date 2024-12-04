package org.dcistudent.library.management.models.books.states;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
public class HandBack {
    private Boolean active = true;
    private LocalDate handBackedDate;

    public HandBack() {
        this.setHandBackedDate(LocalDate.now());
    }
}
