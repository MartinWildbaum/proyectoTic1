package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Administrador;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrador, String> {
    /**
     * Retorna un administrador por mail si encuentra mas de una lanza una excepcion
     * @param mail
     * @return
     */
    Administrador findOneByMail(String mail);

    Boolean existsByMail(String mail);

}
