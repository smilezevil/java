package com.hotel.repository;

import com.hotel.model.Room;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class RoomRepository {
    private final Map<Long, Room> rooms = new HashMap<>();
    private long nextId = 1;

    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    public Optional<Room> findById(Long id) {
        return Optional.ofNullable(rooms.get(id));
    }

    public Room save(Room room) {
        if (room.getId() == null) {
            room.setId(nextId++);
        }
        rooms.put(room.getId(), room);
        return room;
    }

    public void deleteAll() {
        rooms.clear();
    }

    public void deleteById(Long id) {
        rooms.remove(id);
    }
}