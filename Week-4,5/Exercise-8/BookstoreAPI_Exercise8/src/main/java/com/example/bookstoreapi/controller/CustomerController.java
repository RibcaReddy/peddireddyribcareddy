package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails) {
        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (customer != null) {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
        }

        return customer;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }
}
