package com.androvixJ.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data //Lombok para Getters, Setters y toString
@NoArgsConstructor //Constructor vacío para JPA
@AllArgsConstructor //Constructor con todos los campos
public class Productos
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT") //Para descripciones largas
    private String descripcion;

    //BigDecimal para evitar pérdida de centavos en la pasarela de pagos
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;
}
