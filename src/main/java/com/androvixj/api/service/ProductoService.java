package com.androvixj.api.service;

import com.androvixj.api.model.CategoriaProducto;
import com.androvixj.api.model.Producto;
import com.androvixj.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ─── Listar todos ────────────────────────────────────────────────────────

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // ─── Buscar por ID ───────────────────────────────────────────────────────

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // ─── Buscar por categoría ─────────────────────────────────────────────────

    public List<Producto> findByCategoria(CategoriaProducto categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // ─── Buscar por nombre ────────────────────────────────────────────────────

    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // ─── Crear ───────────────────────────────────────────────────────────────

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────

    public Producto update(Long id, Producto datos) {
        Producto existente = productoRepository.findById(id).orElse(null);
        if (existente == null) return null;

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        existente.setCategoria(datos.getCategoria());
        existente.setImagenUrl(datos.getImagenUrl());

        return productoRepository.save(existente);
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────

    public boolean delete(Long id) {
        if (!productoRepository.existsById(id)) return false;
        productoRepository.deleteById(id);
        return true;
    }
}
