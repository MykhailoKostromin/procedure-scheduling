package com.caresyntax.web.controller;

import com.caresyntax.service.DoctorService;
import com.caresyntax.web.json.DoctorJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // get all patients
    @GetMapping("/api/v1/doctors")
    public ResponseEntity<Collection<DoctorJson>> findAll() {
        return ResponseEntity.ok(doctorService.findAll());
    }
}
