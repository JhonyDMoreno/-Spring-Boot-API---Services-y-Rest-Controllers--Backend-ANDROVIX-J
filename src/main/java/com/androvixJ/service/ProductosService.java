package com.androvixJ.service;

import com.androvixJ.model.Productos;
import com.androvixJ.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService
{
    @Autowired
    private ProductosRepository productosRepository;

    //Listar todos los productos disponibles
    public List<Productos> obtenerTodos()
    {
        return productosRepository.findAll();
    }

    //Buscar un producto específico por su ID
    public Optional<Productos> obtenerPorId(Long id)
    {
        return productosRepository.findById(id);
    }

    //Guardar o actualizar un producto (Crear/Editar)
    public Productos guardar(Productos producto)
    {
        return productosRepository.save(producto);
    }

    //Eliminar un producto del inventario
    public void eliminar(Long id)
    {
        productosRepository.deleteById(id);
    }
}
