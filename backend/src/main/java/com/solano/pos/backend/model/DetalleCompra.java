package com.solano.pos.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "detalle_compras")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad es obligatoria")
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidad;

    // Costo al que compraste este producto específico esta vez
    @NotNull(message = "El costo unitario es obligatorio")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;
}
