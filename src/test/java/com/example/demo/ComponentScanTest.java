package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ComponentScanTest.Config.class)
class ComponentScanTest {

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE, classes = {DemoApplication.class, DemoRunner.class}))
    static class Config {
    }

    @Autowired
    private ApplicationContext context;

    @Test
    void annotatedClassesBecomeBeansAndPlainClassesDoNot() {
        // Arrange
        Class<?> annotated = InMemoryBookRepository.class;
        Class<?> plain = PaperBookPrinter.class;

        // Act
        int annotatedBeans = context.getBeanNamesForType(annotated).length;
        int plainBeans = context.getBeanNamesForType(plain).length;

        // Assert
        assertEquals(1, annotatedBeans);
        assertEquals(0, plainBeans);
        assertTrue(context.containsBean("bookService"));
        assertTrue(context.containsBean("bookReport"));
    }
}
