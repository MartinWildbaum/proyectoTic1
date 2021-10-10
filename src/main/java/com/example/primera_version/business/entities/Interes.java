package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "intereses")
public abstract class Interes {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interes")
    private Long idInteres;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "intereses",
            targetEntity = Experiencia.class, cascade = CascadeType.ALL)
    private Collection<Experiencia> experiencias;


    @ManyToMany(mappedBy = "intereses",
            targetEntity = Turist.class, cascade = CascadeType.ALL)
    private Collection<Turist> turistas;

    @ManyToMany(mappedBy = "interesesAsociados",
            targetEntity = InteresGeneral.class, cascade = CascadeType.ALL)
    private Collection<InteresGeneral> interesesGeneralesAsociados;

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

    public Collection<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(Collection<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public Collection<Turist> getTuristas() {
        return turistas;
    }

    public void setTuristas(Collection<Turist> turistas) {
        this.turistas = turistas;
    }

    public Collection<InteresGeneral> getInteresesGeneralesAsociados() {
        return interesesGeneralesAsociados;
    }

    public void setInteresesGeneralesAsociados(Collection<InteresGeneral> interesesGeneralesAsociados) {
        this.interesesGeneralesAsociados = interesesGeneralesAsociados;
    }
}