package com.androvixJ.repository;

import com.androvixJ.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long>
{
    //Al heredar de JpaRepository, ya tenemos el buscar, guardar, editar y eliminar.
}
