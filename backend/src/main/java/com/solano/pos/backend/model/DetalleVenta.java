package com.solano.pos.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalle_ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La cantidad no puede estar vacía")
    @Column(nullable = false)
    private BigDecimal cantidad;

    // Bandera para saber si se aplicó precio mayorista (Caja cerrada) o normal
    // Útil para reportes y devoluciones
    @Column(nullable = false)
    private boolean esMayorista;

    @NotNull(message = "El precio unitario es obligatorio")
    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @NotNull(message = "El subtotal es obligatorio")
    @Column(precision = 10, scale = 2)
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;
}
