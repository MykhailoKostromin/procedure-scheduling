package com.caresyntax.service.impl;

import com.caresyntax.ApplicationIT;
import com.caresyntax.service.PatientService;
import com.caresyntax.web.json.PatientJson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PatientServiceImplIT extends ApplicationIT {

    @Autowired
    private PatientService patientService;

    @Test
    public void addPatientWithoutNameTest() {

        // given
        PatientJson patientJson = new PatientJson();

        // when
        Throwable throwable = catchThrowable(() -> patientService.addPatient(patientJson));

        // then
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }
}