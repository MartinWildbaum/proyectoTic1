package com.example.primera_version.business.entities;

import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "idInteres")
@Table(name = "intereses_generales")
public class InteresGeneral extends Interes{

    @Column(name = "intereses_asociados", nullable = true)
    private InteresParticular[] interesesAsociados;

    public InteresGeneral() {
    }

    public InteresGeneral(String nombre) {
        super(nombre);
    }

    public InteresParticular[] getInteresesAsociados() {
        return interesesAsociados;
    }

    public void setInteresesAsociados(InteresParticular[] interesesAsociados) {
        this.interesesAsociados = interesesAsociados;
    }
}
