package com.example.primera_version.business.entities;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@PrimaryKeyJoinColumn(name = "mail")
@Table(name = "administrador")
public class Administrador extends Usuario{

    // Constructores

    public Administrador(String mail, String password) {
        super(mail, password);
    }

    public Administrador() {
    }

}
