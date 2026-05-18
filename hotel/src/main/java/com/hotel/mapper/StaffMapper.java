package com.hotel.mapper;

import com.hotel.dto.StaffDto;
import com.hotel.model.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffDto toDto(Staff staff);
    Staff toEntity(StaffDto staffDto);
}