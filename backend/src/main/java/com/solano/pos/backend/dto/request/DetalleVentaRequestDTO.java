package com.solano.pos.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleVentaRequestDTO {
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private BigDecimal cantidad;

    // Opcional: Si permites cambiar el precio manualmente en caja.
    // Si viene nulo, el backend usará el precio oficial del producto.
    private BigDecimal precioUnitarioPactado;

    // Importante para tu lógica de mayorista
    private boolean esVentaMayorista; // true = vendió caja cerrada
}
