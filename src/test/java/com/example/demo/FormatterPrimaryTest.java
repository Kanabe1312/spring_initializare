package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = FormatterPrimaryTest.Config.class)
class FormatterPrimaryTest {

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE, classes = {DemoApplication.class, DemoRunner.class}))
    static class Config {
    }

    @Autowired
    private BookFormatter formatter;

    @Test
    void primaryFormatterIsPlain() {
        // Arrange
        Book book = new Book(7, "Clean Code", "Robert C. Martin", LocalDate.of(2026, 9, 1));

        // Act
        String result = formatter.format(book);

        // Assert
        assertTrue(formatter instanceof PlainBookFormatter);
        assertEquals("#7 \"Clean Code\" de Robert C. Martin (adăugată 2026-09-01)", result);
    }
}
