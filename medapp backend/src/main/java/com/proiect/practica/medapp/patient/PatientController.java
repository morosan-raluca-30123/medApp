package com.proiect.practica.medapp.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }


   @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patientDetails) {
        Optional<Patient> existingPatient = patientService.getPatientById(id);

        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            patient.setLastName(patientDetails.getLastName());
            patient.setFirstName(patientDetails.getFirstName());
            patient.setEmail(patientDetails.getEmail());
            patient.setBirthday(patientDetails.getBirthday());
            patient.setPhone(patientDetails.getPhone());
            patient.setAdress(patientDetails.getAdress());
            patient.setMedicalRecords(patientDetails.getMedicalRecords());
            Patient updatedPatient = patientService.savePatient(patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
