package com.caresyntax.service;

import com.caresyntax.web.json.DoctorJson;

import java.util.Collection;

public interface DoctorService {

    Collection<DoctorJson> findAll();
}
