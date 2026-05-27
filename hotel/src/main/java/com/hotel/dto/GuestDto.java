package com.hotel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Схема для представлення даних гостя готелю")
public class GuestDto {

    @Schema(description = "Унікальний ідентифікатор гостя в базі даних", example = "1")
    private Long id;

    @Schema(description = "Повне ім'я гостя", example = "Катерина Дорофтей")
    @NotBlank(message = "The name cannot be empty")
    private String name;

    @Schema(description = "Електронна пошта гостя", example = "kate@gmail.com")
    @NotBlank(message = "The email cannot be empty")
    @Email(message = "The email must be valid")
    private String email;

    @Schema(description = "Номер телефону гостя", example = "0990128525")
    @NotBlank(message = "The phone cannot be empty")
    private String phone;
}