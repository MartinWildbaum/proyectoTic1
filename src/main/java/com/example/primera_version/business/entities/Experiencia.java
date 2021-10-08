package com.example.primera_version.business.entities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;


@Entity
@Table(name = "experiencias")
    public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperiencia;

    @Column(name = "titulo_experiencia", nullable = false, unique = true)
    private String tituloExperiencia;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB")
    private byte[] imagen;

    @Column(name = "links_videos", nullable = true)
    private String linkVideos;

    @Column(name = "aforo_disponible", nullable = true) // Falta poner el periodo de tiempo cuando se renueva el aforo
    private Integer cantidad;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "disponible")
    private Boolean estaDisponible;

    @ManyToMany(targetEntity = Interes.class)
    @JoinTable(name = "Experiencia_interes", joinColumns = @JoinColumn(name = "idExperiencia", referencedColumnName = "idExperiencia"), inverseJoinColumns = @JoinColumn(name = "idInteres", referencedColumnName = "idInteres"))
    private Collection<Interes> intereses;

    @Transient
    private OperadorTuristico operadorTuristico;

    @Column(name = "id_operador", nullable = false) // El operador turistico que las ofrece
    private Long idOperador;

    @Transient
    private Administrador administrador;

    @Column(name = "mail_administrador", nullable = false) // El oadministrador que la valido
    private String mailAdministrador;

//    @Column(name = "intereses_relacionados")
//    private Interes[] intereses;

    public Experiencia(String descripcion, String tituloExperiencia, byte[] imagen, String linkVideos, Integer cantidad, String ubicacion, Boolean estaDisponible, OperadorTuristico operador_turistico, Administrador administrador/*, Interes[] intereses*/) {
        ;
        this.descripcion = descripcion;
        this.tituloExperiencia = tituloExperiencia;
        this.imagen = imagen;
        this.linkVideos = linkVideos;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.estaDisponible = estaDisponible;
        this.operadorTuristico = operador_turistico;
        this.idOperador = operador_turistico.getIdOpTur();
        this.administrador = administrador;
        this.mailAdministrador = administrador.getMail();
//        this.intereses = intereses;
    }

    public Experiencia() {
    }

    public Long getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(Long idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLinkVideos() {
        return linkVideos;
    }

    public void setLinkVideos(String linkVideos) {
        this.linkVideos = linkVideos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(Boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
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
        // Capaz tendria que ir a buscar a la base de datos y a partir de eso fijar el operador turistico?
        this.idOperador = idOperador;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
        this.mailAdministrador = administrador.getMail();
    }


    public String getMailAdministrador() {
        return mailAdministrador;
    }

    public void setMailAdministrador(String mailAdministrador) {
        this.mailAdministrador = mailAdministrador;
    }

//    public Interes[] getIntereses() {
//        return intereses;
//    }
//
//    public void setIntereses(Interes[] intereses) {
//        this.intereses = intereses;
//    }

    public String getTituloExperiencia() {
        return tituloExperiencia;
    }

    public void setTituloExperiencia(String tituloExperiencia) {
        this.tituloExperiencia = tituloExperiencia;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Image getImagenAsJavaFxImage(final int altura, final int ancho) {
        WritableImage image = new WritableImage(ancho, altura);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(this.getImagen());
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException excepcion) {
            //
        }
        return image;
    }

    public Collection<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(Collection<Interes> intereses) {
        this.intereses = intereses;
    }
}











