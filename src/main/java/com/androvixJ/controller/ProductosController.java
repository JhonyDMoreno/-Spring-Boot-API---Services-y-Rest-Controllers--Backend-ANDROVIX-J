package com.androvixJ.controller;

import com.androvixJ.model.Productos;
import com.androvixJ.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") //Para que el Frontend se conecte sin bloqueos de CORS
public class ProductosController
{
    @Autowired
    private ProductosService productosService;

    //1. OBTENER TODOS LOS PRODUCTOS (GET) -> http://localhost:8080/api/productos
    @GetMapping
    public List<Productos> obtenerTodos()
    {
        return productosService.obtenerTodos();
    }

    //2. OBTENER UN PRODUCTO POR ID (GET) -> http://localhost:8080/api/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerPorId(@PathVariable Long id)
    {
        return productosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //3. CREAR UN NUEVO PRODUCTO (POST) -> http://localhost:8080/api/productos
    @PostMapping
    public Productos crear(@RequestBody Productos producto)
    {
        return productosService.guardar(producto);
    }

    //4.ACTUALIZAR UN PRODUCTO (PUT) -> http://localhost:8080/api/productos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizar(@PathVariable Long id, @RequestBody Productos productoDetalles)
    {
        return productosService.obtenerPorId(id).map(productoExistente ->
        {
            productoExistente.setNombre(productoDetalles.getNombre());
            productoExistente.setDescripcion(productoDetalles.getDescripcion());
            productoExistente.setPrecio(productoDetalles.getPrecio());
            productoExistente.setStock(productoDetalles.getStock());
            productoExistente.setCategoria(productoDetalles.getCategoria());
            productoExistente.setImagenUrl(productoDetalles.getImagenUrl());

            Productos productoActualizado = productosService.guardar(productoExistente);
            return ResponseEntity.ok(productoActualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    //5. ELIMINAR UN PRODUCTO (DELETE) -> http://localhost:8080/api/productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id)
    {
        if (productosService.obtenerPorId(id).isPresent())
        {
            productosService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
