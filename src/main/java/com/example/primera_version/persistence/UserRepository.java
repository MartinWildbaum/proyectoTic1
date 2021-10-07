package com.example.primera_version.persistence;


import com.example.primera_version.business.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Usuario, String> {

    /**
     * Retorna un usuario por mail si encuentra mas de una lanza una excepcion
     * @param mail
     * @return
     */
    Usuario findOneByMail(String mail);

    Boolean existsByMail(String mail);

}

