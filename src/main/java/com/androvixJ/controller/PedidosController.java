package com.androvixJ.controller;

import com.androvixJ.model.Pedidos;
import com.androvixJ.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*") //Para conectar con el frontend sin líos de permisos
public class PedidosController
{
    @Autowired
    private PedidosService pedidosService;

    //1. OBTENER TODOS LOS PEDIDOS DE LA TIENDA (GET) -> http://localhost:8080/api/pedidos
    @GetMapping
    public List<Pedidos> obtenerTodos()
    {
        return pedidosService.obtenerTodos();
    }

    //2. BUSCAR UN PEDIDO POR SU ID (GET) -> http://localhost:8080/api/pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> obtenerPorId(@PathVariable Long id)
    {
        return pedidosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //3. CREAR O REGISTRAR UN NUEVO PEDIDO (POST) -> http://localhost:8080/api/pedidos
    @PostMapping
    public Pedidos crear(@RequestBody Pedidos pedido)
    {
        return pedidosService.guardar(pedido);
    }

    //4. ACTUALIZAR EL ESTADO O DETALLES DE UN PEDIDO (PUT) -> http://localhost:8080/api/pedidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> actualizar(@PathVariable Long id, @RequestBody Pedidos pedidosDetalles) {
        return pedidosService.obtenerPorId(id).map(pedidoExistente ->
        {
            pedidoExistente.setEstado(pedidosDetalles.getEstado());
            pedidoExistente.setTotal(pedidosDetalles.getTotal());
            // Nota: La fecha y el usuario usualmente no se cambian, pero se mantiene la relación
            if (pedidosDetalles.getUsuario() != null) {
                pedidoExistente.setUsuario(pedidosDetalles.getUsuario());
            }
            Pedidos pedidoActualizado = pedidosService.guardar(pedidoExistente);
            return ResponseEntity.ok(pedidoActualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    //5. ELIMINAR O CANCELAR UN PEDIDO (DELETE) -> http://localhost:8080/api/pedidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id)
    {
        if (pedidosService.obtenerPorId(id).isPresent())
        {
            pedidosService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
