package com.hotel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Схема для представлення даних бронювання кімнати")
public class BookingDto {

    @Schema(description = "Унікальний ідентифікатор бронювання", example = "1")
    private Long id;

    @Schema(description = "Ідентифікатор гостя, який здійснює бронювання", example = "1")
    @NotNull(message = "The guest ID cannot be null")
    private Long guestId;

    @Schema(description = "Ім'я гостя", example = "Катерина Дорофтей")
    private String guestName;

    @Schema(description = "Ідентифікатор заброньованої кімнати", example = "1")
    @NotNull(message = "The room ID cannot be null")
    private Long roomId;

    @Schema(description = "Номер кімнати", example = "101")
    private Integer roomNumber;

    @Schema(description = "Дата заселення", example = "2026-06-01")
    @NotNull(message = "The check-in date cannot be null")
    private LocalDate checkIn;

    @Schema(description = "Дата виселення", example = "2026-06-05")
    @NotNull(message = "The check-out date cannot be null")
    private LocalDate checkOut;
}