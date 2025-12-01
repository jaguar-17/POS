package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {
    List<CategoriaProducto> findByNegocioId(Long negocioId);
}
