package com.solano.pos.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código de compra es obligatorio")
    @Column(nullable = false, unique = true)
    private String codigo;

    // El número de factura o boleta física que te da el proveedor
    @NotBlank(message = "El número de comprobante es obligatorio")
    private String numeroComprobante;

    // Puede ser BOLETA, FACTURA, GUIA_REMISION
    @NotBlank(message = "El tipo de comprobante es obligatorio")
    private String tipoComprobante;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCompra;

    // Estado: PAGADO, PENDIENTE (Credito), ANULADO
    private String estado;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCompra;

    // --- RELACIONES ---

    @NotNull(message = "El proveedor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // El empleado que registró la compra

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("compra")
    private List<DetalleCompra> detalles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;
}
