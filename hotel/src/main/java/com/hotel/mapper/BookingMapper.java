package com.hotel.mapper;

import com.hotel.dto.BookingDto;
import com.hotel.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "guest.id", target = "guestId")
    @Mapping(source = "guest.name", target = "guestName")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "room.number", target = "roomNumber")
    BookingDto toDto(Booking booking);

    @Mapping(source = "guestId", target = "guest.id")
    @Mapping(source = "roomId", target = "room.id")
    Booking toEntity(BookingDto bookingDto);
}