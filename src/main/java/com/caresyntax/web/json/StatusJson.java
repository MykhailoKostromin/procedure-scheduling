package com.caresyntax.web.json;

import com.caresyntax.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusJson {

    @NotNull
    private Status status;
}
