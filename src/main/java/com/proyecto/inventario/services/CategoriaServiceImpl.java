package com.proyecto.inventario.services;

import com.proyecto.inventario.dao.ICategoriaDao;
import com.proyecto.inventario.model.Categoria;
import com.proyecto.inventario.response.CategoriaResponseRest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional()
    public ResponseEntity<CategoriaResponseRest> buscar() {
        CategoriaResponseRest response = new CategoriaResponseRest();
        try {
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategoria(categoria);
            response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        }
        catch (Exception e)
        {
            response.setMetadata("Respuesta error", "-1", "Error al buscar");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
}
