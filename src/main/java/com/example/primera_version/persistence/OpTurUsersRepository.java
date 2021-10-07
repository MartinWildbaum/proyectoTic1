package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.UsuarioOpTur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OpTurUsersRepository extends CrudRepository<UsuarioOpTur,String> { // Repositorio de los usuarios asociados a operadores turisticos
    /**
     * Retorna un usuario asociado a algun oprador turistico por mail si encuentra mas de una lanza una excepcion
     * @param mail
     * @return
     */
    UsuarioOpTur findOneByMail(String mail);

    Boolean existsByMail(String mail);
}
