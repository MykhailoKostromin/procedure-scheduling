package com.caresyntax.service.impl;

import com.caresyntax.dao.entity.PatientEntity;
import com.caresyntax.dao.repository.PatientRepository;
import com.caresyntax.exception.NotFoundException;
import com.caresyntax.service.PatientService;
import com.caresyntax.web.json.PatientJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional(readOnly = true)
    public PatientJson findById(Long id) {
        Optional<PatientEntity> patientEntityOptional = patientRepository.findById(id);
        if (patientEntityOptional.isPresent()) {
            return new PatientJson(patientEntityOptional.get());
        }
        throw new NotFoundException();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PatientJson> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(PatientJson::new)
                .collect(toList());
    }

    @Override
    @Transactional
    public PatientJson addPatient(PatientJson patientJson) {
        PatientEntity patientEntity = patientRepository.saveAndFlush(new PatientEntity(patientJson)); // flush invokes validation
        PatientJson response = new PatientJson(patientEntity);
        return response;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}
