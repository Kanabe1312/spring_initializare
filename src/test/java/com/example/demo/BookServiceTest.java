package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.time.Clock;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BookServiceTest.Config.class)
class BookServiceTest {

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE, classes = {DemoApplication.class, DemoRunner.class}))
    static class Config {
    }

    @Autowired
    private BookService bookService;

    @Autowired
    private Clock clock;

    @Test
    void addBookStampsTodayAndReadsLibraryNameFromYaml() {
        // Arrange
        LocalDate today = LocalDate.now(clock);

        // Act
        Book book = bookService.addBook("Head First Java", "Kathy Sierra");

        // Assert
        assertEquals("Head First Java", book.title());
        assertEquals(today, book.addedAt());
        assertEquals("Biblioteca MyCodeSchool", bookService.libraryName());
    }
}
