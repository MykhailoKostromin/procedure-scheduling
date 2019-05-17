package com.caresyntax.web.json;

import com.caresyntax.dao.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomJson {

    private Long id;
    @NotBlank
    private String name;

    public RoomJson(RoomEntity roomEntity) {
        this.id = roomEntity.getId();
        this.name = roomEntity.getName();
    }
}
