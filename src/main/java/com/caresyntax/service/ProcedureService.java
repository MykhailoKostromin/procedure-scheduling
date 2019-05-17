package com.caresyntax.service;

import com.caresyntax.enums.Status;
import com.caresyntax.web.json.ProcedureJson;

import java.util.Collection;

public interface ProcedureService {

    ProcedureJson save(ProcedureJson procedure);
    void updateProcedureStatus(Long procedureId, Status status);
    Collection<ProcedureJson> findAll();
    void deleteById(Long procedureId);
}
