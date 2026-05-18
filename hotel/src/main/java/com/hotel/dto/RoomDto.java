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
public class RoomDto {
    private Long id;

    @NotNull(message = "The room number cannot be null")
    private Integer number;

    @NotBlank(message = "The room type cannot be empty")
    private String type;

    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price must be positive")
    private Double price;

    @NotNull(message = "The available status cannot be null")
    private Boolean available;
}