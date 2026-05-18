package com.hotel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "The email cannot be empty")
    @Email(message = "The email must be valid")
    private String email;

    @NotBlank(message = "The phone cannot be empty")
    private String phone;
}