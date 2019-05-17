package com.caresyntax.web.json;

import com.caresyntax.dao.entity.PatientEntity;
import com.caresyntax.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientJson {

    private Long id;
    @NotBlank
    private String name;
    private Sex sex;
    private Instant dayOfBirth;

    public PatientJson(PatientEntity patientEntity) {
        this.id = patientEntity.getId();
        this.name = patientEntity.getName();
        this.sex = patientEntity.getSex();
        this.dayOfBirth = patientEntity.getDayOfBirth();
    }
}
