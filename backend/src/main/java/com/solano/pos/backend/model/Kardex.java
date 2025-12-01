package com.solano.pos.backend.model;

import com.solano.pos.backend.model.enums.TipoMovimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kardex")
public class Kardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- 1. DATOS DEL MOVIMIENTO ---

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipoMovimiento;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime fechaMovimiento;

    // Referencia opcional: "Compra #1024", "Ticket #505", "Ajuste Mensual"
    private String documentoReferencia;

    // --- 2. RELACIONES CON TUS ENTIDADES ---
    // Hacemos relaciones opcionales (nullable=true) porque un registro de Kardex
    // puede venir de una Compra OR una Venta OR una Merma, pero no de las tres a la vez.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;

    // Opcionales: Para trazabilidad estricta (puedes navegar a la fuente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detalle_compra_id")
    private DetalleCompra detalleCompra; // Se llena si es ENTRADA_COMPRA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detalle_venta_id")
    private DetalleVenta detalleVenta;   // Se llena si es SALIDA_VENTA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merma_id")
    private Merma merma;                 // Se llena si es SALIDA_MERMA

    // --- 3. VALORES FÍSICOS (UNIDADES) ---

    @NotNull
    @Column(precision = 10, scale = 3, nullable = false)
    private BigDecimal cantidad; // La cantidad que entró o salió (siempre positiva aquí, el signo lo da el tipo)

    @NotNull
    @Column(precision = 10, scale = 3, nullable = false)
    private BigDecimal stockAnterior; // Cuánto había ANTES de este movimiento

    @NotNull
    @Column(precision = 10, scale = 3, nullable = false)
    private BigDecimal stockResultante; // Cuánto quedó DESPUÉS (El valor que se guardará en Producto.stockActual)

    // --- 4. VALORES MONETARIOS (VALORIZACIÓN) ---

    // Costo unitario del producto en el momento de la transacción.
    // En compras: es el precio de compra.
    // En ventas: suele ser el Costo Promedio o el último precio de compra (para calcular ganancia).
    @Column(precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalMovimiento; // cantidad * costoUnitario

    // --- LOGICA DE LLENADO (Idea para tu Service) ---
    // Cuando guardes un Kardex, calculas:
    // 1. stockAnterior = producto.getStockActual()
    // 2. Si es entrada: stockResultante = stockAnterior + cantidad
    // 3. Si es salida: stockResultante = stockAnterior - cantidad
}
