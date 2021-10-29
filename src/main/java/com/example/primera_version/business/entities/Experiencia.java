package com.example.primera_version.business.entities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(name = "experiencias")
    public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experiencia")
    private Long idExperiencia;

    @Column(name = "titulo_experiencia", nullable = false, unique = true)
    private String tituloExperiencia;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;


    @Column(name = "imagenes", columnDefinition = "LONGBLOB")
    private ArrayList<byte[]> imagenes;

    @Column(name = "links_videos", nullable = true)
    private String linkVideos;

    @Column(name = "aforo_disponible", nullable = true) // Falta poner el periodo de tiempo cuando se renueva el aforo
    private Integer cantidad;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "disponible",nullable = false, columnDefinition = "BOOLEAN")
    private Boolean estaDisponible = false;



    @ManyToMany(targetEntity = Interes.class, fetch = FetchType.EAGER)
    @JoinTable(name = "Experiencia_interes", joinColumns = @JoinColumn(name = "id_experiencia", referencedColumnName = "id_experiencia"), inverseJoinColumns = @JoinColumn(name = "id_interes", referencedColumnName = "id_interes"))
    private Collection<Interes> intereses;

    @ManyToOne(targetEntity = OperadorTuristico.class, optional = false)
    private OperadorTuristico operadorTuristico;

    @ManyToOne(targetEntity = Administrador.class/*, optional = false*/)
    private Administrador administrador;


    public Experiencia(String descripcion, String tituloExperiencia, ArrayList<byte[]> imagenes, String linkVideos, Integer cantidad, String ubicacion, Boolean estaDisponible, OperadorTuristico operador_turistico, Administrador administrador) {
        ;
        this.descripcion = descripcion;
        this.tituloExperiencia = tituloExperiencia;
        this.imagenes = imagenes;
        this.linkVideos = linkVideos;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.estaDisponible = estaDisponible;
        this.operadorTuristico = operador_turistico;
        this.administrador = administrador;
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

    public String getTituloExperiencia() {
        return tituloExperiencia;
    }

    public void setTituloExperiencia(String tituloExperiencia) {
        this.tituloExperiencia = tituloExperiencia;
    }

    public ArrayList<byte[]> getImagen() {
        return imagenes;
    }

    public void setImagen(ArrayList<byte[]> imagen) {
        this.imagenes = imagen;
    }

    public Image getImagenAsJavaFxImage(final int altura, final int ancho) {
        WritableImage image = new WritableImage(ancho, altura);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(this.getImagen().get(0));
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException excepcion) {
            //
        }
        return image;
    }

    public ArrayList<byte[]> getImagenes() {
        return imagenes;
    }

    public Collection<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(Collection<Interes> intereses) {
        this.intereses = intereses;
    }

    public void setOperadorTuristico(OperadorTuristico operadorTuristico) {
        this.operadorTuristico = operadorTuristico;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

}











