package com.example.primera_version.business.entities;


import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "numero_reserva")
    private Long numeroReserva;

    @ManyToOne(targetEntity = Turist.class)
    @JoinColumn(name = "mail_turista", referencedColumnName = "mail")
    private Turist turista;


    @ManyToOne(targetEntity = Experiencia.class)
    @JoinColumn(name="id_experiencia", referencedColumnName = "id_experiencia")
    private Experiencia experiencia;


    @Column(name = "numero_personas", nullable = false)
    private Long numeroPersonas;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;


    @OneToOne(targetEntity = Puntuacion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_clasificacion_puntuacion")
    private Puntuacion puntuacion;

    @OneToOne(targetEntity = Critica.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_clasificacion_critica")
    private Critica critica;


    @OneToOne(targetEntity = Denuncia.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_denuncia")
    private Denuncia denuncia;


    public Reserva(Turist turista, Experiencia experiencia, Long numeroPersonas, LocalDate fechaReserva) {
        this.turista = turista;
        this.experiencia = experiencia;
        this.numeroPersonas = numeroPersonas;
        this.estado = false;
        this.fecha = fechaReserva;
        this.denuncia = null;
        this.puntuacion = null;
        this.critica = null;
    }
    // No lleva puntuacion ni critica ni denuncia pues son cosas que se setean a posteriori


    public Reserva() {

    }


    public Long getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(Long numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public Long getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Long numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void habilitarReserva(){
        this.estado = true;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fechaHora) {
        this.fecha = fechaHora;
    }

    public Puntuacion getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Puntuacion puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Critica getCritica() {
        return critica;
    }

    public void setCritica(Critica critica) {
        this.critica = critica;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }

    public Turist getTurista() {
        return turista;
    }

    public void setTurista(Turist turista) {
        this.turista = turista;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
}

