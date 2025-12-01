package com.solano.pos.backend.dto.request;

import com.solano.pos.backend.model.enums.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimientoStockRequestDTO {
    @NotNull
    private Long productoId;

    @NotNull
    private Long usuarioId; // Quién hace el ajuste

    @NotNull
    @Positive
    private BigDecimal cantidad;

    @NotNull
    private TipoMovimiento tipoMovimiento; // SALIDA_CONSUMO_INTERNO, ENTRADA_AJUSTE, etc.

    private String observacion; // "Se rompió", "Regalo corporativo", etc.
}
