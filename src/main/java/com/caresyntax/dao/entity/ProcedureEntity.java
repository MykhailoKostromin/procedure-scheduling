package com.caresyntax.dao.entity;

import com.caresyntax.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROCEDURE")
public class ProcedureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private PatientEntity patientEntity;
    @OneToOne
    @JoinColumn(name = "DOCTOR_ID")
    private DoctorEntity doctorEntity;
    @OneToOne
    @JoinColumn(name = "ROOM_ID")
    private RoomEntity roomEntity;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;
    @Column(name = "PLANNED_START_TIME", nullable = false)
    private Instant plannedStartTime;
    @Column(name = "ESTIMATED_END_TIME")
    private Instant estimatedEndTime;
}
