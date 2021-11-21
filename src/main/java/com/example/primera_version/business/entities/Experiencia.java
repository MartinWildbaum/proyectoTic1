package com.example.primera_version.business.entities;
import javafx.scene.image.Image;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "experiencias")
    public class Experiencia implements Comparable<Experiencia>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experiencia")
    private Long idExperiencia;

    @Column(name = "titulo_experiencia", nullable = false, unique = true)
    private String tituloExperiencia;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "foto_portada", columnDefinition = "MEDIUMBLOB")
    private byte[] fotoPortada;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experiencia", fetch = FetchType.EAGER)
    private Set<Imagen> imagenes;

    @Column(name = "links_videos", nullable = true)
    private String linkVideos;

    @Column(name = "aforo_disponible", nullable = true) // Falta poner el periodo de tiempo cuando se renueva el aforo
    private Integer cantidad;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "disponible",nullable = false, columnDefinition = "BOOLEAN")
    private Boolean estaDisponible = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experiencia", fetch = FetchType.EAGER)
    private Collection<Reserva> reservas;

    @Column(name = "fecha_registro", columnDefinition = "DATETIME")
    private String momentoRegistro;

    @Transient
    private Float puntaje = 0f;

    @ManyToMany(targetEntity = Interes.class, fetch = FetchType.EAGER)
    @JoinTable(name = "Experiencia_interes", joinColumns = @JoinColumn(name = "id_experiencia", referencedColumnName = "id_experiencia"), inverseJoinColumns = @JoinColumn(name = "id_interes", referencedColumnName = "id_interes"))
    private Set<Interes> intereses;

    @ManyToOne(targetEntity = OperadorTuristico.class, optional = false)
    private OperadorTuristico operadorTuristico;

    @ManyToOne(targetEntity = Administrador.class/*, optional = false*/)
    private Administrador administrador;


    public Experiencia(String descripcion, String tituloExperiencia, String linkVideos, Integer cantidad, String ubicacion, Boolean estaDisponible, OperadorTuristico operador_turistico, Administrador administrador) {
        this.descripcion = descripcion;
        this.tituloExperiencia = tituloExperiencia;
        this.linkVideos = linkVideos;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.estaDisponible = estaDisponible;
        this.operadorTuristico = operador_turistico;
        this.administrador = administrador;
    }

    public Experiencia() {
    }



    public byte[] getFotoPortada() {
        return fotoPortada;
    }

    public void setFotoPortada(byte[] fotoPortada) {
        this.fotoPortada = fotoPortada;
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


    public Set<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(Set<Interes> intereses) {
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


    public Set<Imagen> getImagenes() {
        return imagenes;
    }



    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public int compareTo(Experiencia o) { //Cuanto mas recomendable es para la persona, mayor es el puntaje que tiene
        if (this.puntaje > o.puntaje){
            return (-1);
        }else if(this.puntaje < o.puntaje){
            return (1);
        }else{
            return 0;
        }

    }


    public Collection<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Float puntaje) {
        this.puntaje = puntaje;
    }

    public String getMomentoRegistro() {
        return momentoRegistro;
    }

    public void setMomentoRegistro(String momentoRegistro) {
        this.momentoRegistro = momentoRegistro;
    }

    public LocalDateTime obtenerMomentoRegistroComoFecha(){
        return  LocalDateTime.parse(momentoRegistro, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Image getFotoPortadaAsJavaFxImage(final int altura, final int ancho) {
        //WritableImage image = new WritableImage(ancho, altura);
        Image imagen = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(this.getFotoPortada());
        //BufferedImage read = ImageIO.read(bis);
        //image = SwingFXUtils.toFXImage(read, null);
        imagen = new Image(bis);
        return imagen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Experiencia)) return false;
        Experiencia xp = (Experiencia) obj;
        return this.getIdExperiencia().equals(xp.getIdExperiencia());
    }
}