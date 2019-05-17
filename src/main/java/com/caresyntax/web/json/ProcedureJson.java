package com.caresyntax.web.json;

import com.caresyntax.dao.entity.ProcedureEntity;
import com.caresyntax.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureJson {

    private Long id;
    @NotNull
    private PatientJson patient;
    private DoctorJson doctor;
    private RoomJson room;
    @NotBlank
    private String description;
    @NotNull
    private Status status;
    @NotNull
    private Instant plannedStartTime;
    private Instant estimatedEndTime;

    public ProcedureJson(ProcedureEntity procedureEntity) {
        this.id = procedureEntity.getId();
        Optional.ofNullable(procedureEntity.getPatientEntity())
                .ifPresent(patientEntity -> this.patient = new PatientJson(patientEntity));
        Optional.ofNullable(procedureEntity.getDoctorEntity())
                .ifPresent(doctorEntity -> this.doctor = new DoctorJson(doctorEntity));
        Optional.ofNullable(procedureEntity.getRoomEntity())
                .ifPresent(roomEntity -> this.room = new RoomJson(roomEntity));
        this.description = procedureEntity.getDescription();
        this.status = procedureEntity.getStatus();
        this.plannedStartTime = procedureEntity.getPlannedStartTime();
        this.estimatedEndTime = procedureEntity.getEstimatedEndTime();
    }
}
