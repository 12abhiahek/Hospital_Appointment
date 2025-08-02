package com.hospital.hospital.controller;

import com.hospital.hospital.entity.Appointment;
import com.hospital.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

//    @PostMapping("/book")
//    public ResponseEntity<String> bookAppointment(@RequestBody Map<String, String> data) {
//        try {
//            Long doctorId = Long.parseLong(data.get("doctorId"));
//            Long patientId = Long.parseLong(data.get("patientId"));
//            LocalDateTime time = LocalDateTime.parse(data.get("appointmentTime"));
//
//            String response = appointmentService.bookAppointment(doctorId, patientId, time);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to  book appointment:" + e.getMessage());
//        }
//    }

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Map<String, String> data) {
        try {
            Long doctorId = Long.parseLong(data.get("doctorId"));
            Long patientId = Long.parseLong(data.get("patientId"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.parse(data.get("appointmentTime"), formatter);

            String response = appointmentService.bookAppointment(doctorId, patientId, time);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // log this
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to book appointment: " + e.getMessage());
        }
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getDoctorAppointments(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsForDoctor(doctorId);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getPatientAppointments(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsForPatient(patientId);
    }
}
