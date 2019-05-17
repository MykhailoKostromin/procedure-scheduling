package com.caresyntax.web.json;

import com.caresyntax.dao.entity.DoctorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorJson {

    private Long id;
    @NotBlank
    private String name;

    public DoctorJson(DoctorEntity doctorEntity) {
        this.id = doctorEntity.getId();
        this.name = doctorEntity.getName();
    }
}
