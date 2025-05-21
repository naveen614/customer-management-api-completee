package com.example.customermanagement.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

// DTO for returning customer data including calculated tier
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private UUID id;
    private String name;
    private String email;
    private Double annualSpend;
    private LocalDateTime lastPurchaseDate;
    private String tier; // Tier is derived from business logic, not stored in DB
}