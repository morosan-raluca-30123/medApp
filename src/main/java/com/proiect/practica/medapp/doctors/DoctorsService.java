package com.proiect.practica.medapp.doctors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    public List<Doctors> getAllDoctors() {
        return doctorsRepository.findAll();
    }

    public Optional<Doctors> getDoctorById(Integer id) {
        return doctorsRepository.findById(id);
    }

    public Doctors saveDoctor(Doctors doctor) {
        return doctorsRepository.save(doctor);
    }

    public void deleteDoctor(Integer id) {
        doctorsRepository.deleteById(id);
    }
}
