package com.caresyntax.service.impl;

import com.caresyntax.dao.entity.ProcedureEntity;
import com.caresyntax.dao.repository.DoctorRepository;
import com.caresyntax.dao.repository.PatientRepository;
import com.caresyntax.dao.repository.ProcedureRepository;
import com.caresyntax.dao.repository.RoomRepository;
import com.caresyntax.enums.Status;
import com.caresyntax.exception.NotFoundException;
import com.caresyntax.service.ProcedureService;
import com.caresyntax.web.json.PatientJson;
import com.caresyntax.web.json.DoctorJson;
import com.caresyntax.web.json.ProcedureJson;
import com.caresyntax.web.json.RoomJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public ProcedureJson save(ProcedureJson procedureJson) {
        ProcedureEntity procedureEntity = new ProcedureEntity();
        setPatient(procedureEntity, procedureJson);
        setDoctor(procedureEntity, procedureJson);
        setRoom(procedureEntity, procedureJson);
        procedureEntity.setDescription(procedureJson.getDescription());
        procedureEntity.setStatus(procedureJson.getStatus());
        procedureEntity.setPlannedStartTime(procedureJson.getPlannedStartTime());
        procedureEntity.setEstimatedEndTime(procedureJson.getEstimatedEndTime());
        ProcedureEntity savedProcedureEntity = procedureRepository.save(procedureEntity);
        ProcedureJson response = new ProcedureJson(savedProcedureEntity);
        return response;
    }

    @Override
    @Transactional
    public void updateProcedureStatus(Long procedureId, Status status) {
        ProcedureEntity procedureEntity = procedureRepository.findById(procedureId).orElseThrow(() -> new NotFoundException("Can't find procedure with id = " + procedureId));
        procedureEntity.setStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ProcedureJson> findAll() {
        return procedureRepository.findAll()
                .stream()
                .map(ProcedureJson::new)
                .collect(toList());
    }

    @Override
    @Transactional
    public void deleteById(Long procedureId) {
        procedureRepository.deleteById(procedureId);
    }

    private void setPatient(ProcedureEntity procedureEntity, ProcedureJson procedureJson) {
        Optional.of(procedureJson)
                .map(ProcedureJson::getPatient)
                .map(PatientJson::getId)
                .flatMap(this.patientRepository::findById)
                .ifPresent(procedureEntity::setPatientEntity);
    }

    private void setDoctor(ProcedureEntity procedureEntity, ProcedureJson procedureJson) {
        Optional.of(procedureJson)
                .map(ProcedureJson::getDoctor)
                .map(DoctorJson::getId)
                .flatMap(this.doctorRepository::findById)
                .ifPresent(procedureEntity::setDoctorEntity);
    }

    private void setRoom(ProcedureEntity procedureEntity, ProcedureJson procedureJson) {
        Optional.of(procedureJson)
                .map(ProcedureJson::getRoom)
                .map(RoomJson::getId)
                .flatMap(this.roomRepository::findById)
                .ifPresent(procedureEntity::setRoomEntity);
    }
}
