package com.example.fundamentals.service;

import app.books.model.Book;
import java.util.List;

public interface BookCommandService {

    Book addBook(Book book);

    Book removeBook(Book book);

    Book findBookById(int id);
}