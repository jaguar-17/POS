package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.CategoriaGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaGastoRepository extends JpaRepository<CategoriaGasto, Long> {
    List<CategoriaGasto> findByNegocioId(Long negocioId);
}
