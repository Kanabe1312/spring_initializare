package com.example.demo;

import java.time.LocalDate;

public record Book(int id, String title, String author, LocalDate addedAt) {
}
