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
    private final ICategoriaDao categoriaDao;

    Date fecha = new Date();

    public CategoriaServiceImpl(ICategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

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

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> guardar(Categoria categoria) {
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> lista = new ArrayList<>();

        try
        {
            Categoria categoriaSaved = categoriaDao.save(categoria);
            if(categoriaSaved != null)
            {
                lista.add(categoriaSaved);
                response.getCategoriaResponse().setCategoria(lista);
                response.setMetadata("Categoría guardada", "200", fecha.toString());
            }
            else
            {
                response.setMetadata("No se pudo guardar categoría", "-1", fecha.toString());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e)
        {
            response.setMetadata("Error al guardar categoria", "-1", fecha.toString());
            throw new RuntimeException("Error al guardar categoria", e);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
        CategoriaResponseRest response =  new CategoriaResponseRest();
        List<Categoria> lista = new ArrayList<>();

        try{
            Optional<Categoria> encontrado = categoriaDao.findById(id);
            if(encontrado.isPresent())
            {
                encontrado.get().setNombre(categoria.getNombre());
                encontrado.get().setDescripcion(categoria.getDescripcion());
                Categoria categoriaActual = categoriaDao.save(encontrado.get());
                if(categoriaActual != null)
                {
                    lista.add(categoriaActual);
                    response.getCategoriaResponse().setCategoria(lista);
                    response.setMetadata("Categoria Actualizada con exito", "200", fecha.toString());
                }
            }
            else
            {
                response.setMetadata("Categoría no encontrada", "-1", fecha.toString());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            response.setMetadata("Error al actualizar categoria", "-1", fecha.toString());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        CategoriaResponseRest response = new CategoriaResponseRest();
        try
        {
            Optional<Categoria> encontrado = categoriaDao.findById(id);
            if (encontrado.isPresent())
            {
                categoriaDao.deleteById(id);
                response.setMetadata("Categoria eliminada", "ok", fecha.toString());
            }
            else {
                response.setMetadata("Error al eliminar categoria. Categoria no existe", "-1", fecha.toString());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            response.setMetadata("Error al eliminar categoria", "-1", fecha.toString());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
}
