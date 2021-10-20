package com.example.primera_version.business.entities;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "operadores_turisticos")
public class OperadorTuristico {


    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_op_tur")
    private Long idOpTur;

    @Column(name = "razon_social", nullable = false, unique = true)
    private String razonSocial;

    @Column(name = "name_tur_op", nullable = false)
    private String nameTO;

    @Column(name = "estado", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean estado;

    @Column(name = "contact_name", nullable = false)
    private String contact_name;

    @Column(name = "contact_surname", nullable = false)
    private String contact_surname;

    @Column(name = "contact_phone", nullable = false)
    private String contact_phone;

    @Column(name = "contact_age", nullable = true)
    private Integer contact_age;

    @OneToMany(targetEntity = Administrador.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_op_tur")
    private Collection<Experiencia> experiencias;


    @OneToMany(targetEntity = UsuarioOpTur.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_op_tur")
    private Collection<UsuarioOpTur> usuariosDelOperador;

    public OperadorTuristico() {
    }

    public OperadorTuristico(Long idOpTur, String razonSocial, String nameTO, String contact_name, String contact_surname, String contact_phone, Integer contact_age) {
        this.idOpTur = idOpTur;
        this.razonSocial = razonSocial;
        this.nameTO = nameTO;
        this.estado = false;
        this.contact_name = contact_name;
        this.contact_surname = contact_surname;
        this.contact_phone = contact_phone;
        this.contact_age = contact_age;
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
        return contact_name;
    }

    public void setContact_name(String cantact_name) {
        this.contact_name = cantact_name;
    }

    public String getCantact_surname() {
        return contact_surname;
    }

    public void setContact_surname(String cantact_surname) {
        this.contact_surname = cantact_surname;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public Integer getContact_age() {
        return contact_age;
    }

    public void setContact_age(Integer contact_age) {
        this.contact_age = contact_age;
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
}
