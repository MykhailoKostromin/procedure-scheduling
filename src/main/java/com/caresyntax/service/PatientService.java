package com.caresyntax.service;

import com.caresyntax.web.json.PatientJson;

import java.util.Collection;

public interface PatientService {

    PatientJson findById(Long id);
    Collection<PatientJson> findAll();
    PatientJson addPatient(PatientJson patientJson);
    void deleteById(Long id);
}
