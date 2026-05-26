package com.androvixJ.repository;

import com.androvixJ.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    //Heredando de JpaRepository save(), findById(), findAll(), deleteById()
}