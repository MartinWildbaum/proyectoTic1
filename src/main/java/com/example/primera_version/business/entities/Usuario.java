package com.example.primera_version.business.entities;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class Usuario {

    @Id
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;


    public Usuario(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }


    public Usuario() {
        this.mail = "";//FIXME
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

