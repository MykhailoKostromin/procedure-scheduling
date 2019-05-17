package com.caresyntax.dao.repository;

import com.caresyntax.dao.entity.ProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<ProcedureEntity, Long> {
}
