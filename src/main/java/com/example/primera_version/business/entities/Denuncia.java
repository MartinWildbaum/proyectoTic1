package com.example.primera_version.business.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "denuncias")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_denuncia")
    private Long idDenuncia;

    @Column(name = "motivo_denuncia", nullable = false)
    private String motivoDenuncia;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @OneToOne(mappedBy = "denuncia")
    private Reserva reserva;


    public Denuncia(Long idDenuncia, String descripcion, LocalDateTime fechaHora, Reserva reserva) {
    this.idDenuncia = idDenuncia;
    this.descripcion = descripcion;
    this.fechaHora = fechaHora;
    this.reserva = reserva;
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

    public Reserva getReserva() {
    return reserva;
    }

    public void setReserva(Reserva reserva) {
    this.reserva = reserva;
    }

    public String getMotivoDenuncia() {
        return motivoDenuncia;
    }

    public void setMotivoDenuncia(String motivoDenuncia) {
        this.motivoDenuncia = motivoDenuncia;
    }
}