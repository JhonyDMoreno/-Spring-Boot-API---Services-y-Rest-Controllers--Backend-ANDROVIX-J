package com.androvixj.api.service;

import com.androvixj.api.model.Usuario;
import com.androvixj.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // ─── Listar todos ────────────────────────────────────────────────────────

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // ─── Buscar por ID ───────────────────────────────────────────────────────

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // ─── Crear ───────────────────────────────────────────────────────────────

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ─── Actualizar ──────────────────────────────────────────────────────────

    public Usuario update(Long id, Usuario datos) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente == null) return null;

        existente.setNombreCompleto(datos.getNombreCompleto());
        existente.setTelefono(datos.getTelefono());
        existente.setEmail(datos.getEmail());
        existente.setPassword(datos.getPassword());
        existente.setRol(datos.getRol());
        existente.setFechaRegistro(datos.getFechaRegistro());

        return usuarioRepository.save(existente);
    }

    // ─── Eliminar ────────────────────────────────────────────────────────────

    public boolean delete(Long id) {
        if (!usuarioRepository.existsById(id)) return false;
        usuarioRepository.deleteById(id);
        return true;
    }
}
