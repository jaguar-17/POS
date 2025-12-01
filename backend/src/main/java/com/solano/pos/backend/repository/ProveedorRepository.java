package com.solano.pos.backend.repository;

import com.solano.pos.backend.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
    List<Proveedor> findByNegocioId(Long negocioId);

    // Para buscar proveedores mientras escribes (autocomplete)
    List<Proveedor> findByNegocioIdAndNombreEmpresaContainingIgnoreCase(Long negocioId, String nombreEmpresa);
}
