package com.androvixJ.controller;

import com.androvixJ.model.Usuario;
import com.androvixJ.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") //Permite que el Frontend se conecte sin bloqueos de CORS
public class UsuarioController
{
    @Autowired
    private UsuarioService usuarioService;

    //1. OBTENER TODOS LOS USUARIOS (GET) -> http://localhost:8080/api/usuarios
    @GetMapping
    public List<Usuario> obtenerTodos()
    {
        return usuarioService.obtenerTodos();
    }

    //2. OBTENER UN USUARIO POR ID (GET) -> http://localhost:8080/api/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id)
    {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //3. CREAR UN NUEVO USUARIO (POST) -> http://localhost:8080/api/usuarios
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario)
    {
        return usuarioService.guardar(usuario);
    }

    //4. ACTUALIZAR UN USUARIO EXISTENTE (PUT) -> http://localhost:8080/api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
        return usuarioService.obtenerPorId(id).map(usuarioExistente ->
        {
            usuarioExistente.setNombreCompleto(usuarioDetalles.getNombreCompleto());
            usuarioExistente.setTelefono(usuarioDetalles.getTelefono());
            usuarioExistente.setEmail(usuarioDetalles.getEmail());
            usuarioExistente.setPassword(usuarioDetalles.getPassword());
            usuarioExistente.setRol(usuarioDetalles.getRol());
            Usuario usuarioActualizado = usuarioService.guardar(usuarioExistente);
            return ResponseEntity.ok(usuarioActualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. ELIMINAR UN USUARIO (DELETE) -> http://localhost:8080/api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id)
    {
        if (usuarioService.obtenerPorId(id).isPresent())
        {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
