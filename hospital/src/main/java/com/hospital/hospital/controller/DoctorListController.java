package com.hospital.hospital.controller;

import com.hospital.hospital.entity.DoctorList;
import com.hospital.hospital.repository.DoctorListRepository;
import com.hospital.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doctors")
public class DoctorListController {
    @Autowired
    private DoctorService doctorService;

    // Get all doctors
    @GetMapping
    public List<DoctorList> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public Optional<DoctorList> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    // Add new doctor
    @PostMapping("/add")
    public DoctorList addDoctor(@RequestBody DoctorList doctor) {
        return doctorService.addDoctor(doctor);
    }

    // Update doctor
    @PutMapping("/{id}")
    public DoctorList updateDoctor(@PathVariable Long id, @RequestBody DoctorList doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

}
