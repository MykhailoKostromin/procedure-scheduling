package com.caresyntax.web.controller;

import com.caresyntax.service.ProcedureService;
import com.caresyntax.web.json.ProcedureJson;
import com.caresyntax.web.json.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    // get all procedures
    @GetMapping("/api/v1/procedures")
    public ResponseEntity<Collection<ProcedureJson>> findAll() {
        return ResponseEntity.ok(procedureService.findAll());
    }

    // Schedule procedure
    @PostMapping("/api/v1/procedures")
    public ResponseEntity<ProcedureJson> scheduleProcedure(@Valid @RequestBody ProcedureJson procedure) {
        return new ResponseEntity(procedureService.save(procedure), HttpStatus.CREATED);
    }

    // Updating status of the procedure
    @PatchMapping("/api/v1/procedures/{id}")
    public ResponseEntity<?> updateProcedureStatus(@PathVariable Long id, @RequestBody StatusJson statusJson) {
        procedureService.updateProcedureStatus(id, statusJson.getStatus());
        return new ResponseEntity(HttpStatus.OK);
    }

    // delete patient
    @DeleteMapping("/api/v1/procedures/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        procedureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
