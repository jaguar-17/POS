package com.solano.pos.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "negocio_id", nullable = false)
    @NotNull(message = "El negocio es obligatorio")
    private Negocio negocio;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime fechaRegistro;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;
}
