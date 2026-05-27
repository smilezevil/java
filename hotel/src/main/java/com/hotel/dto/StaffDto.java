package com.hotel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Схема для представлення даних про персонал готелю")
public class StaffDto {

    @Schema(description = "Унікальний ідентифікатор співробітника", example = "1")
    private Long id;

    @Schema(description = "Повне ім'я співробітника", example = "Анастасія Демʼянчук")
    @NotBlank(message = "The name cannot be empty")
    private String name;

    @Schema(description = "Посада співробітника", example = "Адміністратор")
    @NotBlank(message = "The position cannot be empty")
    private String position;

    @Schema(description = "Заробітна плата співробітника", example = "50000.00")
    @NotNull(message = "The salary cannot be null")
    @Positive(message = "The salary must be positive")
    private Double salary;
}