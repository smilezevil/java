package com.hotel.repository;

import com.hotel.model.Guest;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class GuestRepository {
    private final Map<Long, Guest> guests = new HashMap<>();
    private long nextId = 1;

    public List<Guest> findAll() {
        return new ArrayList<>(guests.values());
    }

    public Optional<Guest> findById(Long id) {
        return Optional.ofNullable(guests.get(id));
    }

    public Guest save(Guest guest) {
        if (guest.getId() == null) {
            guest.setId(nextId++);
        }
        guests.put(guest.getId(), guest);
        return guest;
    }

    public void deleteAll() {
        guests.clear();
    }

    public void deleteById(Long id) {
        guests.remove(id);
    }
}