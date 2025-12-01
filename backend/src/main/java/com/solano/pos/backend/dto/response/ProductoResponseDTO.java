package com.solano.pos.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductoResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String categoria; // Solo enviamos el nombre, no el objeto entero
    private BigDecimal stockActual;
    private BigDecimal precioVenta;
    private String unidadMedida;
    private String imagenUrl;
}
