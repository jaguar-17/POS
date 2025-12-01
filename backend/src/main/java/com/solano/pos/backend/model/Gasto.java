package com.solano.pos.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String codigo;

    @ManyToOne
    @NotBlank(message = "La categoría de gasto es obligatoria")
    @NotNull(message = "La categoría de gasto es obligatoria")
    @JoinColumn(name = "categoria_gasto_id")
    private CategoriaGasto categoriaGasto;

    private String descripcion;

    @NotNull(message = "El monto es obligatorio")
    @Column(nullable = false)
    private BigDecimal monto;

    @NotNull(message = "La fecha es obligatoria")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToOne
    @NotNull(message = "El usuario es obligatorio")
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
