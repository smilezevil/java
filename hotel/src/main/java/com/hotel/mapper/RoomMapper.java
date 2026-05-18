package com.hotel.mapper;

import com.hotel.dto.RoomDto;
import com.hotel.model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto toDto(Room room);
    Room toEntity(RoomDto roomDto);
}