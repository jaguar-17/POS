package com.solano.pos.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleCompraRequestDTO {
    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotNull
    @Positive
    private BigDecimal cantidad;

    @NotNull(message = "El costo unitario es obligatorio")
    @Positive
    private BigDecimal costoUnitario; // A cuánto lo compraste esta vez
}
