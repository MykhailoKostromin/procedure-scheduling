package com.caresyntax.web.controller;

import com.caresyntax.ApplicationIT;
import com.caresyntax.dao.entity.PatientEntity;
import com.caresyntax.dao.repository.PatientRepository;
import com.caresyntax.enums.Sex;
import com.caresyntax.web.json.PatientJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerIT extends ApplicationIT {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findByIdTest() throws Exception {
        MockHttpServletRequestBuilder request = get("/api/v1/patients/1");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Linda Cardellini")))
                .andExpect(jsonPath("$.sex", is("female")))
                .andExpect(jsonPath("$.dayOfBirth", is("1990-09-10T00:00:00Z")));
    }

    @Test
    public void findByWrongIdTest() throws Exception {
        MockHttpServletRequestBuilder request = get("/api/v1/patients/123");
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPatientTest() throws Exception {

        // given
        MockHttpServletRequestBuilder request = post("/api/v1/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());

        // when
        String response = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Long id = objectMapper.readValue(response, PatientJson.class).getId();

        // then
        assertThat(id).isNotNull();
        PatientEntity patientEntity = patientRepository.findById(id).get();
        assertThat(patientEntity.getName()).isEqualTo("Tom Robinson");
        assertThat(patientEntity.getSex()).isEqualTo(Sex.male);
        assertThat(patientEntity.getDayOfBirth()).isEqualTo("1980-01-17T00:00:00Z");
    }

    @Test
    public void addPatientWithoutNameTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/api/v1/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findAllTest() throws Exception {
        MockHttpServletRequestBuilder request = get("/api/v1/patients");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(5)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Linda Cardellini")))
                .andExpect(jsonPath("$[0].sex", is("female")))
                .andExpect(jsonPath("$[0].dayOfBirth", is("1990-09-10T00:00:00Z")));
    }
}