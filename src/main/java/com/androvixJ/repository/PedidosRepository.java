package com.androvixJ.repository;

import com.androvixJ.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long>
{
    // Listo el puente automático con la tabla de pedidos de Postgres
}
