package com.example.customermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

// Entity class representing the Customer table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID") // Auto-generate UUID for each customer
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank // Ensures name is not null or empty
    private String name;

    @NotBlank
    @Email // Ensures valid email format
    private String email;

    private Double annualSpend; // Optional field to track spending

    private LocalDateTime lastPurchaseDate; // Optional field to track last purchase
}