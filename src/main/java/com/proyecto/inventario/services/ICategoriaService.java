package com.proyecto.inventario.services;

import com.proyecto.inventario.model.Categoria;
import com.proyecto.inventario.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    public ResponseEntity<CategoriaResponseRest> buscar();
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
    public ResponseEntity<CategoriaResponseRest> guardar(Categoria categoria);
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);

}
