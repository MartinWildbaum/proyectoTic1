package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "intereses")
public abstract class Interes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInteres;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "intereses",targetEntity = Experiencia.class/*,cascade = CascadeType.ALL,fetch = FetchType.EAGER*/)
    private Collection<Experiencia> experiencias;


    public Interes(String nombre) {
        this.nombre = nombre;
    }

    public Interes() {
    }





    public Long getId() {
        return idInteres;
    }

    public void setId(Long idInteres) {
        this.idInteres = idInteres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}