package com.solano.pos.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class KardexResponseDTO {
    private LocalDateTime fecha;
    private String tipoMovimiento; // "VENTA", "COMPRA"
    private String documento;      // "Ticket #100"

    // Entradas
    private BigDecimal entradaCantidad;
    private BigDecimal entradaCosto;

    // Salidas
    private BigDecimal salidaCantidad;
    private BigDecimal salidaValor; // Opcional

    // Saldos
    private BigDecimal saldoCantidad;
    private BigDecimal saldoCostoTotal;
}
