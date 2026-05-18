package com.hotel.repository;

import com.hotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Room> roomRowMapper = (rs, rowNum) -> {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setNumber(rs.getInt("number"));
        room.setType(rs.getString("type"));
        room.setPrice(rs.getDouble("price"));
        room.setAvailable(rs.getBoolean("available"));
        return room;
    };

    public List<Room> findAllAvailableRooms() {
        return jdbcTemplate.query(
                "SELECT * FROM room WHERE available = true",
                roomRowMapper
        );
    }
}