package com.hotel.mapper;

import com.hotel.dto.BookingDto;
import com.hotel.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto toDto(Booking booking);
    Booking toEntity(BookingDto bookingDto);
}