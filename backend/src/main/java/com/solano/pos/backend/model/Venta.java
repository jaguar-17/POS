package com.solano.pos.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solano.pos.backend.model.enums.MetodoPago;
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
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String codigo;

    private BigDecimal descuento;

    private BigDecimal total;

    @NotBlank(message = "El método de pago no puede estar vacío")
    @NotNull(message = "El método de pago no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("venta")
    private List<DetalleVenta> detalles;

    @ManyToOne
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
