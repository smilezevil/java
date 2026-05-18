package com.hotel.mapper;

import com.hotel.dto.GuestDto;
import com.hotel.model.Guest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestDto toDto(Guest guest);
    Guest toEntity(GuestDto guestDto);
}