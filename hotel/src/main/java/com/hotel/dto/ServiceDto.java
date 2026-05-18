package com.hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private Long id;

    @NotBlank(message = "The service name cannot be empty")
    private String name;

    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price must be positive")
    private Double price;
}