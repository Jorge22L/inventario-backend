package com.proyecto.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Categoria{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idcategoria;
    private String nombre;
    private String descripcion;
}