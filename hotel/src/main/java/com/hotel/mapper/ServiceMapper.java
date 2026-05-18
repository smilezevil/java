package com.hotel.mapper;

import com.hotel.dto.ServiceDto;
import com.hotel.model.Service;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceDto toDto(Service service);
    Service toEntity(ServiceDto serviceDto);
}