package com.example.primera_version.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "paises")
public class Pais {
    @Id
    private String nombre;

    public Pais(String nombre) {
        this.nombre = nombre;
    }


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

