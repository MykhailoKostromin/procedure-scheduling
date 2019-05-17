package com.caresyntax.service.impl;

import com.caresyntax.dao.repository.DoctorRepository;
import com.caresyntax.service.DoctorService;
import com.caresyntax.web.json.DoctorJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    @Transactional
    public Collection<DoctorJson> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorJson::new)
                .collect(toList());
    }
}
