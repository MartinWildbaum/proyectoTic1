package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.Turist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuristRepository extends CrudRepository<Turist, String> {

    /**
     * Retorna un turista por mail si encuentra mas de una lanza una excepcion
     * @param mail
     * @return
     */
    Turist findOneByMail(String mail);

    Boolean existsByMail(String mail);
}
