package com.example.primera_version.business.entities;


import javax.persistence.*;
import java.util.Collection;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "administrador")
public class Administrador extends Usuario{


    @OneToMany(cascade = CascadeType.ALL, targetEntity = Experiencia.class, mappedBy = "administrador")
    private Collection<Experiencia> experienciasRegistradas;


    // Constructores

    public Administrador(String mail, String password) {
        super(mail, password);
    }

    public Administrador() {
    }

    public Collection<Experiencia> getExperienciasRegistradas() {
        return experienciasRegistradas;
    }

    public void setExperienciasRegistradas(Collection<Experiencia> experienciasRegistradas) {
        this.experienciasRegistradas = experienciasRegistradas;
    }
}
