package com.androvixj.api.repository;

import com.androvixj.api.model.ProductoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Long> {

    // Todos los items de un pedido específico
    List<ProductoPedido> findByPedidoIdPedido(Long idPedido);

    // Todas las apariciones de un producto en pedidos
    List<ProductoPedido> findByProductoIdProducto(Long idProducto);
}
