package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(int id);

    List<Book> findAll();

    int count();
}
