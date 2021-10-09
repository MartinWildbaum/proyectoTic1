package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.util.Collection;


@Entity
@PrimaryKeyJoinColumn(name = "id_interes")
@Table(name = "intereses_generales")
public class InteresGeneral extends Interes {


    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Interes.class)
    @JoinTable(name = "Intereses_asociados", joinColumns = @JoinColumn(name = "id_interes_general", referencedColumnName = "id_interes"), inverseJoinColumns = @JoinColumn(name = "id_interes_asociado", referencedColumnName = "id_interes"))
    private Collection<Interes> interesesAsociados;


    public InteresGeneral() {
    }

    public InteresGeneral(String nombre) {
        super(nombre);
    }

    public Collection<Interes> getInteresesAsociados() {
        return interesesAsociados;
    }

    public void setInteresesAsociados(Collection<Interes> interesesAsociados) {
        this.interesesAsociados = interesesAsociados;
    }
}
