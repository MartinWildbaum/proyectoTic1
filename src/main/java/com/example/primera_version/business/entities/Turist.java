package com.example.primera_version.business.entities;



import com.example.primera_version.persistence.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "turists")
public class Turist extends Usuario{

    @ManyToOne(targetEntity = Pais.class)
    //@JoinColumn(name="nombre_pais", referencedColumnName = "nombre")
    private Pais pais;


    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate birthdate;


    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Interes.class)
    @JoinTable(name = "Turista_interes", joinColumns = @JoinColumn(name = "mail_turista", referencedColumnName = "mail"), inverseJoinColumns = @JoinColumn(name = "id_interes", referencedColumnName = "id_interes"))
    private Collection<Interes> intereses;



    public Turist(String mail, String password, Pais pais, LocalDate birthdate) {
        super(mail, password);
        this.pais = pais;
        this.birthdate = birthdate;
    }

    public Turist() {
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    /*// Agregado en la clase de base de datos viernes 8/10
    @Autowired
    @Transient
    private CountryRepository countryRepository;

    public void setNacionalidad(String nationality){
        this.pais = countryRepository.findOneByNombre(nationality);
    }*/

    public Collection<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(Collection<Interes> intereses) {
        this.intereses = intereses;
    }
}

