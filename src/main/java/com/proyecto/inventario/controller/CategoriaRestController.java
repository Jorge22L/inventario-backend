package com.proyecto.inventario.controller;

import com.proyecto.inventario.model.Categoria;
import com.proyecto.inventario.response.CategoriaResponseRest;
import com.proyecto.inventario.services.ICategoriaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> guardar(@RequestBody Categoria categoria)
    {
        ResponseEntity<CategoriaResponseRest> response = categoriaService.guardar(categoria);
        return response;
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody Categoria categoria,@PathVariable Long id)
    {
        ResponseEntity<CategoriaResponseRest> response = categoriaService.actualizar(categoria,id);
        return response;
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Long id)
    {
        ResponseEntity<CategoriaResponseRest> response = categoriaService.eliminar(id);
        return response;
    }
}
