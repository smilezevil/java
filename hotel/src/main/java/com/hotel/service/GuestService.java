package com.hotel.service;

import com.hotel.model.Guest;
import com.hotel.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Optional<Guest> getGuestById(Long id) {
        return guestRepository.findById(id);
    }

    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Optional<Guest> updateGuest(Long id, Guest updatedGuest) {
        return guestRepository.findById(id).map(guest -> {
            guest.setName(updatedGuest.getName());
            guest.setEmail(updatedGuest.getEmail());
            guest.setPhone(updatedGuest.getPhone());
            return guestRepository.save(guest);
        });
    }

    public Optional<Guest> patchGuest(Long id, Guest patchedGuest) {
        return guestRepository.findById(id).map(guest -> {
            if (patchedGuest.getName() != null) guest.setName(patchedGuest.getName());
            if (patchedGuest.getEmail() != null) guest.setEmail(patchedGuest.getEmail());
            if (patchedGuest.getPhone() != null) guest.setPhone(patchedGuest.getPhone());
            return guestRepository.save(guest);
        });
    }

    public void deleteAllGuests() {
        guestRepository.deleteAll();
    }

    public boolean deleteGuest(Long id) {
        if (guestRepository.findById(id).isPresent()) {
            guestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}