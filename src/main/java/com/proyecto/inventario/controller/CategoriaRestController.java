package com.proyecto.inventario.controller;

import com.proyecto.inventario.response.CategoriaResponseRest;
import com.proyecto.inventario.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService categoriaService;

    /**
     * Lista todas las categorías
     * @return
     */
    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> buscarCategoria() {
        ResponseEntity<CategoriaResponseRest> response = categoriaService.buscar();
        return response;
    }

    /**
     * Obtiene una categoría por id
     * @param id
     * @return
     */
    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> buscarCategoriaPorId(@PathVariable Long id)
    {
        ResponseEntity<CategoriaResponseRest> response = categoriaService.buscarPorId(id);
        return response;
    }
}
