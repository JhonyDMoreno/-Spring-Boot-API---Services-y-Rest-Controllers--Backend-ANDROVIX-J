package com.androvixJ.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data //Lombok: Crea los Getters, Setters y toString en silencio para no llenar esto de texto
@NoArgsConstructor //Constructor vacío obligatorio para que JPA funcione
@AllArgsConstructor //Constructor con todos los campos
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 30)
    private String rol;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    //Creando la fecha y hora exacta en tu Postgres cuando se registre un usuario nuevo
    @PrePersist
    protected void onCreate()
    {
        this.fechaRegistro = LocalDateTime.now();
    }
}
