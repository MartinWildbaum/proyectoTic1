package com.example.primera_version.business.entities;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "idInteres")
@Table(name = "intereses_particulares")
public class InteresParticular extends Interes{



    public InteresParticular() {
    }

    public InteresParticular(String nombre) {
        super(nombre);
    }
}
