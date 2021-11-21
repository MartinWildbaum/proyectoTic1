package com.example.primera_version.business.entities;
import javafx.scene.image.Image;
import javax.persistence.*;
import java.io.ByteArrayInputStream;

@Entity
@Table(name = "imagenes_expereincias")
public class Imagen {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @ManyToOne(targetEntity = Experiencia.class)
    @JoinColumn(name="id_experiencia", referencedColumnName = "id_experiencia", nullable = false)
    private Experiencia experiencia;

    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB", nullable = false)
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
        //WritableImage image = new WritableImage(ancho, altura);
        Image imagen = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(this.getImagen());
        //BufferedImage read = ImageIO.read(bis);
        //image = SwingFXUtils.toFXImage(read, null);
        imagen = new Image(bis);
        return imagen;
    }


}
