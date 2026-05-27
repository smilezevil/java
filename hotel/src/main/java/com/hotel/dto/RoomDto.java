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
@Schema(description = "Схема для представлення даних кімнати готелю")
public class RoomDto {

    @Schema(description = "Унікальний ідентифікатор кімнати в базі даних", example = "1")
    private Long id;

    @Schema(description = "Номер кімнати", example = "101")
    @NotNull(message = "The room number cannot be null")
    private Integer number;

    @Schema(description = "Тип кімнати (Standard, Deluxe, Suite, Presidential)", example = "Standard")
    @NotBlank(message = "The room type cannot be empty")
    private String type;

    @Schema(description = "Ціна за одну ніч проживання", example = "1500.00")
    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price must be positive")
    private Double price;

    @Schema(description = "Статус доступності кімнати для вселення", example = "true")
    @NotNull(message = "The available status cannot be null")
    private Boolean available;
}