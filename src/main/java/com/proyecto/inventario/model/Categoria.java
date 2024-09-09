package com.proyecto.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "categoria")
public record Categoria(
        @Id Long id,
        String nombre,
        String descripcion) implements Serializable {

}