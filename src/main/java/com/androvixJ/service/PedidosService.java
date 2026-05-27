package com.androvixJ.service;

import com.androvixJ.model.Pedidos;
import com.androvixJ.model.ProductoPedido;
import com.androvixJ.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidosService
{
    @Autowired
    private PedidosRepository pedidosRepository;

    public List<Pedidos> obtenerTodos()
    {
        return pedidosRepository.findAll();
    }

    public Optional<Pedidos> obtenerPorId(Long id)
    {
        return pedidosRepository.findById(id);
    }

    //Enlazando el pedido con los productos antes de guardar
    public Pedidos guardar(Pedidos pedido)
    {
        if (pedido.getDetalles() != null)
        {
            for (ProductoPedido detalle : pedido.getDetalles())
            {
                detalle.setPedido(pedido); //Vincula cada producto al pedido actual
            }
        }

        return pedidosRepository.save(pedido);
    }

    public void eliminar(Long id)
    {
        pedidosRepository.deleteById(id);
    }
}
