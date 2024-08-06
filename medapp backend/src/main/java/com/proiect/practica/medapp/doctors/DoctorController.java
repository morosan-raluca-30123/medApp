package com.proiect.practica.medapp.doctors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorsService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorsService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer id) {
        Optional<Doctor> doctor = doctorsService.getDoctorById(id);
        return doctor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorsService.saveDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer id, @RequestBody Doctor doctorDetails) {
        Optional<Doctor> existingDoctor = doctorsService.getDoctorById(id);

        if (existingDoctor.isPresent()) {
            Doctor doctor = existingDoctor.get();
            doctor.setFirstName(doctorDetails.getFirstName());
            doctor.setLastName(doctorDetails.getLastName());
            doctor.setSpecialization(doctorDetails.getSpecialization());
            doctor.setPhone(doctorDetails.getPhone());
            doctor.setDescription(doctorDetails.getDescription());
            Doctor updatedDoctor = doctorsService.saveDoctor(doctor);
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