package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.util.Collection;


@Entity
@PrimaryKeyJoinColumn(name = "id_interes")
@Table(name = "intereses_generales")
public class InteresGeneral extends Interes {


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interesGeneral")
    private Collection<InteresParticular> interesesParticularesAsociados;


    public InteresGeneral() {
    }

    public InteresGeneral(String nombre) {
        super(nombre);
    }

    public Collection<InteresParticular> getInteresesParticularesAsociados() {
        return interesesParticularesAsociados;
    }

    public void setInteresesParticularesAsociados(Collection<InteresParticular> interesesParticularesAsociados) {
        this.interesesParticularesAsociados = interesesParticularesAsociados;
    }
}
