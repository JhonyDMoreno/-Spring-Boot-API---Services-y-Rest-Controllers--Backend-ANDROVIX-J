package com.androvixJ.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "producto_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPedido
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_pedido")
    private Long idProductoPedido;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    // Relación con el Pedido al que pertenece
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedidos pedido;

    // Relación con el Producto que se compró
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;
}
