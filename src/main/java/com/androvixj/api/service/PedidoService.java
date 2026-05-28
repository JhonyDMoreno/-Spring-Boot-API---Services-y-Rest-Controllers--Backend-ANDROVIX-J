package com.androvixj.api.service;

import com.androvixj.api.dto.PedidoRequestDTO;
import com.androvixj.api.dto.PedidoResponseDTO;
import com.androvixj.api.model.Pedido;
import com.androvixj.api.model.Usuario;
import com.androvixj.api.repository.PedidoRepository;
import com.androvixj.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository,
                         UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // ─── Listar todos ────────────────────────────────────────────────────────

    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoResponseDTO::desde)
                .collect(Collectors.toList());
    }

    // ─── Buscar por ID ───────────────────────────────────────────────────────

    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) return null;
        return PedidoResponseDTO.desde(pedido);
    }

    // ─── Buscar por usuario ───────────────────────────────────────────────────

    public List<PedidoResponseDTO> findByUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioIdUsuario(usuarioId)
                .stream()
                .map(PedidoResponseDTO::desde)
                .collect(Collectors.toList());
    }

    // ─── Crear ───────────────────────────────────────────────────────────────

    public PedidoResponseDTO save(PedidoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        if (usuario == null) return null;

        Pedido pedido = new Pedido(dto.getFecha(), dto.getTotal(), dto.getEstado(), usuario);
        return PedidoResponseDTO.desde(pedidoRepository.save(pedido));
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────

    public PedidoResponseDTO update(Long id, PedidoRequestDTO dto) {
        Pedido existente = pedidoRepository.findById(id).orElse(null);
        if (existente == null) return null;

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        if (usuario == null) return null;

        existente.setFecha(dto.getFecha());
        existente.setTotal(dto.getTotal());
        existente.setEstado(dto.getEstado());
        existente.setUsuario(usuario);

        return PedidoResponseDTO.desde(pedidoRepository.save(existente));
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────

    public boolean delete(Long id) {
        if (!pedidoRepository.existsById(id)) return false;
        pedidoRepository.deleteById(id);
        return true;
    }
}
