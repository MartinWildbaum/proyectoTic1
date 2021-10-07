package com.example.primera_version.business.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
    @Table(name = "denuncia")
    public class Denuncia {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idDenuncia;

        @Column(name = "descripcion", nullable = false)
        private String descripcion;

        @Column(name = "fecha_hora", nullable = false)
        private LocalDateTime fechaHora;

    public Denuncia(Long idDenuncia, String descripcion, LocalDateTime fechaHora) {
        this.idDenuncia = idDenuncia;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    public Denuncia() {
    }

    public Long getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Long idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}



