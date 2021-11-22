package com.example.primera_version.business.entities;
import javax.persistence.*;
import java.util.Collection;
@Entity
@Table(name = "operadores_turisticos")
public class OperadorTuristico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_op_tur")
    private Long idOpTur;

    @Column(name = "razon_social", nullable = false, unique = true)
    private String razonSocial;

    @Column(name = "name_tur_op", nullable = false, unique = true)
    private String nameTO;

    @Column(name = "estado", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean estado = true; // Lo hago empezar habilitado ya que lo registra un administrador

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "contact_surname", nullable = false)
    private String contactSurname;

    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Column(name = "contact_age", nullable = true)
    private Integer contactAge;

    @OneToMany(targetEntity = Administrador.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_op_tur")
    private Collection<Experiencia> experiencias;


    @OneToMany(mappedBy = "operadorTuristico", cascade = CascadeType.ALL)
    private Collection<UsuarioOpTur> usuariosDelOperador;

    public OperadorTuristico() {
    }

    public OperadorTuristico(Long idOpTur, String razonSocial, String nameTO, String contact_name, String contact_surname, String contact_phone, Integer contact_age) {
        this.idOpTur = idOpTur;
        this.razonSocial = razonSocial;
        this.nameTO = nameTO;
        this.estado = true;
        this.contactName = contact_name;
        this.contactSurname = contact_surname;
        this.contactPhone = contact_phone;
        this.contactAge = contact_age;
    }


    public Long getIdOpTur() {
        return idOpTur;
    }

    public void setIdOpTur(Long idOpTur) {
        this.idOpTur = idOpTur;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNameTO() {
        return nameTO;
    }

    public void setNameTO(String nameTO) {
        this.nameTO = nameTO;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCantact_name() {
        return contactName;
    }

    public void setContact_name(String cantact_name) {
        this.contactName = cantact_name;
    }

    public String getCantact_surname() {
        return contactSurname;
    }

    public void setContact_surname(String cantact_surname) {
        this.contactSurname = cantact_surname;
    }

    public String getContact_phone() {
        return contactPhone;
    }

    public void setContact_phone(String contact_phone) {
        this.contactPhone = contact_phone;
    }

    public Integer getContact_age() {
        return contactAge;
    }

    public void setContact_age(Integer contact_age) {
        this.contactAge = contact_age;
    }

    public Collection<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(Collection<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public Collection<UsuarioOpTur> getUsuariosDelOperador() {
        return usuariosDelOperador;
    }

    public void setUsuariosDelOperador(Collection<UsuarioOpTur> usuariosDelOperador) {
        this.usuariosDelOperador = usuariosDelOperador;
    }

    @Override
    public String toString() {
        return nameTO;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof OperadorTuristico)) return false;
        OperadorTuristico operadorTuristico = (OperadorTuristico) obj;
        return this.getIdOpTur().equals(operadorTuristico.getIdOpTur());
    }
}