package com.androvixJ.service;

import com.androvixJ.model.Pedidos;
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

    //1. Obtener todos los pedidos realizados en la tienda
    public List<Pedidos> obtenerTodos()
    {
        return pedidosRepository.findAll();
    }

    //2. Buscar un pedido específico por su ID
    public Optional<Pedidos> obtenerPorId(Long id)
    {
        return pedidosRepository.findById(id);
    }

    //3. Crear o actualizar un pedido
    public Pedidos guardar(Pedidos pedido)
    {
        return pedidosRepository.save(pedido);
    }

    //4. Cancelar o eliminar un pedido del sistema
    public void eliminar(Long id)
    {
        pedidosRepository.deleteById(id);
    }
}
