package com.androvixj.api.repository;

import com.androvixj.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por email (útil para login / validar duplicados)
    Optional<Usuario> findByEmail(String email);

    // Verificar si existe un email (útil para validar unicidad)
    boolean existsByEmail(String email);
}
