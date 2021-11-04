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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais") // Me hace el programa mas lento
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

    public Collection<Turist> getTuristas() {
        return turistas;
    }

    public void setTuristas(Collection<Turist> turistas) {
        this.turistas = turistas;
    }
}

