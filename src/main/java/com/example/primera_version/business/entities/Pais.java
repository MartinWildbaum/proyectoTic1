package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "paises")
public class Pais {
    @Id
    private String nombre;

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "pais")
    private Collection<Turist> turistas;


    public Pais() {
        this.nombre = "";// FIXME
    }


    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

