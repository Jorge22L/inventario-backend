package com.proyecto.inventario.response;

import com.proyecto.inventario.model.Categoria;

import java.util.List;


public class CategoriaResponse {
    private List<Categoria> categoria;

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "CategoriaResponse{" +
                "categoria=" + categoria +
                '}';
    }
}
