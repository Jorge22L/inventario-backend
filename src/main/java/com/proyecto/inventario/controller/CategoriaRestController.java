package com.proyecto.inventario.controller;

import com.proyecto.inventario.response.CategoriaResponseRest;
import com.proyecto.inventario.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> buscarCategoria() {
        ResponseEntity<CategoriaResponseRest> response = categoriaService.buscar();
        return response;
    }
}
