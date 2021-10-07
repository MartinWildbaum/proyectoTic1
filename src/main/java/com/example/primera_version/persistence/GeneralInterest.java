package com.example.primera_version.persistence;

import com.example.primera_version.business.entities.InteresGeneral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GeneralInterest extends CrudRepository<InteresGeneral,Long> {

    /**
     * Retorna un interes general por idInteres si encuentra mas de una lanza una excepcion
     * @param idInteres
     * @return
     */
    InteresGeneral findOneByIdInteres(Long idInteres);

}

