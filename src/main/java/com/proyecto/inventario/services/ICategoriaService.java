package com.proyecto.inventario.services;

import com.proyecto.inventario.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    public ResponseEntity<CategoriaResponseRest> buscar();
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);

}
