package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Merma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MermaRepository extends JpaRepository<Merma, Long> {
    List<Merma> findByNegocioId(Long negocioId);
    List<Merma> findByNegocioIdAndFechaRegistroBetween(Long negocioId, LocalDateTime inicio, LocalDateTime fin);
}
