package com.example.primera_version.business.entities;

import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "usuarios_op_tur")
public class UsuarioOpTur extends Usuario {


    @Transient
    private OperadorTuristico operadorTuristico;

    @Column(name = "id_op_tur", nullable = false)
    private Long idOperador;


    public UsuarioOpTur(String mail, String password, OperadorTuristico operadorTuristico) {
        super(mail, password);
        this.operadorTuristico = operadorTuristico;
        this.idOperador = operadorTuristico.getIdOpTur();
    }

    public UsuarioOpTur() {
    }

    public OperadorTuristico getOperadorTuristico() {
        return operadorTuristico;
    }

    public void setOperadorTuristico(OperadorTuristico operadorTuristico) {
        this.operadorTuristico = operadorTuristico;
        this.idOperador = operadorTuristico.getIdOpTur();
    }

    public Long getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }
}