package com.hotel.service;

import com.hotel.model.Staff;
import com.hotel.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Optional<Staff> updateStaff(Long id, Staff updatedStaff) {
        return staffRepository.findById(id).map(staff -> {
            staff.setName(updatedStaff.getName());
            staff.setPosition(updatedStaff.getPosition());
            staff.setSalary(updatedStaff.getSalary());
            return staffRepository.save(staff);
        });
    }

    public Optional<Staff> patchStaff(Long id, Staff patchedStaff) {
        return staffRepository.findById(id).map(staff -> {
            if (patchedStaff.getName() != null) staff.setName(patchedStaff.getName());
            if (patchedStaff.getPosition() != null) staff.setPosition(patchedStaff.getPosition());
            if (patchedStaff.getSalary() != 0) staff.setSalary(patchedStaff.getSalary());
            return staffRepository.save(staff);
        });
    }

    public void deleteAllStaff() {
        staffRepository.deleteAll();
    }

    public boolean deleteStaff(Long id) {
        if (staffRepository.findById(id).isPresent()) {
            staffRepository.deleteById(id);
            return true;
        }
        return false;
    }
}