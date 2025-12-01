package com.solano.pos.backend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompraRequestDTO {
    @NotNull(message = "El proveedor es obligatorio")
    private Long proveedorId;

    @NotNull(message = "El usuario que registra es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El número de comprobante es obligatorio")
    private String numeroComprobante; // Ej: F001-2342

    @NotBlank(message = "El tipo de comprobante es obligatorio")
    private String tipoComprobante; // FACTURA, BOLETA

    @NotEmpty(message = "La compra debe tener productos")
    @Valid
    private List<DetalleCompraRequestDTO> detalles;
}
