package com.example.primera_version.business.entities;



import javax.persistence.*;
import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "turists")
public class Turist extends Usuario{

    @Transient
    private Pais pais;

    @Column(name = "nombre_pais", nullable = false)
    private String nombrePais;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate birthdate;

    @Column(name = "intereses_asociados")
    private Interes[] interesesAsociados;


    public Turist(String mail, String password, Pais pais, LocalDate birthdate, Interes[] interesesAsociados) {
        super(mail, password);
        this.pais = pais;
        this.nombrePais = pais.getNombre();
        this.birthdate = birthdate;
        this.interesesAsociados = interesesAsociados;
    }

    public Turist() {
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        this.nombrePais = pais.getNombre();
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNacionalidad() {
        return nombrePais;
    }

    public void setNacionalidad(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Interes[] getInteresesAsociados() {
        return interesesAsociados;
    }

    public void setInteresesAsociados(Interes[] interesesAsociados) {
        this.interesesAsociados = interesesAsociados;
    }
}

