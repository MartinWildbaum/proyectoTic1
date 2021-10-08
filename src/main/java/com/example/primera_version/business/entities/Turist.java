package com.example.primera_version.business.entities;



import com.example.primera_version.persistence.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "turists")
public class Turist extends Usuario{

    @ManyToOne(targetEntity = Pais.class)
    @JoinColumn(name="nombre_pais", referencedColumnName = "nombre")
    private Pais pais;

//    @Column(name = "nombre_pais", nullable = false)
//    private String nombrePais;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate birthdate;

    @Column(name = "intereses_asociados")
    private Interes[] interesesAsociados;


    public Turist(String mail, String password, Pais pais, LocalDate birthdate, Interes[] interesesAsociados) {
        super(mail, password);
        this.pais = pais;
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
    }

    public String getNombrePais() {
        return this.pais.getNombre();
    }

    public void setNombrePais(String nombrePais) {
        this.pais.setNombre(nombrePais);
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

    @Autowired
    @Transient
    private CountryRepository countryRepository;

    public void setNacionalidad(String nationality){
        this.pais = countryRepository.findOneByNombre(nationality);
    }
}

