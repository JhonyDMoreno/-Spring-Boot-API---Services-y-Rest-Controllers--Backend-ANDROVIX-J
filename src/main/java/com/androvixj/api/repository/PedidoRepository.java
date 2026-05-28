package com.androvixj.api.repository;

import com.androvixj.api.model.EstadoPedido;
import com.androvixj.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Todos los pedidos de un usuario específico
    List<Pedido> findByUsuarioIdUsuario(Long idUsuario);

    // Pedidos por estado (PENDIENTE, PROCESANDO, CANCELADA, ENVIADA)
    List<Pedido> findByEstado(EstadoPedido estado);
}