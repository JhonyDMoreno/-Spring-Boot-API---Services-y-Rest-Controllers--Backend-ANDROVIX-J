package com.androvixj.api.dto;

import com.androvixj.api.model.EstadoPedido;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PedidoRequestDTO {

    @NotNull(message = "La fecha del pedido es obligatoria")
    private LocalDateTime fecha;

    @NotNull(message = "El total es obligatorio")
    private Double total;

    @NotNull(message = "El estado del pedido es obligatorio")
    private EstadoPedido estado;

    @NotNull(message = "El id del usuario es obligatorio")
    private Long usuarioId;

    // ─── Constructor vacío ───────────────────────────────────────────────────

    public PedidoRequestDTO() {}

    // ─── Getters y Setters ───────────────────────────────────────────────────

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}
