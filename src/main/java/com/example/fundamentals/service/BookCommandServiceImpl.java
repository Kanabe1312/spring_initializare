package com.example.fundamentals.service;

import app.books.model.Book;
import app.books.repository.BookRepoSingleton;
import app.books.repository.BookRepository;

import java.util.List;

public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;

    public BookCommandServiceImpl() {
        this.bookRepository = new BookRepoSingleton().getInstance();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.saveBook(book);
    }

    @Override
    public Book removeBook(Book book) {
        return bookRepository.deleteBook(book);
    }



    @Override
    public Book findBookById(int id) {
        return bookRepository.findBookById(id);
    }
}