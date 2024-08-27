package com.example.bookstoreapi.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Customer {
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number should be between 10 and 15 characters")
    private String phoneNumber;
}
