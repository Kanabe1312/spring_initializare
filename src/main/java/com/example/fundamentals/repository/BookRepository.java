package com.example.fundamentals.repository;



public interface BookRepository {

    Book saveBook(Book book);
    List<Book> getBooks();
    Book deleteBook(Book book);
    Book findBookById(int id);


}
