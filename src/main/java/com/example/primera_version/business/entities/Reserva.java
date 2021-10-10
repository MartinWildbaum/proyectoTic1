package com.example.primera_version.business.entities;


import javax.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_reserva")
    private Long numeroReserva;

    @Transient
    private Turist turista;

    @Column(name = "mail_turista", nullable = false)
    private String mailTurista;

    @Transient
    private Experiencia experiencia;

    @Column(name = "id_experiencia", nullable = false)
    private Long idExperiencia;

    @Column(name = "numero_personas", nullable = false)
    private Long numeroPersonas;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;


    @OneToOne(targetEntity = Puntuacion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_clasificacion_puntuacion")
    private Puntuacion puntuacion;

    @OneToOne(targetEntity = Critica.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_clasificacion_critica")
    private Critica critica;


    @OneToOne(targetEntity = Denuncia.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_denuncia")
    private Denuncia denuncia;


    public Reserva(Long numeroReserva, Turist turista, Experiencia experiencia, Long numeroPersonas) {
        this.numeroReserva = numeroReserva;
        this.turista = turista;
        this.mailTurista = turista.getMail();
        this.experiencia = experiencia;
        this.idExperiencia = experiencia.getIdExperiencia();
        this.numeroPersonas = numeroPersonas;
        this.estado = false;
        this.fechaHora = LocalDateTime.now();
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

    public String getMailTurista() {
        return mailTurista;
    }

    public void setMailTurista(String mailTurista) {
        this.mailTurista = mailTurista;
    }

    public Long getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(Long idExperiencia) {
        this.idExperiencia = idExperiencia;
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
        this.mailTurista = turista.getMail();
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
        this.idExperiencia = experiencia.getIdExperiencia();
    }
}

