package com.example.primera_version.business.entities;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "intereses")
public class Interes { //ERA ABSTRACTO!!

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interes")
    private Long idInteres;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "intereses",
            targetEntity = Experiencia.class)
    private Collection<Experiencia> experiencias;


    @ManyToMany(mappedBy = "intereses",
            targetEntity = Turist.class)
    private Collection<Turist> turistas;

   /* @ManyToMany(mappedBy = "interesesAsociados",
            targetEntity = InteresGeneral.class)
    private Collection<InteresGeneral> interesesGeneralesAsociados;*/

    public Interes(String nombre) {
        this.nombre = nombre;
    }

    public Interes() {
    }

    @Override
    public String toString() {
        return nombre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interes)) return false;
        Interes interes = (Interes) o;
        return this.getId().equals(interes.getId());
    }

}