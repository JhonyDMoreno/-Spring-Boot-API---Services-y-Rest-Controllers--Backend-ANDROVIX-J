package com.androvixj.api.repository;

import com.androvixj.api.model.CategoriaProducto;
import com.androvixj.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar productos por categoría
    List<Producto> findByCategoria(CategoriaProducto categoria);

    // Buscar productos con stock disponible
    List<Producto> findByStockGreaterThan(Integer stock);

    // Buscar por nombre (búsqueda parcial, sin distinción de mayúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
