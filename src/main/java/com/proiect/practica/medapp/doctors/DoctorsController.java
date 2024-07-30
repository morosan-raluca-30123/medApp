package com.proiect.practica.medapp.doctors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

    @Autowired
    private DoctorsService doctorsService;

    @GetMapping
    public ResponseEntity<List<Doctors>> getAllDoctors() {
        List<Doctors> doctors = doctorsService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable Integer id) {
        Optional<Doctors> doctor = doctorsService.getDoctorById(id);
        return doctor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Doctors> createDoctor(@RequestBody Doctors doctor) {
        Doctors savedDoctor = doctorsService.saveDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctors> updateDoctor(@PathVariable Integer id, @RequestBody Doctors doctorDetails) {
        Optional<Doctors> existingDoctor = doctorsService.getDoctorById(id);

        if (existingDoctor.isPresent()) {
            Doctors doctor = existingDoctor.get();
            doctor.setDescription(doctorDetails.getDescription());
            Doctors updatedDoctor = doctorsService.saveDoctor(doctor);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Integer id) {
        doctorsService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}