package com.androvixj.api.dto;

import com.androvixj.api.model.EstadoPedido;
import com.androvixj.api.model.Pedido;

import java.time.LocalDateTime;

public class PedidoResponseDTO {

    private Long idPedido;
    private LocalDateTime fecha;
    private Double total;
    private EstadoPedido estado;
    private Long usuarioId;
    private String usuarioNombre;
    private String usuarioEmail;

    // ─── Constructor vacío ───────────────────────────────────────────────────

    public PedidoResponseDTO() {}

    // ─── Método estático de conversión ───────────────────────────────────────

    public static PedidoResponseDTO desde(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.idPedido      = pedido.getIdPedido();
        dto.fecha         = pedido.getFecha();
        dto.total         = pedido.getTotal();
        dto.estado        = pedido.getEstado();
        dto.usuarioId     = pedido.getUsuario().getIdUsuario();
        dto.usuarioNombre = pedido.getUsuario().getNombreCompleto();
        dto.usuarioEmail  = pedido.getUsuario().getEmail();
        return dto;
    }

    // ─── Getters y Setters ───────────────────────────────────────────────────

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }

    public String getUsuarioEmail() { return usuarioEmail; }
    public void setUsuarioEmail(String usuarioEmail) { this.usuarioEmail = usuarioEmail; }
}
