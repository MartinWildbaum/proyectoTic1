package com.example.primera_version.business.entities;

import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "usuarios_op_tur")
public class UsuarioOpTur extends Usuario {



    @ManyToOne(targetEntity = OperadorTuristico.class)
    @JoinColumn(name="id_op_tur", referencedColumnName = "id_op_tur", nullable = false)
    private OperadorTuristico operadorTuristico;

    public UsuarioOpTur(String mail, String password, OperadorTuristico operadorTuristico) {
        super(mail, password);
        this.operadorTuristico = operadorTuristico;
    }

    public UsuarioOpTur() {
    }

    public OperadorTuristico getOperadorTuristico() {
        return operadorTuristico;
    }

    public void setOperadorTuristico(OperadorTuristico operadorTuristico) {
        this.operadorTuristico = operadorTuristico;
    }
}