package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @PostMapping("/register")
    public Customer registerCustomer(@RequestParam String name, 
                                     @RequestParam String email, 
                                     @RequestParam String phoneNumber) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customers.add(customer);
        return customer;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }
}
