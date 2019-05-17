package com.caresyntax.service;

import com.caresyntax.web.json.RoomJson;

import java.util.Collection;

public interface RoomService {

    Collection<RoomJson> findAll();
}
