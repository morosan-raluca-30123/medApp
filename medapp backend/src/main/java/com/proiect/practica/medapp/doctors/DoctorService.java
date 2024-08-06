package com.proiect.practica.medapp.doctors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorsRepository;

    public List<Doctor> getAllDoctors() {
        return doctorsRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Integer id) {
        return doctorsRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorsRepository.save(doctor);
    }

    public void deleteDoctor(Integer id) {
        doctorsRepository.deleteById(id);
    }
}
