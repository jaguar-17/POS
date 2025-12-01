package com.solano.pos.backend.model;

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
@Table(name = "mermas")
public class Merma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String codigo;

    // --- CORRECCIÓN CRÍTICA ---
    // Cambiado de Integer a BigDecimal para soportar mermas de granel (0.5 kg de arroz derramado)
    @NotNull(message = "La cantidad no puede estar vacía")
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidad;

    // --- NUEVOS CAMPOS FINANCIEROS (IMPORTANTE) ---

    // Guardamos cuánto costaba el producto cuando se rompió/venció.
    // Esto es vital para saber cuánto DINERO perdió el negocio, no solo cuántas unidades.
    @Column(precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalPerdida; // cantidad * costoUnitario

    @ManyToOne
    @NotNull(message = "El motivo de la merma es obligatorio")
    @JoinColumn(name = "motivo_merma_id")
    private MotivoMerma motivoMerma;

    @ManyToOne
    @NotNull(message = "El producto es obligatorio")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @NotNull(message = "El usuario es obligatorio")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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
