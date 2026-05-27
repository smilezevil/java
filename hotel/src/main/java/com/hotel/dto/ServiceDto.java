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
@Schema(description = "Схема для представлення додаткових послуг готелю")
public class ServiceDto {

    @Schema(description = "Унікальний ідентифікатор послуги", example = "1")
    private Long id;

    @Schema(description = "Назва послуги", example = "Сніданок")
    @NotBlank(message = "The service name cannot be empty")
    private String name;

    @Schema(description = "Вартість послуги", example = "250.00")
    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price must be positive")
    private Double price;
}