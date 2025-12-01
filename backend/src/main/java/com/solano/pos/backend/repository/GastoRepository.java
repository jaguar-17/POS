package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    List<Gasto> findByNegocioIdAndFechaRegistroBetween(Long negocioId, LocalDateTime inicio, LocalDateTime fin);
}
