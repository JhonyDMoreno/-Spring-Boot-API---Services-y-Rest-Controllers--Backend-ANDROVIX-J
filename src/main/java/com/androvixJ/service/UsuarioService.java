package com.androvixJ.service;

import com.androvixJ.model.Usuario;
import com.androvixJ.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    //1. Obtener todos los usuarios (Read)
    public List<Usuario> obtenerTodos()
    {
        return usuarioRepository.findAll();
    }

    //2. Obtener un usuario por su ID (Read)
    public Optional<Usuario> obtenerPorId(Long id)
    {
        return usuarioRepository.findById(id);
    }

    //3. Crear o actualizar un usuario (Create/Update)
    public Usuario guardar(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    //4. Eliminar un usuario por ID (Delete)
    public void eliminar(Long id)
    {
        usuarioRepository.deleteById(id);
    }
}
