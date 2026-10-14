package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = RepositorySingletonTest.Config.class)
class RepositorySingletonTest {

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE, classes = {DemoApplication.class, DemoRunner.class}))
    static class Config {
    }

    @Autowired
    private BookRepository first;

    @Autowired
    private BookRepository second;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookReport bookReport;

    @Test
    void serviceAndReportShareTheSameRepositoryInstance() {
        // Arrange
        int before = first.count();

        // Act
        bookService.addBook("Domain-Driven Design", "Eric Evans");

        // Assert
        assertSame(first, second);
        assertEquals(before + 1, second.count());
        assertTrue(bookReport.lines().stream().anyMatch(l -> l.contains("Domain-Driven Design")));
    }
}
