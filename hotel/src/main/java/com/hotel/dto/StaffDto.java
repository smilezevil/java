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
public class StaffDto {
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "The position cannot be empty")
    private String position;

    @NotNull(message = "The salary cannot be null")
    @Positive(message = "The salary must be positive")
    private Double salary;
}