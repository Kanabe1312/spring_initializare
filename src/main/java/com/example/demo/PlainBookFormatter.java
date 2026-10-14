package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PlainBookFormatter implements BookFormatter {

    public PlainBookFormatter() {
        System.out.println("    [constructor] PlainBookFormatter");
    }

    @Override
    public String format(Book book) {
        return "#" + book.id() + " \"" + book.title() + "\" de " + book.author() + " (adăugată " + book.addedAt() + ")";
    }
}
