package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class CsvBookFormatter implements BookFormatter {

    public CsvBookFormatter() {
        System.out.println("    [constructor] CsvBookFormatter");
    }

    @Override
    public String format(Book book) {
        return book.id() + "," + book.title() + "," + book.author() + "," + book.addedAt();
    }
}
