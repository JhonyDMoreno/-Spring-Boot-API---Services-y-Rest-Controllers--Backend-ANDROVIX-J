package com.androvixj.api.controller;

import com.androvixj.api.dto.ProductoPedidoRequestDTO;
import com.androvixj.api.dto.ProductoPedidoResponseDTO;
import com.androvixj.api.service.ProductoPedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos por Pedido", description = "Gestión de items dentro de cada pedido")
@RestController
@RequestMapping("/producto-pedido")
public class ProductoPedidoController {

    private final ProductoPedidoService productoPedidoService;

    @Autowired
    public ProductoPedidoController(ProductoPedidoService productoPedidoService) {
        this.productoPedidoService = productoPedidoService;
    }

    // ─── GET /producto-pedido ─────────────────────────────────────────────────

    @Operation(summary = "Listar todos los items de pedidos")
    @GetMapping
    public ResponseEntity<List<ProductoPedidoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(productoPedidoService.findAll());
    }

    // ─── GET /producto-pedido/{id} ────────────────────────────────────────────

    @Operation(summary = "Buscar item de pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoPedidoResponseDTO> obtenerPorId(@PathVariable Long id) {
        ProductoPedidoResponseDTO resultado = productoPedidoService.findById(id);
        if (resultado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    // ─── GET /producto-pedido/pedido/{pedidoId} ───────────────────────────────

    @Operation(summary = "Listar todos los items de un pedido específico")
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<ProductoPedidoResponseDTO>> obtenerPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(productoPedidoService.findByPedido(pedidoId));
    }

    // ─── POST /producto-pedido ────────────────────────────────────────────────

    @Operation(summary = "Agregar un producto a un pedido")
    @PostMapping
    public ResponseEntity<ProductoPedidoResponseDTO> crear(@Valid @RequestBody ProductoPedidoRequestDTO dto) {
        ProductoPedidoResponseDTO resultado = productoPedidoService.save(dto);
        if (resultado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    // ─── PUT /producto-pedido/{id} ────────────────────────────────────────────

    @Operation(summary = "Actualizar un item de pedido")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoPedidoResponseDTO> actualizar(@PathVariable Long id,
                                                                @Valid @RequestBody ProductoPedidoRequestDTO dto) {
        ProductoPedidoResponseDTO resultado = productoPedidoService.update(id, dto);
        if (resultado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    // ─── DELETE /producto-pedido/{id} ─────────────────────────────────────────

    @Operation(summary = "Eliminar un item de un pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!productoPedidoService.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
