package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.mapper.Mapper;
import com.example.bookstoreapi.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customers.stream()
                .map(Mapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        return Mapper.toCustomerDTO(customer);
    }

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = Mapper.toCustomerEntity(customerDTO);
        customers.add(customer);
        return Mapper.toCustomerDTO(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDetails) {
        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (customer != null) {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
        }

        return Mapper.toCustomerDTO(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }
}
