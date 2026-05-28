package com.androvixj.api.controller;

import com.androvixj.api.dto.PedidoRequestDTO;
import com.androvixj.api.dto.PedidoResponseDTO;
import com.androvixj.api.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedidos", description = "Gestión de pedidos ANDROVIX-J")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // ─── GET /pedidos ─────────────────────────────────────────────────────────

    @Operation(summary = "Listar todos los pedidos")
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    // ─── GET /pedidos/{id} ────────────────────────────────────────────────────

    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obtenerPorId(@PathVariable Long id) {
        PedidoResponseDTO resultado = pedidoService.findById(id);
        if (resultado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    // ─── GET /pedidos/usuario/{usuarioId} ─────────────────────────────────────

    @Operation(summary = "Listar pedidos de un usuario específico")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoResponseDTO>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.findByUsuario(usuarioId));
    }

    // ─── POST /pedidos ────────────────────────────────────────────────────────

    @Operation(summary = "Crear un nuevo pedido")
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crear(@Valid @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO resultado = pedidoService.save(dto);
        if (resultado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    // ─── PUT /pedidos/{id} ────────────────────────────────────────────────────

    @Operation(summary = "Actualizar un pedido existente")
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> actualizar(@PathVariable Long id,
                                                        @Valid @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO resultado = pedidoService.update(id, dto);
        if (resultado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resultado);
    }

    // ─── DELETE /pedidos/{id} ─────────────────────────────────────────────────

    @Operation(summary = "Eliminar un pedido por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!pedidoService.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
