package com.example.bookstoreapi.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

@Data
public class Book {
    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;

    @NotNull(message = "Author is required")
    private String author;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price should be positive")
    private Double price;

    @NotNull(message = "ISBN is required")
    private String isbn;
}
