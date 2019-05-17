package com.caresyntax.web.controller;

import com.caresyntax.service.PatientService;
import com.caresyntax.web.json.PatientJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    // get patient by id
    @GetMapping("/api/v1/patients/{id}")
    public ResponseEntity<PatientJson> findById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    // get all patients
    @GetMapping("/api/v1/patients")
    public ResponseEntity<Collection<PatientJson>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    // Adding patient
    @PostMapping("/api/v1/patients")
    public ResponseEntity<PatientJson> addPatient(@Valid @RequestBody PatientJson patientJson) {
        PatientJson body = patientService.addPatient(patientJson);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // delete patient
    @DeleteMapping("/api/v1/patients/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
