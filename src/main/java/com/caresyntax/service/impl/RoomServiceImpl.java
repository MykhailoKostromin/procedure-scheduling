package com.caresyntax.service.impl;

import com.caresyntax.dao.repository.RoomRepository;
import com.caresyntax.service.RoomService;
import com.caresyntax.web.json.RoomJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public Collection<RoomJson> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(RoomJson::new)
                .collect(toList());
    }
}
