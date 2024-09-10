package com.proyecto.inventario.services;

import com.proyecto.inventario.dao.ICategoriaDao;
import com.proyecto.inventario.model.Categoria;
import com.proyecto.inventario.response.CategoriaResponseRest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private ICategoriaDao categoriaDao;

    Date fecha = new Date();

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

    @Override
    @Transactional()
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> lista = new ArrayList<>();
        try
        {
            Optional<Categoria> categoria = categoriaDao.findById(id);
            if(categoria.isPresent())
            {
                lista.add(categoria.get());
                response.getCategoriaResponse().setCategoria(lista);
            }
            else
            {
                response.setMetadata("Categoría no encontrada", "-1", fecha.toString());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            response.setMetadata("Error al listar categorias", "-1", fecha.toString());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
}
