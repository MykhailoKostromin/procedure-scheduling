package com.caresyntax.dao.entity;

import com.caresyntax.enums.Sex;
import com.caresyntax.web.json.PatientJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PATIENT")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SEX")
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(name = "DATE_OF_BIRTH")
    private Instant dayOfBirth;

    public PatientEntity(PatientJson patientJson) {
        this.id = patientJson.getId();
        this.name = patientJson.getName();
        this.sex = patientJson.getSex();
        this.dayOfBirth = patientJson.getDayOfBirth();
    }
}
