package com.example.primera_version.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@PrimaryKeyJoinColumn(name = "idCalificacion")
@Table(name = "puntuaciones")
public class Puntuacion extends Calificacion{

    @Column(name = "puntaje", nullable = false)
    private Long puntaje;

    public Puntuacion(LocalDateTime fechaHora, Long puntaje) {
        super(fechaHora);
        this.puntaje = puntaje;
    }

    public Puntuacion() {
    }

    public Long getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Long puntaje) {
        this.puntaje = puntaje;
    }

}
