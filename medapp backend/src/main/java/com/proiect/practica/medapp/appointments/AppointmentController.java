package com.proiect.practica.medapp.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentsService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentsService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Integer id) {
        Optional<Appointment> appointment = appointmentsService.getAppointmentById(id);
        return appointment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentsService.saveAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointmentDetails) {
        Optional<Appointment> existingAppointment = appointmentsService.getAppointmentById(id);

        if (existingAppointment.isPresent()) {
            Appointment appointment = existingAppointment.get();
            appointment.setPatient(appointmentDetails.getPatient());
            appointment.setDoctor(appointmentDetails.getDoctor());
            appointment.setReason(appointmentDetails.getReason());
            appointment.setStatus(appointmentDetails.getStatus());
            //appointmentDate is not updated as it is marked updatable = false
            Appointment updatedAppointment = appointmentsService.saveAppointment(appointment);
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAppointment(@PathVariable Integer id) {
        appointmentsService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
