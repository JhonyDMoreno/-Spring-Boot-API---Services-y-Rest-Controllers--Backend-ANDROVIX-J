package com.androvixj.api.dto;

import com.androvixj.api.model.EstadoPedido;
import com.androvixj.api.model.ProductoPedido;

public class ProductoPedidoResponseDTO {

    private Long idProductoPedido;
    private Integer cantidad;
    private Double precioUnitario;
    private Long productoId;
    private String productoNombre;
    private Double productoPrecio;
    private Long pedidoId;
    private EstadoPedido pedidoEstado;

    // ─── Constructor vacío ───────────────────────────────────────────────────

    public ProductoPedidoResponseDTO() {}

    // ─── Método estático de conversión ───────────────────────────────────────

    public static ProductoPedidoResponseDTO desde(ProductoPedido pp) {
        ProductoPedidoResponseDTO dto = new ProductoPedidoResponseDTO();
        dto.idProductoPedido = pp.getIdProductoPedido();
        dto.cantidad         = pp.getCantidad();
        dto.precioUnitario   = pp.getPrecioUnitario();
        dto.productoId       = pp.getProducto().getIdProducto();
        dto.productoNombre   = pp.getProducto().getNombre();
        dto.productoPrecio   = pp.getProducto().getPrecio();
        dto.pedidoId         = pp.getPedido().getIdPedido();
        dto.pedidoEstado     = pp.getPedido().getEstado();
        return dto;
    }

    // ─── Getters y Setters ───────────────────────────────────────────────────

    public Long getIdProductoPedido() { return idProductoPedido; }
    public void setIdProductoPedido(Long idProductoPedido) { this.idProductoPedido = idProductoPedido; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

    public Double getProductoPrecio() { return productoPrecio; }
    public void setProductoPrecio(Double productoPrecio) { this.productoPrecio = productoPrecio; }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public EstadoPedido getPedidoEstado() { return pedidoEstado; }
    public void setPedidoEstado(EstadoPedido pedidoEstado) { this.pedidoEstado = pedidoEstado; }
}
