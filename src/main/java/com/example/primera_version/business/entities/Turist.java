package com.example.primera_version.business.entities;


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

    @ManyToMany(targetEntity = Interes.class/*, fetch = FetchType.EAGER*/)
    @JoinTable(name = "turista_interes", joinColumns = @JoinColumn(name = "mail_turista", referencedColumnName = "mail"), inverseJoinColumns = @JoinColumn(name = "id_interes", referencedColumnName = "id_interes"))
    private Collection<Interes> intereses;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turista")
    private Collection<Reserva> reservas;

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



    public Collection<Interes> getIntereses() {
        return intereses;
    }

    public String getListaIntereses(){
        String devolucion = " ";
        for (Interes interes: this.getIntereses()) {
            devolucion = devolucion + "\n" + interes.getNombre();
        }
        return devolucion;
    }

    public void setIntereses(Collection<Interes> intereses) {
        //Porque no anda?
        this.intereses = intereses;

    }

    /*public void sumarIntereses(Collection<Interes> intereses){
        if (this.intereses == null){
            this.intereses = intereses;
        }else{
            this.intereses.addAll(intereses);
        }
    }*/


    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(String valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public Boolean getEstaVacunado() {
        return estaVacunado;
    }

    public void setEstaVacunado(Boolean estaVacunado) {
        this.estaVacunado = estaVacunado;
    }

    public Collection<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }
}

