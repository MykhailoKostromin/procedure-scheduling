package com.caresyntax.web.controller;

import com.caresyntax.service.RoomService;
import com.caresyntax.web.json.RoomJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    // get all patients
    @GetMapping("/api/v1/rooms")
    public ResponseEntity<Collection<RoomJson>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }
}
