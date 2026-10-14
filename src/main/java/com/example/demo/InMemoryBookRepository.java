package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final AtomicInteger INSTANCES = new AtomicInteger();

    private final int instanceNumber = INSTANCES.incrementAndGet();
    private final Map<Integer, Book> books = new LinkedHashMap<>();

    public InMemoryBookRepository() {
        System.out.println("    [constructor] " + this + " (depozit gol)");
    }

    @PostConstruct
    void seed() {
        save(new Book(1, "Clean Code", "Robert C. Martin", LocalDate.of(2026, 9, 1)));
        save(new Book(2, "Effective Java", "Joshua Bloch", LocalDate.of(2026, 9, 15)));
        save(new Book(3, "Spring in Action", "Craig Walls", LocalDate.of(2026, 10, 1)));
        System.out.println("    [@PostConstruct] " + this + ".seed() → " + count() + " cărți");
    }

    @Override
    public Book save(Book book) {
        books.put(book.id(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public int count() {
        return books.size();
    }

    @Override
    public String toString() {
        return "InMemoryBookRepository#" + instanceNumber;
    }

    public static int instancesCreated() {
        return INSTANCES.get();
    }
}
