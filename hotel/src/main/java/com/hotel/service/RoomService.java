package com.hotel.service;

import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.hotel.repository.RoomJdbcRepository;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    private final RoomJdbcRepository roomJdbcRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Optional<Room> updateRoom(Long id, Room updatedRoom) {
        return roomRepository.findById(id).map(room -> {
            room.setNumber(updatedRoom.getNumber());
            room.setType(updatedRoom.getType());
            room.setPrice(updatedRoom.getPrice());
            room.setAvailable(updatedRoom.getAvailable());
            return roomRepository.save(room);
        });
    }

    public Optional<Room> patchRoom(Long id, Room patchedRoom) {
        return roomRepository.findById(id).map(room -> {
            if (patchedRoom.getType() != null) room.setType(patchedRoom.getType());
            if (patchedRoom.getPrice() != null) room.setPrice(patchedRoom.getPrice());
            if (patchedRoom.getNumber() != null) room.setNumber(patchedRoom.getNumber());
            if (patchedRoom.getAvailable() != null) room.setAvailable(patchedRoom.getAvailable());
            return roomRepository.save(room);
        });
    }

    public boolean deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }

    public List<Room> getAvailableRooms() {
        return roomJdbcRepository.findAllAvailableRooms();
    }
}