package com.hotel.mapper;

import com.hotel.dto.GuestDto;
import com.hotel.model.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestMapperManual {

    public GuestDto toDto(Guest guest) {
        if (guest == null) return null;
        GuestDto dto = new GuestDto();
        dto.setId(guest.getId());
        dto.setName(guest.getName());
        dto.setEmail(guest.getEmail());
        dto.setPhone(guest.getPhone());
        return dto;
    }

    public Guest toEntity(GuestDto dto) {
        if (dto == null) return null;
        Guest guest = new Guest();
        guest.setId(dto.getId());
        guest.setName(dto.getName());
        guest.setEmail(dto.getEmail());
        guest.setPhone(dto.getPhone());
        return guest;
    }
}