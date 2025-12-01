package com.solano.pos.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solano.pos.backend.model.enums.UnidadMedida;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(unique = true)
    private String codigoBarras;

    @ManyToOne
    @JoinColumn(name = "categoria_producto_id")
    private CategoriaProducto categoriaProducto;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private String imagenUrl;

    @NotNull(message = "El stock actual es obligatorio")
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal stockActual;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal stockMinimo;

    // --- SECCIÓN DE PRECIOS UNITARIOS (MODIFICADO) ---

    @NotNull(message = "El precio de compra no puede estar vacío")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @NotNull(message = "El precio de venta unitario no puede estar vacío")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta; // Este es el precio por KILO o por UNIDAD SUELTA

    // --- SECCIÓN DE LOGICA MAYORISTA / GRANEL (NUEVO) ---

    // NUEVO: Define si el producto se vende por peso (true) o unidad (false)
    @Column(nullable = false)
    private boolean esGranel; // true = Arroz, Azucar | false = Aceite, Gaseosa

    // NUEVO: Usa tu Enum para saber la unidad base (KILO, UNIDAD, LITRO)
    @Enumerated(EnumType.STRING)
    private UnidadMedida unidadMedida;

    // NUEVO: Precio especial si se lleva la caja cerrada o el saco
    @Column(precision = 10, scale = 2)
    private BigDecimal precioVentaMayorista;

    // NUEVO: Cuántas unidades trae la caja/saco (Ej: 6 para aceite, 50 para arroz)
    // Se usa para descontar del stock correctamente
    @Column(precision = 10, scale = 3)
    private BigDecimal factorConversion;

    @ManyToOne
    @NotNull(message = "El proveedor es obligatorio")
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime fechaRegistro;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;
}
