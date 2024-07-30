package com.proiect.practica.medapp.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    public Optional<Appointments> getAppointmentById(Integer id) {
        return appointmentsRepository.findById(id);
    }

    public Appointments saveAppointment(Appointments appointment) {
        return appointmentsRepository.save(appointment);
    }

    public void deleteAppointment(Integer id) {
        appointmentsRepository.deleteById(id);
    }
}
