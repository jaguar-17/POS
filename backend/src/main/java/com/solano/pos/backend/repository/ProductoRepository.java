package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    // Validaciones únicas por negocio
    boolean existsByCodigoAndNegocioId(String codigo, Long negocioId);
    boolean existsByCodigoBarrasAndNegocioId(String codigoBarras, Long negocioId);

    // Búsquedas específicas
    // Buscar por código
    Optional<Producto> findByCodigoAndNegocioId(String codigo, Long negocioId);

    // Buscar por código de barras
    Optional<Producto> findByCodigoBarrasAndNegocioId(String codigoBarras, Long negocioId);

    // Buscador general (por nombre)
    List<Producto> findByNegocioIdAndNombreContainingIgnoreCase(Long negocioId, String nombre);

    // Buscar por categoríaz
    List<Producto> findByNegocioIdAndCategoriaProductoId(Long negocioId, Long categoriaId);

    // QUERY ESPECIAL: Productos con Stock Bajo (Alerta)
    @Query("SELECT p FROM Producto p WHERE p.negocio.id = :negocioId AND p.stockActual <= p.stockMinimo")
    List<Producto> findProductosBajoStock(Long negocioId);
}
