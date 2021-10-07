package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@PrimaryKeyJoinColumn(name = "idCalificacion")
@Table(name = "criticas")
public class Critica extends Calificacion{

    @Column(name = "comentario", nullable = false)
    private String comentario;

    public Critica(LocalDateTime fechaHora, String comentario) {
        super(fechaHora);
        this.comentario = comentario;
    }

    public Critica() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
