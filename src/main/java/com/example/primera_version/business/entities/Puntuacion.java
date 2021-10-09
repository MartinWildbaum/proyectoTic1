package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@PrimaryKeyJoinColumn(name = "id_calificacion")
@Table(name = "puntuaciones")
public class Puntuacion extends Calificacion{

    @Column(name = "puntaje", nullable = false)
    private Long puntaje;

    @OneToOne(mappedBy = "puntuacion")
    private Reserva reserva;

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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
