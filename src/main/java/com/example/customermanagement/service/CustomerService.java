package com.example.customermanagement.service;

import com.example.customermanagement.dto.CustomerRequest;
import com.example.customermanagement.dto.CustomerResponse;
import com.example.customermanagement.model.Customer;
import com.example.customermanagement.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    // Create and persist a new customer
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .annualSpend(request.getAnnualSpend())
                .lastPurchaseDate(request.getLastPurchaseDate())
                .build();
        customer = repository.save(customer);
        return mapToResponse(customer);
    }

    // Retrieve a customer by UUID
    public CustomerResponse getCustomerById(UUID id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return mapToResponse(customer);
    }

    // Retrieve a customer by name
    public CustomerResponse getCustomerByName(String name) {
        Customer customer = repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return mapToResponse(customer);
    }

    // Retrieve a customer by email
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return mapToResponse(customer);
    }

    // Update an existing customer's details
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAnnualSpend(request.getAnnualSpend());
        customer.setLastPurchaseDate(request.getLastPurchaseDate());

        customer = repository.save(customer);
        return mapToResponse(customer);
    }

    // Delete a customer by UUID
    public void deleteCustomer(UUID id) {
        repository.deleteById(id);
    }

    // Map entity to response including tier calculation
    private CustomerResponse mapToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .annualSpend(customer.getAnnualSpend())
                .lastPurchaseDate(customer.getLastPurchaseDate())
                .tier(calculateTier(customer)) // Tier is computed dynamically
                .build();
    }

    // Determine tier based on annualSpend and purchase recency
    private String calculateTier(Customer customer) {
        if (customer.getAnnualSpend() == null) return "Silver"; // Default tier
        double spend = customer.getAnnualSpend();
        LocalDateTime lastPurchase = customer.getLastPurchaseDate();

        // Check for Platinum eligibility
        if (spend >= 10000 && lastPurchase != null && ChronoUnit.MONTHS.between(lastPurchase, LocalDateTime.now()) <= 6) {
            return "Platinum";
        }
        // Check for Gold eligibility
        if (spend >= 1000 && lastPurchase != null && ChronoUnit.MONTHS.between(lastPurchase, LocalDateTime.now()) <= 12) {
            return "Gold";
        }
        return "Silver"; // Fallback tier
    }
}