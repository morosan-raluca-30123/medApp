package com.proiect.practica.medapp.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentsRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentsRepository.findById(id);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentsRepository.save(appointment);
    }

    public void deleteAppointment(Integer id) {
        appointmentsRepository.deleteById(id);
    }
}
