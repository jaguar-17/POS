package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Compra;
import com.solano.pos.backend.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Venta, Long> {
    Optional<Compra> findByCodigoAndNegocioId(String codigo, Long negocioId);
    List<Compra> findByNegocioIdAndFechaCompraBetween(Long negocioId, LocalDateTime inicio, LocalDateTime fin);
    List<Compra> findByNegocioIdAndProveedorId(Long negocioId, Long proveedorId);
}
