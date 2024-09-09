package com.proyecto.inventario.dao;

import com.proyecto.inventario.model.Categoria;
import org.springframework.data.repository.CrudRepository;


public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
}
