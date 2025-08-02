package com.hospital.hospital.service;

import com.hospital.hospital.entity.Appointment;
import com.hospital.hospital.entity.DoctorList;
import com.hospital.hospital.entity.User;
import com.hospital.hospital.repository.AppointmentRepository;
import com.hospital.hospital.repository.DoctorListRepository;
import com.hospital.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private DoctorListRepository doctorListRepository;

    @Autowired
    private UserRepository userRepository;

    public String bookAppointment(Long doctorId, Long patientId, LocalDateTime appointmentTime) {
        Optional<DoctorList> doctorOpt = doctorListRepository.findById(doctorId);
        Optional<User> patientOpt = userRepository.findById(patientId);

        if (doctorOpt.isEmpty() || patientOpt.isEmpty()) {
            return "Doctor or Patient not found.";
        }

        // Check if slot already booked
        boolean alreadyBooked = AppointmentRepository.existsByDoctorIdAndAppointmentTime(doctorId, appointmentTime);
        if (alreadyBooked) {
            return "This time slot is already booked.";
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctorOpt.get());
        appointment.setPatient(patientOpt.get());
        appointment.setAppointmentTime(appointmentTime);
        appointment.setDate(appointmentTime.toLocalDate());
        appointment.setStatus("BOOKED");

        appointmentRepo.save(appointment);
        return "Appointment successfully booked!";
    }

    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }
}
