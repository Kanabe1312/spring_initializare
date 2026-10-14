package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookReport {

    private final BookRepository repository;
    private final BookFormatter formatter;

    public BookReport(BookRepository repository, BookFormatter formatter) {
        this.repository = repository;
        this.formatter = formatter;
        System.out.println("    [constructor] BookReport → a primit: " + repository + ", " + formatter.getClass().getSimpleName());
    }

    public List<String> lines() {
        return repository.findAll().stream().map(formatter::format).toList();
    }

    public String summary() {
        return repository.count() + " cărți în depozit";
    }
}
