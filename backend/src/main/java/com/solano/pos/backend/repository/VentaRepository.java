package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    Optional<Venta> findByCodigoAndNegocioId(String codigo, Long negocioId);

    // Reporte de ventas por fecha
    List<Venta> findByNegocioIdAndFechaRegistroBetween(Long negocioId, LocalDateTime inicio, LocalDateTime fin);

    // Ventas hechas por un usuario específico (Corte de caja por cajero)
    List<Venta> findByNegocioIdAndUsuarioIdAndFechaRegistroBetween(Long negocioId, Long usuarioId, LocalDateTime inicio, LocalDateTime fin);
}
