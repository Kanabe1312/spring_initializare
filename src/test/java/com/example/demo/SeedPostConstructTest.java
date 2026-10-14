package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SeedPostConstructTest.Config.class)
class SeedPostConstructTest {

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE, classes = {DemoApplication.class, DemoRunner.class}))
    static class Config {
    }

    @Autowired
    private BookRepository repository;

    @Test
    void springManagedRepositoryIsSeededButManualOneIsEmpty() {
        // Arrange
        InMemoryBookRepository manual = new InMemoryBookRepository();

        // Act
        List<Book> seeded = repository.findAll();

        // Assert
        assertEquals(3, seeded.size());
        assertEquals("Clean Code", seeded.get(0).title());
        assertEquals(0, manual.count());
    }
}
