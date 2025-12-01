package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KardexRepository extends JpaRepository<Kardex,Long> {
    // Obtener historial de un producto específico en un negocio
    List<Kardex> findByProductoIdAndNegocioIdOrderByFechaDesc(Long productoId, Long negocioId);

    // Obtener movimientos dentro de un rango de fechas para un negocio
    List<Kardex> findByNegocioIdAndFechaBetweenOrderByFechaDesc(Long negocioId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Obtener movimientos dentro de un rango de fechas para un producto específico en un negocio
    List<Kardex> findByProductoIdAndNegocioIdAndFechaBetweenOrderByFechaDesc(Long productoId, Long negocioId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
