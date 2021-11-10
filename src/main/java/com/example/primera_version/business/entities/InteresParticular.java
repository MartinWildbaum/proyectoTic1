package com.example.primera_version.business.entities;
import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id_interes")
@Table(name = "intereses_particulares")
public class InteresParticular extends Interes{

    @ManyToOne(targetEntity = InteresGeneral.class)
    @JoinColumn(name="intere_general", referencedColumnName = "id_interes")
    private InteresGeneral interesGeneral;

    public InteresParticular() {
    }

    public InteresParticular(String nombre) {
        super(nombre);
    }

    public InteresGeneral getInteresGeneral() {
        return interesGeneral;
    }

    public void setInteresGeneral(InteresGeneral interesGeneral) {
        this.interesGeneral = interesGeneral;
    }
}
