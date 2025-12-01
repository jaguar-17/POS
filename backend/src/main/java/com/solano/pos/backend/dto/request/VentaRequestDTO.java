package com.solano.pos.backend.dto.request;

import com.solano.pos.backend.model.enums.MetodoPago;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VentaRequestDTO {
    @NotNull(message = "El ID del cliente no puede ser nulo")
    private Long usuarioId;

    @NotNull(message = "El método de pago no puede ser nulo")
    private MetodoPago metodoPago;

    private BigDecimal descuento;

    @NotEmpty(message = "La venta debe contener al menos un detalle")
    @Valid
    private List<DetalleVentaRequestDTO> detalles;
}