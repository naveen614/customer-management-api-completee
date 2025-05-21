package com.example.customermanagement.controller;

import com.example.customermanagement.dto.CustomerRequest;
import com.example.customermanagement.dto.CustomerResponse;
import com.example.customermanagement.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    // Create new customer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CustomerRequest request) {
        return service.createCustomer(request);
    }

    // Retrieve customer by ID
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable UUID id) {
        return service.getCustomerById(id);
    }

    // Retrieve customer by name (via query param)
    @GetMapping(params = "name")
    public CustomerResponse getByName(@RequestParam String name) {
        return service.getCustomerByName(name);
    }

    // Retrieve customer by email (via query param)
    @GetMapping(params = "email")
    public CustomerResponse getByEmail(@RequestParam String email) {
        return service.getCustomerByEmail(email);
    }

    // Update existing customer by ID
    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable UUID id, @Valid @RequestBody CustomerRequest request) {
        return service.updateCustomer(id, request);
    }

    // Delete customer by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.deleteCustomer(id);
    }
}