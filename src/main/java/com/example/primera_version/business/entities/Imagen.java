package com.example.primera_version.business.entities;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Entity
@Table(name = "iamgenes_expereincias")
public class Imagen {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @ManyToOne(targetEntity = Experiencia.class)
    @JoinColumn(name="id_experiencia", referencedColumnName = "id_experiencia")
    private Experiencia experiencia;

    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB")
    private byte[] imagen;



    public Imagen() {
    }

    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }


    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagenes) {
        this.imagen = imagenes;
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


}
