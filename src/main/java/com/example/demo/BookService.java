package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final Clock clock;
    private final String libraryName;

    public BookService(BookRepository repository,
                       Clock clock,
                       @Value("${demo.library.name}") String libraryName) {
        this.repository = repository;
        this.clock = clock;
        this.libraryName = libraryName;
        System.out.println("    [constructor] BookService → a primit: " + repository + ", Clock, libraryName='" + libraryName + "'");
    }

    public Book addBook(String title, String author) {
        int nextId = repository.count() + 1;
        return repository.save(new Book(nextId, title, author, LocalDate.now(clock)));
    }

    public List<Book> catalog() {
        return repository.findAll();
    }

    public String libraryName() {
        return libraryName;
    }
}
