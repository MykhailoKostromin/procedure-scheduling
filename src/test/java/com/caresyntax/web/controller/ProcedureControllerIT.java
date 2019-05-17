package com.caresyntax.web.controller;

import com.caresyntax.ApplicationIT;
import com.caresyntax.dao.entity.ProcedureEntity;
import com.caresyntax.dao.repository.ProcedureRepository;
import com.caresyntax.enums.Status;
import com.caresyntax.web.json.PatientJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProcedureControllerIT  extends ApplicationIT {

    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void scheduleProcedureTest() throws Exception {

        // given
        MockHttpServletRequestBuilder request = post("/api/v1/procedures")
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
        ProcedureEntity procedureEntity = procedureRepository.findById(id).get();
        assertThat(procedureEntity.getDescription()).isEqualTo("physical examination (propaedeutic procedures)");
    }

    @Test
    public void updateProcedureStatusTest() throws Exception {

        // given
        MockHttpServletRequestBuilder createProcedure = post("/api/v1/procedures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("ProcedureControllerIT.scheduleProcedureTest.json"));

        // when
        String response = mockMvc.perform(createProcedure)
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Long procedureId = objectMapper.readValue(response, PatientJson.class).getId();
        MockHttpServletRequestBuilder updateProcedureStatus = patch("/api/v1/procedures/" + procedureId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());
        mockMvc.perform(updateProcedureStatus).andExpect(status().isOk());

        // then
        ProcedureEntity procedureEntity = procedureRepository.findById(procedureId).get();
        assertThat(procedureEntity.getStatus()).isEqualTo(Status.Finished);
    }
}