package com.hotel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long id;

    @NotNull(message = "The guest ID cannot be null")
    private Long guestId;

    @NotNull(message = "The room ID cannot be null")
    private Long roomId;

    @NotNull(message = "The check-in date cannot be null")
    private LocalDate checkIn;

    @NotNull(message = "The check-out date cannot be null")
    private LocalDate checkOut;
}