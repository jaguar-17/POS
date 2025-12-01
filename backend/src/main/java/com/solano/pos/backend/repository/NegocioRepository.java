package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegocioRepository extends JpaRepository<Negocio, Long> {
    boolean existsByRuc(String ruc);
}
