package com.proyecto.inventario.dao;

import com.proyecto.inventario.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDao extends JpaRepository<Categoria, Long> {
}
