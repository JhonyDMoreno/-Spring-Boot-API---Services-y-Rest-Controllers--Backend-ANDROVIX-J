package com.androvixj.api.service;

import com.androvixj.api.dto.ProductoPedidoRequestDTO;
import com.androvixj.api.dto.ProductoPedidoResponseDTO;
import com.androvixj.api.model.Pedido;
import com.androvixj.api.model.Producto;
import com.androvixj.api.model.ProductoPedido;
import com.androvixj.api.repository.PedidoRepository;
import com.androvixj.api.repository.ProductoPedidoRepository;
import com.androvixj.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoPedidoService {

    private final ProductoPedidoRepository productoPedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public ProductoPedidoService(ProductoPedidoRepository productoPedidoRepository,
                                 ProductoRepository productoRepository,
                                 PedidoRepository pedidoRepository) {
        this.productoPedidoRepository = productoPedidoRepository;
        this.productoRepository = productoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // ─── Listar todos ────────────────────────────────────────────────────────

    public List<ProductoPedidoResponseDTO> findAll() {
        return productoPedidoRepository.findAll()
                .stream()
                .map(ProductoPedidoResponseDTO::desde)
                .collect(Collectors.toList());
    }

    // ─── Buscar por ID ───────────────────────────────────────────────────────

    public ProductoPedidoResponseDTO findById(Long id) {
        ProductoPedido pp = productoPedidoRepository.findById(id).orElse(null);
        if (pp == null) return null;
        return ProductoPedidoResponseDTO.desde(pp);
    }

    // ─── Buscar items de un pedido ───────────────────────────────────────────

    public List<ProductoPedidoResponseDTO> findByPedido(Long pedidoId) {
        return productoPedidoRepository.findByPedidoIdPedido(pedidoId)
                .stream()
                .map(ProductoPedidoResponseDTO::desde)
                .collect(Collectors.toList());
    }

    // ─── Crear ───────────────────────────────────────────────────────────────

    public ProductoPedidoResponseDTO save(ProductoPedidoRequestDTO dto) {
        Producto producto = productoRepository.findById(dto.getProductoId()).orElse(null);
        if (producto == null) return null;

        Pedido pedido = pedidoRepository.findById(dto.getPedidoId()).orElse(null);
        if (pedido == null) return null;

        ProductoPedido pp = new ProductoPedido(
                dto.getCantidad(), dto.getPrecioUnitario(), producto, pedido);
        return ProductoPedidoResponseDTO.desde(productoPedidoRepository.save(pp));
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────

    public ProductoPedidoResponseDTO update(Long id, ProductoPedidoRequestDTO dto) {
        ProductoPedido existente = productoPedidoRepository.findById(id).orElse(null);
        if (existente == null) return null;

        Producto producto = productoRepository.findById(dto.getProductoId()).orElse(null);
        if (producto == null) return null;

        Pedido pedido = pedidoRepository.findById(dto.getPedidoId()).orElse(null);
        if (pedido == null) return null;

        existente.setCantidad(dto.getCantidad());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        existente.setProducto(producto);
        existente.setPedido(pedido);

        return ProductoPedidoResponseDTO.desde(productoPedidoRepository.save(existente));
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────

    public boolean delete(Long id) {
        if (!productoPedidoRepository.existsById(id)) return false;
        productoPedidoRepository.deleteById(id);
        return true;
    }
}
