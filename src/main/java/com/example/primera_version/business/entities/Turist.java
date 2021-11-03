package com.example.primera_version.business.entities;



import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "turists")
public class Turist extends Usuario{

    @ManyToOne(targetEntity = Pais.class)
    @JoinColumn(name="nombre_pais", referencedColumnName = "nombre")
    private Pais pais;


    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate birthdate;

    @Column(name = "esta_vacunado", nullable = true)
    private Boolean estaVacunado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = true)
    private TipoDocumento tipoDocumento;

    private enum TipoDocumento {
        PASAPORTE, CEDULA;
    }

    @Column(name = "valor_documento", nullable = true)
    private String valorDocumento;

    @ManyToMany(targetEntity = Interes.class, fetch = FetchType.EAGER)
    @JoinTable(name = "Turista_interes", joinColumns = @JoinColumn(name = "mail_turista", referencedColumnName = "mail"), inverseJoinColumns = @JoinColumn(name = "id_interes", referencedColumnName = "id_interes"))
    private Collection<Interes> intereses;



    public Turist(String mail, String password, Pais pais, LocalDate birthdate, Collection<Interes> intereses) {
        super(mail, password);
        this.pais = pais;
        this.birthdate = birthdate;
        this.intereses = intereses;
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

    public String getListaIntereses(){
        String devolucion = " ";
        intereses = this.getIntereses();
        for (Interes interes: intereses) {
            devolucion = devolucion + "\n" + interes.getNombre();
        }
        return devolucion;
    }

    public void setIntereses(Collection<Interes> intereses) {
        this.intereses = intereses;
    }
}

